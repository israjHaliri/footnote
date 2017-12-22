<template>
	<div id="full">
		<header-bar></header-bar>
		<div class="app-body">
			<menu-bar></menu-bar>
			<main class="main" style="min-height: 550px;">
				<history-bar></history-bar>
				<router-view></router-view>
			</main>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
	import Header from '@/components/shared/Header.vue'
	import Footer from '@/components/shared/Footer.vue'
	import Menu from '@/components/shared/Menu.vue'
	import History from '@/components/shared/History.vue'
	export default {
		name: 'full',
		data: function(){
			return {
			}
		},
		components: {
			'header-bar': Header,
			'footer-bar': Footer,
			'menu-bar': Menu,
			'history-bar': History
		}
	}
</script>

<style scoped>
</style>
