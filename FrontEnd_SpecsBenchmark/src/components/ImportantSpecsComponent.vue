<template>
    <div id="mainMostImportant">
        <h3>Wichtige Spezifikationen</h3>
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
        <div class="information">
            <div>{{ props.sixthAssignment }}</div>
            <div>{{ props.sixthValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.seventhAssignment }}</div>
            <div>{{ props.seventhValue }}</div>
        </div>
        <div class="information">
            <div>{{ props.eigthAssignment }}</div>
            <div>{{ props.eigthValue }}</div>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = withDefaults(defineProps<{
    firstAssignment: string;
    firstValue: number;
    secondAssignment: string;
    secondValue: number;
    thirdAssignment: string;
    thirdValue: number;
    fourthAssignment: string;
    fourthValue: number;
    fifthAssignment: string;
    fifthValue: number;
    sixthAssignment: string;
    sixthValue: number;
    seventhAssignment: string;
    seventhValue: number;
    eigthAssignment: string;
    eigthValue: number;
}>(),{
    firstAssignment: "null",
    firstValue: 0,
    secondAssignment: "null",
    secondValue: 0,
    thirdAssignment: "null",
    thirdValue: 0,
    fourthAssignment: "null",
    fourthValue: 0,
    fifthAssignment: "null",
    fifthValue: 0,
    sixthAssignment: "null",
    sixthValue: 0,
    seventhAssignment: "null",
    seventhValue: 0,
    eigthAssignment: "null",
    eigthValue: 0
})
</script>

<style lang="scss" scoped>
#mainMostImportant {
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