import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Dashboard from '@/components/Dashboard'
import NotFound from '@/components/404'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [
	{
		path: '/',
		name: 'Login',
		component: Login
	},
	{
		path: '/page/dashboard',
		name: 'Dashboard',
		component: Dashboard
	},
	{ path: '/404', component: NotFound },  
  	{ path: '*', redirect: '/404' }, 
	]
})
