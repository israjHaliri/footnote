import Vue from 'vue'
import Router from 'vue-router'

import Full from '@/containers/Full'

import Login from '@/components/Login'
import Dashboard from '@/components/dashboard/ContainerChart'
import Profile from '@/components/Profile'
import Testimonial from '@/components/Testimonial'
import ListItem from '@/components/item/ListItem'
import NewItem from '@/components/item/NewItem'
import Attachment from '@/components/item/Attachment'
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
		},{
			path: '/testimonial',
			name: 'Testimonial',
			component: Testimonial
		},{
			path: '/item/list',
			name: 'ListItem',
			component: ListItem
		},{
			path: '/item/new',
			name: 'NewItem',
			component: NewItem
		},{
			path: '/item/attachment',
			name: 'Attachment',
			component: Attachment
		}
		]
	}
	]
})