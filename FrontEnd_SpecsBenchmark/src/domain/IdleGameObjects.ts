export type GameState = {
  money: number;
  inventory: number;
  gpuPerSecond: number;
  demand: number;
  marketingLevel: number;
  factoryLevel: number;
  pricePerGpu: number;
  lastSavedAt: number;
  purchasedUpgradeIds: string[];
};

export type OneTimeUpgrade = {
  id: string;
  name: string;
  description: string;
  cost: number;

  productionMultiplier?: number;
  demandMultiplier?: number;
};

export const ONE_TIME_UPGRADES: OneTimeUpgrade[] = [
  {
    id: "better-tools",
    name: "Bessere Werkzeuge",
    description: "Erhöht die GPU-Produktion um 25%.",
    cost: 2500,
    productionMultiplier: 1.25,
  },
  {
    id: "social-media-ads",
    name: "Social-Media-Werbung",
    description: "Erhöht die Nachfrage um 30%.",
    cost: 4000,
    demandMultiplier: 1.3,
  },
  {
    id: "quality-branding",
    name: "Qualitätsmarke",
    description: "Erhöht Produktion und Nachfrage um 15%.",
    cost: 10000,
    productionMultiplier: 1.15,
    demandMultiplier: 1.15,
  },
];