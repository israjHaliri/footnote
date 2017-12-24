import Vue from 'vue'
import Router from 'vue-router'

import Full from '@/containers/Full'

import Login from '@/components/Login'
import Dashboard from '@/components/Dashboard'
import Profile from '@/components/Profile'
import Item from '@/components/Item'
import NotFound from '@/components/404'

Vue.use(Router)
export default new Router({
	mode: 'history',
	linkActiveClass: 'open active',
	routes: [
	{
		path: '/',
		name: 'Login',
		component: Login
	},
	{ path: '/404', component: NotFound },  
	{ path: '*', redirect: '/404' }, 
	{
		path: '/',
		name: 'Backend',
		component: Full,
		children : 
		[
		{
			path: '/dashboard',
			name: 'Dashboard',
			component: Dashboard
		},{
			path: '/profile',
			name: 'Profile',
			component: Profile
		}
		]
	}
	]
})