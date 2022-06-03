<script>
import { createEventDispatcher, onMount } from 'svelte';
import * as Api from './lib/api'; 
import notify from './lib/notify';

const dispatch = createEventDispatcher();

let type = 'login';
let username = '';
let password = '';

// fields for signup
let email = '';
let rePassword = '';

onMount(() => {
    // 登录状态检测跳转
    Api.getUserStatus().then((status) => {
        if (status.isLogin) {
            dispatch('logined');
            return;
        }

        // 使用 localStorge 保存数据登录
        let localUser = localStorage.getItem('user')
        if (!localUser) return
        try {
            localUser = JSON.parse(localUser)
        } catch { return }
        if (localUser.username && localUser.password) {
            username = localUser.username
            password = localUser.password
            login()
        }
    })
})

// 登录
function login() {
    Api.login(username, password).then(() => {
        // 记住密码
        localStorage.setItem('user', JSON.stringify({ username, password }));

        dispatch('logined');
    }).catch(() => {
        localStorage.removeItem('user');
    });
}

// 注册
function signup() {
    Api.signup(username, email, password, rePassword).then(() => {
        notify('注册成功', 's');
        type = 'login'
    })
}

function switchType() {
    type = (type === 'login') ? 'signup' : 'login'
}
</script>

<form class="login" on:submit|preventDefault={() => { type === 'login' ? login() : signup() }}>
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
        <button type="submit">登录</button>
        <button class="switch-btn" on:click="{switchType}">注册</button>
        {:else}
        <button type="submit">注册</button>
        <button class="switch-btn" on:click="{switchType}">登录</button>
        {/if}
    </div>
</form>

<style>
    .login {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .bottom {
        margin-top: 20px;
        width: 270px;
        display: flex;
        justify-content: space-between;
    }

    .switch-btn {
        cursor: pointer;
        background: transparent;
        color: #000;
        width: fit-content;
    }
</style>