<template>
  <div id="main">
    <CsInput
      placeholder="Suchen..."
      maxLength="240px"
      v-model="query"
      type="text"
      @focus="isOpen = true"
      ref="inputEl"
    />
    <div id="results" ref="resultsEl" @click="onResultsClick"></div>
  </div>
</template>

<script setup lang="ts">
import { computed, ref, watchEffect, onMounted, onUnmounted } from "vue";
import CsInput from "./CsInput.vue";

const props = defineProps<{
  nameList: string[];
  modelValue: string;
}>();

const emit = defineEmits<{
  (e: "update:modelValue", value: string): void;
  (e: "select", value: string): void;
}>();

const query = ref(props.modelValue ?? "");

const resultsEl = ref<HTMLDivElement | null>(null);
const inputEl = ref<HTMLElement | null>(null);
const isOpen = ref(false);

const filtered = computed(() => {
  const term = query.value.trim().toLowerCase();
  if (!term) return props.nameList.slice(0, 10);
  return props.nameList.filter(x => x.toLowerCase().includes(term)).slice(0, 50);
});

watchEffect(() => {
  if (!resultsEl.value || !isOpen.value) {
    if (resultsEl.value) resultsEl.value.innerHTML = "";
    return;
  }

  resultsEl.value.innerHTML = filtered.value
    .map(x => `<div class="row" data-val="${escapeHtmlAttr(x)}">${escapeHtmlText(x)}</div>`)
    .join("");
});

watchEffect(() => {
  isOpen.value = query.value.trim().length > 0;
});

function onResultsClick(e: MouseEvent) {
  const row = (e.target as HTMLElement | null)?.closest(".row") as HTMLElement | null;
  if (!row) return;

  const val = row.dataset.val ?? "";
  emit("update:modelValue", val);
  emit("select", val);
  query.value = val;                   
  isOpen.value = false;
}

function escapeHtmlText(s: string) {
  return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
}
function escapeHtmlAttr(s: string) {
  return escapeHtmlText(s).replaceAll('"', "&quot;");
}

function onDocumentClick(e: MouseEvent) {
  const target = e.target as Node;

  const clickedInResults = !!resultsEl.value?.contains(target);
  const clickedInInput = !!(inputEl.value && (inputEl.value as any).$el
    ? (inputEl.value as any).$el.contains(target) // falls CsInput eine Komponente ist
    : inputEl.value?.contains(target));

  if (!clickedInResults && !clickedInInput) {
    isOpen.value = false;
  }
}

onMounted(() => document.addEventListener("click", onDocumentClick));
onUnmounted(() => document.removeEventListener("click", onDocumentClick));
</script>

<style lang="scss">
#results {
    max-height:240px; 
    overflow:auto;
    width: 276px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    position: absolute;
    z-index: 10;
}

.row {
    background-color: white;
}

.row:hover {
    cursor: pointer;
    background-color: var(--primaryDarkerBackgroundColor1);
}
</style>