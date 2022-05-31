<script>
import * as Api from './api';
import { onMount } from "svelte";
import { tags as sTags, costs, editCost as sEditCost, FetchCosts, SwitchWorkSpace } from './stores'

let dateGrpCosts = []
let tags = []

costs.subscribe(value => {
  dateGrpCosts = value
})
sTags.subscribe(value => {
  tags = value
})

onMount(() => {
  FetchCosts()
  // console.log("onMount");
});

function delCost(cid) {
  const confirm = window.confirm('确定删除该条记录？')
  if (!confirm) return

  Api.delCost(cid).then(() => {
    FetchCosts()
  })
}

function editCost(cost) {
  SwitchWorkSpace('edit')
  sEditCost.set(cost)
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
                <div class="tag">{tags.find(o => o.tid === cost.tid)?.name}</div>
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

<style>
.list {
  height: calc(100% - 60px);
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
</style>