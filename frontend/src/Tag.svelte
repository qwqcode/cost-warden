<script>
import { onMount } from "svelte";
import { FetchCosts, FetchTags } from './lib/act'
import { tags as sTags, selectedTid as sSelectedTid } from './lib/stores'
import * as Api from './lib/api'
import notify from './lib/notify'

let tags = []
let selectedID = null

onMount(() => {
});

sTags.subscribe(val => {
    tags = val
})

sSelectedTid.subscribe(val => {
    selectedID = val
})

function select(tid) {
    sSelectedTid.set(tid)
}

function delTag(tag) {
    if (!confirm(`确定标签 "${tag.name}" 吗？`)) return;
    Api.delTag(tag.tid).then(() => {
        notify('删除成功', 's')
        FetchTags()
    })
}

function addTag() {
    const name = prompt('请输入标签名称')
    if (!name) return;
    Api.addTag(name.trim()).then(() => {
        notify('创建成功', 's')
        FetchTags()
    })
}
</script>

<div class="tags-wrap fade-in">
    <div class="tags">
        {#each tags as tag}
        <div class="item"
            class:active={selectedID === tag.tid}
            on:click={() => { select(tag.tid) }}
            on:contextmenu={(e) => { delTag(tag);e.preventDefault() }}>{tag.name}</div>
        {/each}
    </div>
    <div class="add-btn" on:click={() => { addTag() }}>+</div>
</div>

<style>
.tags-wrap {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 57px;
    margin-bottom: 30px;
}

.add-btn {
    display: flex;
    flex-basis: 57px;
    width: 57px;
    height: 57px;
    background: #F1F1F1;
    margin-left: 15px;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    cursor: pointer;
}

.add-btn:hover {
    background: #00A3FF;
    color: #fff;
}

.tags {
    display: flex;
    flex: 1;
    flex-direction: row;
    height: 100%;
    padding: 0 20px;
    background: #F1F1F1;
}

.item {
    cursor: pointer;
    position: relative;
    display: flex;
    align-items: center;
    padding: 0 20px;
}

.item.active:after {
    content: '';
    position: absolute;
    bottom: 0;
    height: 5px;
    width: 40%;
    left: 50%;
    transform: translateX(-50%);
    background: #00A3FF;
}
</style>