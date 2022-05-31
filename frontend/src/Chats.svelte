<script>
import * as Api from './api';
import * as Stat from './stat'
import { onMount } from 'svelte';

let pieChartEl = null

onMount(() => {
    Api.costStat().then((data) => {
        Stat.InitPieChart(pieChartEl, data)
    })
})
</script>

<div class="charts">
    <div class="pie-chart" bind:this={pieChartEl}></div>
</div>

<style>
.charts {
    height: 100%;
}

.pie-chart {
    height: 100%;
}
</style>