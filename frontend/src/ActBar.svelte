<script>
import * as Api from './lib/api';

import { SwitchWorkSpace, FetchCosts } from './lib/act'
import { workspace as sWorkpace } from './lib/stores'

let curt = ''
let isSearchShow = false
let searchEl = null
let searchVal = ''
sWorkpace.subscribe(val => {
    curt = val
})

function navigate(name) {
    SwitchWorkSpace(name)
}

function showSearch() {
    isSearchShow = true
    setTimeout(() => {
        searchEl.focus()
    }, 80)
}

function hideSearch() {
    isSearchShow = false
    searchVal = ''
}

let searchTimer = null

$: {
    if (searchTimer) clearTimeout(searchTimer)

    searchTimer = setTimeout(() => {    
        search(searchVal)
    }, 80)
}

function search(keywords) {
    keywords = keywords.trim();
    if (!keywords) {
        FetchCosts()
        return
    }

    const cond = []

    const dayRegx = /^(\d+)[-/](\d+)[-/](\d+)$/;
    const monthRegx = /^(\d+)[-/](\d+)$/;
    const yearRegx = /^(\d+)$/;

    if (yearRegx.test(keywords)) {
        cond.push({ name: 'year', value: keywords })
    } else if (monthRegx.test(keywords)) {
        const [, year, month] = keywords.match(monthRegx)
        cond.push({ name: 'year', value: year })
        cond.push({ name: 'month', value: month })
    } else if (dayRegx.test(keywords)) {
        const [, year, month, day] = keywords.match(dayRegx)
        cond.push({ name: 'year', value: year })
        cond.push({ name: 'month', value: month })
        cond.push({ name: 'day', value: day })
    } else {
        cond.push({ name: 'note', value: keywords })
    }

    FetchCosts(cond)
}

function logout() {
    Api.logout().then(() => {
        location.reload()
        localStorage.removeItem('user')
    })
}
</script>

<div class="act-bar fade-in" style:display={isSearchShow ? 'none' : ''}>
    <div class="item" on:click={() => { navigate('add') }} class:active={curt === 'add'}>记录</div>
    <div class="item" on:click={() => { navigate('stat') }} class:active={curt === 'stat'}>分析</div>
    <div class="item" on:click={() => { showSearch() }}>搜索</div>
    <div class="item" on:click={() => { logout() }}>注销</div>
</div>

<div class="search-bar fade-in" style:display={!isSearchShow ? 'none' : ''}>
    <input type="text" placeholder="输入关键字..." bind:value={searchVal} bind:this={searchEl}>
    <div class="close-btn" on:click={() => { hideSearch() }}>×</div>
</div>

<style>
.act-bar {
    margin-bottom: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 50px;
    padding-top: 10px;
}

.act-bar .item {
    display: flex;
    align-items: center;
    font-size: 20px;
    cursor: pointer;
}

.act-bar .item:not(:last-child) {
    margin-right: 1.6em;
}

.act-bar .item:hover {
    color: #00A3FF;
}

.act-bar .item.active::before {
    content: '';
    display: inline-block;
    margin-top: 10px;
    margin-right: 10px;
    width: 11px;
    height: 11px;
    transform: translateY(-50%);
    background: #00A3FF;
    animation: animFadeIn both .3s;
}

.search-bar {
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 100%;
    height: 50px;
    padding-top: 10px;
}

.search-bar input {
    flex: 1;
    height: 100%;
    border: 0;
    background: transparent;
    margin: 0;
    padding: 0 30px;
}

.search-bar .close-btn {
    cursor: pointer;
    font-size: 23px;
    flex-basis: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
}

.search-bar .close-btn:hover {
    background: #F1F1F1;
}
</style>