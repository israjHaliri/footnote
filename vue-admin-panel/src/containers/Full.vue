<template>
	<div id="full">
		<header-bar></header-bar>
		<div class="app-body">
			<menu-bar @menuSelected="onSelectNav"></menu-bar>
			<main class="main" style="min-height: 550px;">				
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Home</li>
					<li class="breadcrumb-item"><a href="#">Admin</a>
					</li>
					<li class="breadcrumb-item active">{{newLabelNav}}</li>
				</ol>
				<router-view></router-view>
			</main>
		</div>
		<footer-bar></footer-bar>
	</div>
</template>

<script>
import Header from '@/components/layout/Header.vue'
import Footer from '@/components/layout/Footer.vue'
import Menu from '@/components/layout/Menu.vue'
export default {
	name: 'full',
	data: function(){
		return {
			newLabelNav : "Dashboard",
		}
	},
	components: {
		'header-bar': Header,
		'footer-bar': Footer,
		'menu-bar': Menu,
		'history-bar': History
	},
	methods: {
		onSelectNav (menuSelected) {
			this.newLabelNav = menuSelected
		}
	}
}
</script>

<style scoped>
</style>
