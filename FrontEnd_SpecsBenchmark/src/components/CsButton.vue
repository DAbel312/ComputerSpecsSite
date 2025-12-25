<template>
  <button :style="{ 
  width: props.width, 
  padding: `${props.paddingTopBottom}px 20px` 
}">
    <span>{{ props.content }}</span>
  </button>
</template>

<script setup lang="ts">

const props = withDefaults(defineProps<{
  content: string,
  width?: string,
  paddingTopBottom?: number
}>(),{
  content: "Button",
  width: "170px",
  paddingTopBottom: 10
})
</script>


<style scoped lang="css">
/* From Uiverse.io by mrhyddenn */
button {
  background: #fff;
  border: none;
  display: inline-block;
  font-size: 15px;
  font-weight: 600;
  width: 0px;
  text-transform: uppercase;
  cursor: pointer;
  transform: skew(-21deg);
}

span {
  display: inline-block;
  transform: skew(21deg);
}

button::before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  right: 100%;
  left: 0;
  background: var(--primaryGreen1);
  opacity: 0;
  z-index: -1;
  transition: all 0.5s;
}

button:hover {
  color: #fff;
}

button:hover::before {
  left: 0;
  right: 0;
  opacity: 1;
}

</style>