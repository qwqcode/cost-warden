<script>
import Login from "./Login.svelte";

export let name;
</script>

<main>
	<div class="app">
		<div class="brand"></div>
		<div class="body">
			<Login />
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
		height: 90vh;
		background: #fff;
	}

	.brand {
		display: flex;
		flex-basis: 30%;
		position: relative;
		background: linear-gradient(180deg, #008DDD 0%, #00A3FF 100%);
		height: 100%;
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