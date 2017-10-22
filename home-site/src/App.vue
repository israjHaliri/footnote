
<template>
	<div v-if="show" id="app">
		<header-bar></header-bar>
		<div class="app-body">
			<menu-bar></menu-bar>
			<main class="main">
				<history-bar></history-bar>
				<router-view></router-view>
			</main>
		</div>
		<footer-bar></footer-bar>
	</div>
	<div v-else id="app">
		<router-view></router-view>
	</div>
</template>

<script>
	import Header from '@/components/shared/Header.vue'
	import Footer from '@/components/shared/Footer.vue'
	import Menu from '@/components/shared/Menu.vue'
	import History from '@/components/shared/History.vue'
	export default {
		name: 'app',
		data: function(){
			return {
				show : false,
				pathCss : "../static/assets/css/style.css"
			}
		},
		components: {
			'header-bar': Header,
			'footer-bar': Footer,
			'menu-bar': Menu,
			'history-bar': History
		},
		created() {

			if (this.$route.path == "/404" || this.$route.path == "/") {
				this.show = false
			} else{
				this.show =	true	
			}
			
		}
	}
</script>

<style scoped>
</style>
