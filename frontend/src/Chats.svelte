<script>
import * as Api from './lib/api';
import * as Stat from './lib/stat'
import { onMount } from 'svelte';

let monthlyEl = null
let yearPieEl = null
let monthPieEl = null

let curtYear = new Date().getFullYear()
let curtMonth = new Date().getMonth()+1

onMount(() => {
    Api.costStat().then((data) => {
        if (data.monthPie.length) Stat.InitPieChart(monthPieEl, data.monthPie)
        if (data.priceMonths.length) Stat.InitMonthlyChart(monthlyEl, data.priceMonths)
        if (data.yearPie.length) Stat.InitPieChart(yearPieEl, data.yearPie)
    })
})
</script>

<div class="charts">
    <div class="scene scene-1">
        <div class="chart-item">
            <h2>本月开支占比</h2>
            <div class="pie-chart" bind:this={monthPieEl}></div>
        </div>

        <div class="chart-item">
            <h2>全年开支占比</h2>
            <div class="pie-chart" bind:this={yearPieEl}></div>
        </div>
    </div>
    <div class="scene scene-2">
        <div class="chart-item">
            <h2>全年每月开支统计</h2>
            <div class="bar-chart" bind:this={monthlyEl}></div>
        </div>
    </div>
</div>

<style>
.charts {
    height: 100%;
}

.scene {
    height: 50%;
    width: 100%;
    display: flex;
    flex-direction: row;
}

.scene-1 {
    height: 220px;
    margin-bottom: 20px;
}

.chart-item {
    flex: 1;
}

.chart-item h2 {
    margin: 0;
    padding: 0;
    font-size: 20px;
    line-height: 30px;
    margin-bottom: 10px;
}

.pie-chart {
    height: 500px;
    overflow: hidden;
}

.bar-chart {
    height: 380px;
    overflow: hidden;
}
</style>