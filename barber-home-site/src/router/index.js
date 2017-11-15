import Vue from 'vue'
import Router from 'vue-router'

import Full from '@/containers/Full'

import Login from '@/components/Login'
import Dashboard from '@/components/Category'
import Category from '@/components/Dashboard'
import NotFound from '@/components/404'


Vue.use(Router)

export default new Router({
	mode: 'history',
	linkActiveClass: 'open active',
	routes: [
	{
		path: '/',
		name: 'login',
		component: Login
	},
	{ path: '/404', component: NotFound },  
  	{ path: '*', redirect: '/404' }, 
	{
		path: '/',
		name: 'backend',
		component: Full,
		children : 
		[
			{
				path: '/dashboard',
				name: 'dashboard',
				component: Dashboard
			},{
				path: '/category',
				name: 'category',
				component: Category
			}
		]
	}
	]
})