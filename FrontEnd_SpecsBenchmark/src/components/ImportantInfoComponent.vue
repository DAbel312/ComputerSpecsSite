<template>
    <div id="mainMostImportantInformation">
        <h3>Wichtige Detailinformationen</h3>
        <div class="information">
            <div>{{ props.firstAssignment }}</div>
            <div>{{ props.firstValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.secondAssignment }}</div>
            <div>{{ props.secondValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.thirdAssignment }}</div>
            <div>{{ props.thirdValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.fourthAssignment }}</div>
            <div>{{ props.fourthValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.fifthAssignment }}</div>
            <div>{{ props.fifthValue }}</div>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = withDefaults(defineProps<{
    firstAssignment: string;
    firstValue: string;
    secondAssignment: string;
    secondValue: string;
    thirdAssignment: string;
    thirdValue: string;
    fourthAssignment: string;
    fourthValue: string;
    fifthAssignment: string;
    fifthValue: string | number;
}>(),{
    firstAssignment: "null",
    firstValue: "null",
    secondAssignment: "null",
    secondValue: "null",
    thirdAssignment: "null",
    thirdValue: "null",
    fourthAssignment: "null",
    fourthValue: "null",
    fifthAssignment: "null",
    fifthValue: "null",
})
</script>

<style lang="scss" scoped>
#mainMostImportantInformation {
    background-color: white;
    height: fit-content;
    width: 90%;
    border-radius: 13px;
    padding-top: 10px;
    padding-bottom: 20px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    line-height: 150%;

    h3 {
        text-align: center;
        margin-bottom: 10px;
    }

    .information {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-row: 1fr;
        justify-items: center;
    }
}
</style>