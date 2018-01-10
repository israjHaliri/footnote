import Vue from 'vue'
import Router from 'vue-router'

import Full from '@/containers/Full'

import Login from '@/components/Login'
import GuesbookDashboard from '@/components/guestbook/Dashboard'
import ProfileUpdate from '@/components/profile/Update'
import TestimonialList from '@/components/testimonial/List'
import ItemForm from '@/components/item/Form'
import ItemList from '@/components/item/List'
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
			path: '/guestbook/dashboard',
			name: 'GuestbookDashboard',
			component: GuesbookDashboard
		},{
			path: '/profile/update',
			name: 'ProfileUpdate',
			component: ProfileUpdate
		},{
			path: '/testimonial/list',
			name: 'TestimonialList',
			component: TestimonialList
		},{
			path: '/item/new',
			name: 'ItemNew',
			component: ItemForm
		},{
			path: '/item/update/:id/:type',
			props: true,
			name: 'ItemUpdate',
			component: ItemForm
		},{
			path: '/item/list',
			name: 'ItemList',
			component: ItemList
		}
		]
	}
	]
})
