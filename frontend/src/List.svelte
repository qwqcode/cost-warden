<script>
import * as Api from './lib/api';
import { FetchCosts, SwitchWorkSpace } from './lib/act'
import { tags as sTags, costs, editCost as sEditCost } from './lib/stores'
import { onMount } from "svelte";
import notify from './lib/notify'

let dateGrpCosts = []
let priceSum = 0
let tags = []
let filterConds = []

costs.subscribe(value => {
  dateGrpCosts = value
  recalcSum()
})

sTags.subscribe(value => {
  tags = value
})

onMount(() => {
  // console.log("onMount");
});

function delCost(cid) {
  const confirm = window.confirm('确定删除该条记录？')
  if (!confirm) return

  Api.delCost(cid).then(() => {
    notify('删除成功', 's')
    FetchCosts()
  })
}

function editCost(cost) {
  SwitchWorkSpace('edit')
  sEditCost.set(cost)
}

function filterByDate(type, grpItem) {
  const type2Fields = {
    year: ['year'],
    month: ['year', 'month'],
    day: ['year', 'month', 'day']
  };

  const nFilterConds = filterConds.filter(o => !type2Fields[type].includes(o.name))
  type2Fields[type].forEach((field) => { nFilterConds.push({ name: field, value: grpItem[field] }) })

  priceSum = ''
  filterConds = nFilterConds
  FetchCosts(filterConds)
}

function filterByTid(tid) {
  filterConds.push({ name: 'tid', value: tid })
  priceSum = ''
  filterConds = filterConds
  FetchCosts(filterConds)
}

function recalcSum() {
  // 计算总消费
  priceSum = dateGrpCosts.reduce((sum, item) => {
    return sum + item.costs.reduce((sum, cost) => {
      return sum + cost.price
    }, 0)
  }, 0)
  priceSum = priceSum?.toFixed(2) || '-'
}
</script>

<div class="list fade-in">
    {#each dateGrpCosts as grpItem}
    <div class="date">
      <span on:click={()=>{filterByDate('year', grpItem)}}>{grpItem.year}</span>/
      <span on:click={()=>{filterByDate('month', grpItem)}}>{grpItem.month}</span>/
      <span on:click={()=>{filterByDate('day', grpItem)}}>{grpItem.day}</span>
    </div>

    {#each grpItem.costs as cost}
    <div class="item">
        <div class="left">
            <div class="main">
                <div class="price">-{cost.price}</div>
                <div class="tag" on:click={() => { filterByTid(cost.tid) }}>{tags.find(o => o.tid === cost.tid)?.name}</div>
            </div>
            <div class="sub" style:display={!cost.note ? 'none' : ''}>
                <div class="note">{cost.note || ''}</div>
            </div>
        </div>
        <div class="right">
            <div class="date">{cost.time}</div>
            <div class="actions">
                <span class="edit-btn" on:click={() => { editCost(cost) }}>编辑</span>
                <span class="del-btn" on:click={() => { delCost(cost.cid) }}>删除</span>
            </div>
        </div>
    </div>
    {/each}

    {/each}
</div>

<div class="filter-list-wrap">
  <div class="filter-list" style:display={!filterConds.length ? 'none' : ''}>
    {#each filterConds as item}
      <div class="cond-item" on:click={() => {
        filterConds = filterConds.filter(o => o.name !== item.name)
        FetchCosts(filterConds)
      }}>{item.name.substr(0, 1).toUpperCase()} = {(item.name !== 'tid') ? item.value : tags.find(o => o.tid === item.value).name}</div>
    {/each}
  </div>
</div>

<div class="counter">
  {#if !!priceSum}
  <div class="price fade-in">-{priceSum}</div>
  {/if}
</div>

<style>
.list {
  position: relative;
  height: calc(100% - 60px);
  overflow-y: auto;
}

.list > .date {
  font-size: 18px;
  padding: 15px 25px 10px 25px;
  display: flex;
  flex-direction: row;
}

.list > .date > span {
  cursor: pointer;
}

.list > .date > span:hover {
  text-decoration: underline;
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
}

.item .main .price {
  color: #d92929;
  margin-right: 12px;
  font-size: 19px;
}

.item .main .tag {
  font-size: 16px;
  color: #000;
  cursor: pointer;
}

.item .main .tag:hover {
  text-decoration: underline;
}

.item .sub {
  margin-top: 10px;
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

.filter-list-wrap {
  position: absolute;
  right: 10px;
  top: 70px;
}

.filter-list {
  display: flex;
  flex-direction: row;
}

.filter-list .cond-item {
  background: #f1f1f1;
  padding: 2px 6px;
  cursor: pointer;
}

.filter-list .cond-item:hover {
  background: rgb(219, 219, 219);
}

.filter-list .cond-item:not(:last-child) {
  margin-right: 5px;
}

.counter {
  top: -32px;
  right: -5px;
  position: absolute;
  display: inline-block;
  font-size: 20px;
  background: rgba(217, 41, 41, 0.865);
  color: #fff;
  margin-top: 5px;
  padding: 4px 10px;
  text-align: right;
  transition: all .3s;
  min-height: 20px;
  min-width: 20px;
}
</style>