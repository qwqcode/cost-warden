<script>
import { FetchTags, workspace as sWorkpace } from './lib/stores'
import ActBar from "./ActBar.svelte";
import Chats from "./Chats.svelte";

import CostAdd from "./CostAdd.svelte";
import List from "./List.svelte";
import Login from "./Login.svelte";
import Tag from "./Tag.svelte";

let logined = false
let sidebarBody;

// stat|add
let workspace = ''

sWorkpace.subscribe(val => {
	workspace = val
})

function onLogined() {
	logined = true
	setTimeout(() => {
		sidebarBody.style.display = ''
	}, 300)

	// Fetch tags
	FetchTags()
}
</script>

<main>
	<div class="app">
		<div class="sidebar">
			<div class="brand" style:height="{!logined ? '100%' : '120px'}"></div>
			<div class="sidebar-body" bind:this={sidebarBody} style="display: none;">
				{#if logined}
					<ActBar />
					<List />
				{/if}
			</div>
		</div>
		<div class="body">
			{#if !logined}
			<Login on:logined={onLogined} />
			{:else}
			<div class="workspace">
				{#if ['add', 'edit'].includes(workspace)}
				<Tag />
				<CostAdd />
				{:else if workspace === 'stat'}
				<Chats />
				{/if}
			</div>
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
		width: 85vw;
		height: 85vh;
		background: #fff;
	}

	.sidebar {
		display: flex;
		flex-direction: column;
		flex-basis: 30%;
		height: 100%;
	}

	.sidebar-body {
		position: relative;
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

	.body .workspace {
		padding: 30px;
		width: 100%;
		overflow-y: auto;
	}
</style>