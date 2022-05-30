<script>
    import { createEventDispatcher } from 'svelte';

    const dispatch = createEventDispatcher();

    let username = '';
    let password = '';

	function login() {
		dispatch('login', {
			username, password
		});
	}
</script>

<div class="login">
    <input type="text" bind:value={username} placeholder="用户名" autocomplete="off">
    <input type="password" bind:value={password} placeholder="密码">
    <div class="bottom">
        <button type="submit" on:click={login}>登录</button>
        <button class="signup-btn">注册</button>
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

    .signup-btn {
        cursor: pointer;
        background: transparent;
        color: #000;
        width: fit-content;
    }
</style>