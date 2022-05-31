<script>
import { onMount } from "svelte";
import * as Api from './api'

let tags = []
let selectedID = null

onMount(() => {
    Api.getTags().then(rTags => {
        tags = rTags
        selectedID = rTags[0].tid
    })
});
</script>

<div class="tags-wrap">
    <div class="tags">
        {#each tags as tag}
        <div class="item" class:active={selectedID === tag.tid}>{tag.name}</div>
        {/each}
    </div>
    <div class="add-btn">+</div>
</div>

<style>
.tags-wrap {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 57px;
    margin: 30px;
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