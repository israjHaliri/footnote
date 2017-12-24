<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import Vue from 'vue'
import Router from './router'
import Axios from 'axios';
import Querystring from 'querystring';
import Base64 from 'js-base64'

let axios = Axios.create({
	baseURL: 'http://localhost:9393'
});

axios.defaults.timeout = 2500;
axios.interceptors.response.use(function (response) {
	if(response.data.status == 420){
		alert(response.data.message)
		return response;	
	}else{
		return response;	
	}
}, function (error) {
	if(typeof error.response == "undefined"){
		alert(error)
	}
	else{
		if(error.response.data.status == 404){
			alert(error);
			return Promise.reject("Service Not Found");
		}else if(error.response.data.status == 401 || error.response.data.status == 403){
			Router.push("/")
			return Promise.reject("Access Denied");
		}else{
			return Promise.reject(error);
		}
		
	}
});

Vue.prototype.$axios = axios;
Vue.prototype.$querystring = Querystring;
Vue.prototype.$base64 = Base64;

export default {
}
</script>

<style>
</style>
