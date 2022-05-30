<script>
import * as Api from './api';
import { onMount } from "svelte";

let dateGrpCosts = []

onMount(() => {
    Api.getCosts().then((costs) => {
        loadList(costs)
    })
    console.log("onMount");
});

function loadList(costs) {
    dateGrpCosts = [] // clear all original items
    costs.forEach((c) => {
        // @sample "2022-05-30 16:02:21.322000000"
        const time = c.date.split(' ')[1].split('.')[0].substr(0, 5)
        const day = c.date.split(' ')[0].replace(/-/g, '/');
        c = { time, day, ...c }
        let dayArr = dateGrpCosts.find(o => (o.day === day));
        if (!dayArr) {
            dayArr = { day, costs: [] }
            dateGrpCosts.push(dayArr)
        }
        dayArr.costs.push(c);
    })
    dateGrpCosts = dateGrpCosts // notify arr changed
}
</script>

<div class="list">
    {#each dateGrpCosts as item}
    <div class="date">{item.day}</div>

    {#each item.costs as cost}
    <div class="item">
        <div class="left">
            <div class="main">
                <div class="price">-{cost.price}</div>
                <div class="tag">{cost.tid}</div>
            </div>
            <div class="sub">
                <div class="note">{cost.note}</div>
            </div>
        </div>
        <div class="right">
            <div class="date">{cost.time}</div>
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
  height: 100%;
  overflow-y: auto;
}

.list > .date {
  font-size: 18px;
  padding: 15px 25px 10px 25px;
}

.item {
  position: relative;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 15px 25px;
}

.item:hover {
  background: #f4f4f4;
}

.item .left {
  flex: 1;
}

.item .right {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: flex-end;
}

.item .date {
  margin-bottom: 10px;
  color: #363636;
}

.item:hover .actions {
  opacity: 1;
}

.item .actions {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  opacity: 0;
  font-size: 14px;
}

.item .main {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  margin-bottom: 10px;
}

.item .main .price {
  color: #d92929;
  margin-right: 12px;
  font-size: 19px;
}

.item .main .tag {
  font-size: 16px;
  color: #000;
}

.item .sub {
}

.item .sub .note {
  font-size: 14px;
  color: #5C5C5C;
}

.item .actions > * {
  cursor: pointer;
}

.item .actions > *:hover {
  color: #00A3FF;
}

.item .actions > *:not(:last-child) {
  margin-right: 13px;
}
</style>