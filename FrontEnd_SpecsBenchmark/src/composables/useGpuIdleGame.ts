import { computed, onMounted, onUnmounted, ref } from "vue";
import {
  ONE_TIME_UPGRADES,
  type GameState,
  type OneTimeUpgrade,
} from "../domain/IdleGameObjects";

const SAVE_KEY = "gpu-idle-game-save";

const BASE_PRICE_PER_GPU = 500;
const MIN_PRICE_PER_GPU = 100;
const MAX_PRICE_PER_GPU = 5000;
const PRICE_STEP = 50;

const PRESTIGE_REQUIREMENT = 1_000_000_000;
const PRESTIGE_MONEY_DIVISOR = 1_000_000_000;
const PRESTIGE_BONUS_PER_POINT = 0.05;

const BASE_GPU_PER_SECOND = 0.2;

const TICK_RATE_SECONDS = 0.1;
const TICK_RATE_MS = TICK_RATE_SECONDS * 1000;

function createInitialState(
  prestigePoints = 0,
  totalPrestigePoints = 0,
  prestigeCount = 0,
  lifetimeMoney = 0
): GameState {
  return {
    money: 0,
    inventory: 0,
    gpuPerSecond: BASE_GPU_PER_SECOND,
    demand: 1,
    marketingLevel: 0,
    factoryLevel: 1,
    pricePerGpu: BASE_PRICE_PER_GPU,
    lastSavedAt: Date.now(),
    purchasedUpgradeIds: [],

    lifetimeMoney,
    prestigePoints,
    totalPrestigePoints,
    prestigeCount,
  };
}

export function useGpuIdleGame() {
  const state = ref<GameState>(applyOfflineProgress(loadGame()));

  let intervalId: number | undefined;

  const marketingCost = computed(() =>
    getMarketingCost(state.value.marketingLevel)
  );

  const factoryCost = computed(() =>
    getFactoryCost(state.value.factoryLevel)
  );

  const availableOneTimeUpgrades = computed(() =>
    ONE_TIME_UPGRADES.filter(
      (upgrade) => !state.value.purchasedUpgradeIds.includes(upgrade.id)
    )
  );

  const purchasedOneTimeUpgrades = computed(() =>
    ONE_TIME_UPGRADES.filter((upgrade) =>
      state.value.purchasedUpgradeIds.includes(upgrade.id)
    )
  );

  const potentialPrestigePoints = computed(() =>
    getPotentialPrestigePoints(state.value)
  );

  const prestigeMultiplier = computed(() =>
    getPrestigeMultiplier(state.value)
  );

  const isPrestigeAvailable = computed(() =>
    canPrestige(state.value)
  );

  function gameTick() {
    state.value = tick(state.value, TICK_RATE_SECONDS);
    saveGame(state.value);
  }

  function buyMarketingUpgrade() {
    state.value = buyMarketing(state.value);
    saveGame(state.value);
  }

  function buyFactory() {
    state.value = buyFactoryUpgrade(state.value);
    saveGame(state.value);
  }

  function resetGame() {
    state.value = createInitialState();
    saveGame(state.value);
  }

  function increasePrice() {
    changePrice(PRICE_STEP);
  }

  function decreasePrice() {
    changePrice(-PRICE_STEP);
  }

  function changePrice(amount: number) {
    const newPrice = Math.min(
      MAX_PRICE_PER_GPU,
      Math.max(MIN_PRICE_PER_GPU, state.value.pricePerGpu + amount)
    );

    const updatedState: GameState = {
      ...state.value,
      pricePerGpu: newPrice,
      lastSavedAt: Date.now(),
    };

    state.value = recalculateState(updatedState);
    saveGame(state.value);
  }

  function produceGpu(amount = 1) {
    if (amount <= 0) {
      return;
    }

    state.value = {
      ...state.value,
      inventory: state.value.inventory + amount,
      lastSavedAt: Date.now(),
    };

    saveGame(state.value);
  }

  function buyOneTimeUpgrade(upgradeId: string) {
    const upgrade = ONE_TIME_UPGRADES.find(
      (upgrade) => upgrade.id === upgradeId
    );

    if (!upgrade) {
      return;
    }

    if (state.value.purchasedUpgradeIds.includes(upgrade.id)) {
      return;
    }

    if (state.value.money < upgrade.cost) {
      return;
    }

    const updatedState: GameState = {
      ...state.value,
      money: state.value.money - upgrade.cost,
      purchasedUpgradeIds: [
        ...state.value.purchasedUpgradeIds,
        upgrade.id,
      ],
      lastSavedAt: Date.now(),
    };

    state.value = recalculateState(updatedState);
    saveGame(state.value);
  }

  function prestigeReset() {
    const gainedPrestigePoints = getPotentialPrestigePoints(state.value);

    if (gainedPrestigePoints <= 0) {
      return;
    }

    const oldLifetimeMoney = state.value.lifetimeMoney;

    const newPrestigePoints =
      state.value.prestigePoints + gainedPrestigePoints;

    const newTotalPrestigePoints =
      state.value.totalPrestigePoints + gainedPrestigePoints;

    const newPrestigeCount = state.value.prestigeCount + 1;

    state.value = recalculateState(
      createInitialState(
        newPrestigePoints,
        newTotalPrestigePoints,
        newPrestigeCount,
        oldLifetimeMoney
      )
    );

    saveGame(state.value);
  }

  onMounted(() => {
    intervalId = window.setInterval(gameTick, TICK_RATE_MS);
  });

  onUnmounted(() => {
    if (intervalId !== undefined) {
      clearInterval(intervalId);
    }
  });

  return {
    state,
    marketingCost,
    factoryCost,
    buyMarketingUpgrade,
    buyFactory,
    resetGame,
    increasePrice,
    decreasePrice,
    changePrice,
    produceGpu,
    availableOneTimeUpgrades,
    purchasedOneTimeUpgrades,
    buyOneTimeUpgrade,

    potentialPrestigePoints,
    prestigeMultiplier,
    isPrestigeAvailable,
    prestigeReset,
  };
}

function tick(state: GameState, seconds: number): GameState {
  const produced = state.gpuPerSecond * seconds;

  const maxSellable = state.demand * seconds;
  const actuallySold = Math.min(state.inventory + produced, maxSellable);

  const newInventory = state.inventory + produced - actuallySold;
  const earned = actuallySold * state.pricePerGpu;

  return {
    ...state,
    inventory: newInventory,
    money: state.money + earned,
    lifetimeMoney: state.lifetimeMoney + earned,
    lastSavedAt: Date.now(),
  };
}

function getMarketingCost(level: number): number {
  return Math.floor(1000 * Math.pow(1.25, level));
}

function getFactoryCost(level: number): number {
  return Math.floor(1500 * Math.pow(1.35, level));
}

function buyMarketing(state: GameState): GameState {
  const cost = getMarketingCost(state.marketingLevel);

  if (state.money < cost) {
    return state;
  }

  const updatedState: GameState = {
    ...state,
    money: state.money - cost,
    marketingLevel: state.marketingLevel + 1,
    lastSavedAt: Date.now(),
  };

  return recalculateState(updatedState);
}

function buyFactoryUpgrade(state: GameState): GameState {
  const cost = getFactoryCost(state.factoryLevel);

  if (state.money < cost) {
    return state;
  }

  const updatedState: GameState = {
    ...state,
    money: state.money - cost,
    factoryLevel: state.factoryLevel + 1,
    lastSavedAt: Date.now(),
  };

  return recalculateState(updatedState);
}

function saveGame(state: GameState): void {
  localStorage.setItem(SAVE_KEY, JSON.stringify(state));
}

function loadGame(): GameState {
  const raw = localStorage.getItem(SAVE_KEY);

  if (!raw) {
    return createInitialState();
  }

  try {
    const loadedState: GameState = {
      ...createInitialState(),
      ...JSON.parse(raw),
    };

    return recalculateState(loadedState);
  } catch {
    return createInitialState();
  }
}

function applyOfflineProgress(state: GameState): GameState {
  const now = Date.now();
  const secondsOffline = Math.floor((now - state.lastSavedAt) / 1000);

  if (secondsOffline <= 0) {
    return state;
  }

  const cappedSeconds = Math.min(secondsOffline, 60 * 60 * 8);

  return tick(state, cappedSeconds);
}

function calculateDemand(
  pricePerGpu: number,
  marketingLevel: number,
  demandMultiplier: number
): number {
  const baseDemand = 1 + marketingLevel * 0.4;
  const priceFactor = BASE_PRICE_PER_GPU / pricePerGpu;

  const demand = baseDemand * priceFactor * demandMultiplier;

  return Math.max(0.05, demand);
}

function getPurchasedUpgrades(state: GameState): OneTimeUpgrade[] {
  return ONE_TIME_UPGRADES.filter((upgrade) =>
    state.purchasedUpgradeIds.includes(upgrade.id)
  );
}

function getProductionMultiplier(state: GameState): number {
  return getPurchasedUpgrades(state).reduce((multiplier, upgrade) => {
    return multiplier * (upgrade.productionMultiplier ?? 1);
  }, 1);
}

function getDemandMultiplier(state: GameState): number {
  return getPurchasedUpgrades(state).reduce((multiplier, upgrade) => {
    return multiplier * (upgrade.demandMultiplier ?? 1);
  }, 1);
}

function calculateGpuPerSecond(
  factoryLevel: number,
  productionMultiplier: number
): number {
  return BASE_GPU_PER_SECOND * factoryLevel * productionMultiplier;
}

function getPrestigeMultiplier(state: GameState): number {
  return 1 + state.prestigePoints * PRESTIGE_BONUS_PER_POINT;
}

function getPotentialPrestigePoints(state: GameState): number {
  if (state.lifetimeMoney < PRESTIGE_REQUIREMENT) {
    return 0;
  }

  const totalPointsFromLifetimeMoney = Math.floor(
    state.lifetimeMoney / PRESTIGE_MONEY_DIVISOR
  );

  return Math.max(
    0,
    totalPointsFromLifetimeMoney - state.totalPrestigePoints
  );
}

function canPrestige(state: GameState): boolean {
  return getPotentialPrestigePoints(state) > 0;
}

function recalculateState(state: GameState): GameState {
  const productionMultiplier = getProductionMultiplier(state);
  const demandMultiplier = getDemandMultiplier(state);
  const prestigeMultiplier = getPrestigeMultiplier(state);

  return {
    ...state,
    gpuPerSecond: calculateGpuPerSecond(
      state.factoryLevel,
      productionMultiplier * prestigeMultiplier
    ),
    demand: calculateDemand(
      state.pricePerGpu,
      state.marketingLevel,
      demandMultiplier * prestigeMultiplier
    ),
  };
}