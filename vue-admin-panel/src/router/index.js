import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios';
import querystring from 'querystring';
import base64 from 'js-base64'

import Full from '@/containers/Full'

import Login from '@/components/Login'
import Dashboard from '@/components/Dashboard'
import Category from '@/components/Category'
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
			path: '/profile',
			name: 'category',
			component: Category
		}
		]
	}
	]
})

var instanceAxios = axios.create({
	baseURL: 'http://localhost:9393'
});
instanceAxios.defaults.timeout = 2500;
instanceAxios.interceptors.response.use(function (response) {
	return response;
}, function (error) {
	return Promise.reject(error);
});

Vue.prototype.$axios = instanceAxios
Vue.prototype.$querystring = querystring
Vue.prototype.$base64 = base64