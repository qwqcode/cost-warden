<script>
import { createEventDispatcher } from 'svelte';
import * as Api from './api'; 

const dispatch = createEventDispatcher();

let type = 'login';
let username = 'admin';
let password = 'admin';
    
// fields for signup
let email = '';
let rePassword = '';

// 登录
function login() {
    Api.login(username, password).then(() => {
        dispatch('logined');
    });
}

// 注册
function signup() {
    Api.signup(username, email, password, rePassword).then(() => {
        dispatch('signuped');
    })
}

function switchType() {
    type = (type === 'login') ? 'signup' : 'login'
}
</script>

<div class="login">
    {#if type === 'login'}
    <input type="text" bind:value={username} placeholder="用户名" autocomplete="off">
    <input type="password" bind:value={password} placeholder="密码">
    {:else}
    <input type="text" bind:value={username} placeholder="用户名" autocomplete="off">
    <input type="text" bind:value={email} placeholder="邮箱" autocomplete="off">
    <input type="password" bind:value={password} placeholder="密码">
    <input type="password" bind:value={rePassword} placeholder="确认密码">
    {/if}
    <div class="bottom">
        {#if type === 'login'}
        <button type="submit" on:click={login}>登录</button>
        <button class="switch-btn" on:click="{switchType}">注册</button>
        {:else}
        <button type="submit" on:click={signup}>注册</button>
        <button class="switch-btn" on:click="{switchType}">登录</button>
        {/if}
    </div>
</div>

<style>
    .login {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    input {
        width: 270px;
        line-height: 37px;
        padding: 0 20px;
        background: #F1F1F1;
        border: 0;
        border-left: 5px solid transparent;
        outline: none;
    }

    input:focus {
        border-left-color: #00A3FF;
    }

    .bottom {
        margin-top: 20px;
        width: 270px;
        display: flex;
        justify-content: space-between;
    }

    button {
        cursor: pointer;
        display: inline-block;
        width: 80px;
        height: 37px;
        border: 0;
        background: #00A3FF;
        color: #fff;
    }

    .switch-btn {
        cursor: pointer;
        background: transparent;
        color: #000;
        width: fit-content;
    }
</style>