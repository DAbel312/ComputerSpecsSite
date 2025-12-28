<template>
  <form class="form" :style="{ width: props.width }">
    <input class="input" :placeholder="props.placeholder" :type="props.type" v-model="model" :value="modelValue" :maxlength="props.maxLength" @input="onInput" v-bind="$attrs">
    <button class="reset" type="reset">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"></path>
      </svg>
    </button>
  </form>
</template>

<script setup lang="ts">
const model = defineModel<string>();

const props = withDefaults(defineProps<{
  placeholder: string,
  width?: string,
  type?: string,
  maxLength: string,
  modelValue: string
}>(),{
  placeholder: "Input Placeholder",
  width: "250px",
  type: "text",
  maxLength: "30"
});

const emit = defineEmits<{
  (e: "update:modelValue", v: string): void
  (e: "input", ev: Event): void
}>();

function onInput(ev: Event) {
  const v = (ev.target as HTMLInputElement).value;
  emit("update:modelValue", v);
  emit("input", ev);
}
</script>

<style scoped lang="scss">
/* From uiverse.io by @satyamchaudharydev */
/* removing default style of button */

.form button {
  border: none;
  background: none;
  color: #8b8ba7;
}
/* styling of whole input container */
.form {
  --timing: 0.3s;
  --height-of-input: 40px;
  --border-height: 2px;
  --input-bg: #fff;
  --border-color: black;
  --border-radius: 13px;
  --after-border-radius: 1px;
  position: relative;
  height: var(--height-of-input);
  display: flex;
  align-items: center;
  padding-inline: 0.8em;
  border-radius: var(--border-radius);
  transition: border-radius 0.5s ease;
  background: var(--input-bg,#fff);
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}
/* styling of Input */
.input {
  font-size: 0.9rem;
  background-color: transparent;
  width: 100%;
  height: 100%;
  padding-inline: 0.5em;
  border: none;
}
/* styling of animated border */
.form:before {
  content: "";
  position: absolute;
  background: var(--border-color);
  transform: scaleX(0);
  transform-origin: center;
  width: 100%;
  height: var(--border-height);
  left: 0;
  bottom: 0;
  border-radius: 1px;
  transition: transform var(--timing) ease;
}
/* Hover on Input */
.form:focus-within {
  border-radius: var(--after-border-radius);
}

input:focus {
  outline: none;
}
/* here is code of animated border */
.form:focus-within:before {
  transform: scale(1);
}
/* styling of close button */
/* == you can click the close button to remove text == */
.reset {
  border: none;
  background: none;
  opacity: 0;
  visibility: hidden;
}
/* close button shown when typing */
input:not(:placeholder-shown) ~ .reset {
  opacity: 1;
  visibility: visible;
}
/* sizing svg icons */
.form svg {
  width: 17px;
  margin-top: 3px;
}
</style>