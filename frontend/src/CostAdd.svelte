<script>
import { FetchCosts, FetchTags } from './lib/act'
import { selectedTid as sSelectedTid, editCost as sEditCost } from './lib/stores'
import notify from './lib/notify'
import * as Api from './lib/api'
import { onDestroy } from 'svelte';

let editCost = {}
let priceEl = null
let price = ''
let note = ''
let tid = 0

onDestroy(() => {
    price = ''
    note = ''
})

sSelectedTid.subscribe(val => {
    tid = val
})

sEditCost.subscribe(val => {
    editCost = val
    if (!val.cid) return
    price = String(val.price)
    note = String(val.note || '')
    sSelectedTid.set(val.tid)
})

function save() {
    if (price.trim() === '') {
        priceEl.focus();
        notify('请输入价格')
        return
    }

    if (!editCost.cid) {
        // 新增
        Api.addCost(price, tid, note).then(() => {
            price = ''
            note = ''

            // 刷新 Costs 列表
            FetchCosts()
            notify('保存成功', 's')
        })
    } else {
        // 修改
        Api.editCost(editCost.cid, price, tid, note).then(() => {
            FetchCosts()
            notify('修改成功', 's')
        })
    }
}
</script>

<div class="cost-add fade-in">
    <div class="text">{#if !editCost.cid}记录{:else}修改{/if}一笔消费</div>
    <div class="form">
        <input type="text" bind:value={price} bind:this={priceEl} placeholder="价格" autocomplete="off">
        <input type="text" bind:value={note} placeholder="备注" autocomplete="off">
        <div class="bottom">
            <button type="submit" on:click={save}>保存</button>
        </div>
    </div>
</div>

<style>
.cost-add {
    margin: 0 auto;
    margin-top: 100px;
    width: 337px;
}

.text {
    font-size: 20px;
    margin-bottom: 2em;
}

.form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
}

input {
    width: 100%;
}

.form .bottom {
    display: flex;
    flex-direction: row;
    width: 100%;
    margin-top: 20px;
}
</style>