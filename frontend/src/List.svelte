<script>
import * as Api from './api';
import { onMount } from "svelte";

let dateGrpCosts = []

onMount(() => {
    Api.getCosts().then((costs) => {
        costs.forEach((c) => {
            const day = c.date.split(' ')[0];
            let dayArr = dateGrpCosts.find(o => (o.day === day));
            if (!dayArr) {
                dayArr = { day, costs: [] }
                dateGrpCosts.push(dayArr)
            }
            dayArr.costs.push(c);
        })
        dateGrpCosts = dateGrpCosts // notify arr changed
    })
    console.log("onMount");
});
</script>

<div class="list">
    {#each dateGrpCosts as item}
    <div class="date">{item.day}</div>

    {#each item.costs as cost}
    <div class="item">
        <div class="left">
            <div class="main">
                <div class="price">{cost.price}</div>
                <div class="tag">{cost.tid}</div>
            </div>
            <div class="sub">
                <div class="note">{cost.note}</div>
            </div>
        </div>
        <div class="right">
            <div class="date">{cost.date}</div>
            <div class="actions">
                <span class="edit-btn">编辑</span>
                <span class="del-btn">删除</span>
            </div>
        </div>
    </div>
    {/each}

    {/each}
</div>

<style>
.list {

}
</style>