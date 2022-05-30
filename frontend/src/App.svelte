<script>
import Login from "./Login.svelte";
import * as Api from './api'; 

let logined = false
let sidebarBody;;

function loginAction(event) {
	fetch(`${Api.API_BASE}/user/login`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			username: event.detail.username,
			password: event.detail.password
		})
	}).then(res => {
		if (res.status === 200) {
			return res.json()
		}
	}).then(res => {
		if (!res.success) {
			alert(res.msg)
			return
		}

		setLogined();
		console.log(res)
	})
}

function setLogined() {
	logined = true
	setTimeout(() => {
		sidebarBody.style.display = ''
	}, 300)
}
</script>

<main>
	<div class="app">
		<div class="sidebar">
			<div class="brand" style:height="{!logined ? '100%' : '120px'}"></div>
			<div class="sidebar-body" bind:this={sidebarBody} style="display: none;">
			</div>
		</div>
		<div class="body">
			{#if !logined}
			<Login on:login={loginAction} />
			{/if}
		</div>
	</div>
</main>

<style>
	main {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 100vh;
	}

	.app {
		display: flex;
		flex-direction: row;
		width: 80vw;
		height: 80vh;
		background: #fff;
	}

	.sidebar {
		display: flex;
		flex-direction: column;
		flex-basis: 30%;
		height: 100%;
	}

	.sidebar-body {
		height: calc(100% - 120px);
		width: 100%;
	}

	.brand {
		position: relative;
		background: linear-gradient(180deg, #008DDD 0%, #00A3FF 100%);
		height: 100%;
		width: 100%;
		transition: height .3s cubic-bezier(0.4,0,0.2,1);
	}

	.brand:after {
		position: absolute;
		content: '';
		background-image: url(/logo.svg);
		background-repeat: no-repeat;
		background-position: center;
		background-size: 60%;
		height: 100%;
		width: 100%;
	}
	
	.body {
		display: flex;
		flex: 1;
	}
</style>