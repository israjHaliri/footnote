<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<div class="modal-dialog">
					<div class="loginmodal-container">
						<h1><b><small>Login to Your </small></b><i>Account</i></h1><br>
						<input type="text" name="user" placeholder="Username">
						<input type="password" name="pass" placeholder="Password">
						<button class="btn btn-primary" style="width: 100%; height: 50px;" v-on:click="auth">
							<b>Login</b>
						</button>
						<div class="login-help">
							<p><i>israjHaliri.com</i></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from 'axios';
	import querystring from 'querystring';

	export default {
		data () {
			return {

			}
		},
		methods:{
			auth(){
				axios.post('http://localhost:9393/auth', querystring.stringify({
					'username': 'JONO',
					'password': '123'
				}))
				.then((response) => {
					this.$router.push({ name: 'Dashboard' })
				})
				.catch((error) => {
					alert("Failed To Login "+error);
				});
			}
		}
	}
</script>

<style scoped>
@import url(http://fonts.googleapis.com/css?family=Roboto);

.loginmodal-container {
	padding: 30px;
	max-width: 350px;
	width: 100% !important;
	background-color: #F7F7F7;
	margin: 30% 0px auto;
	border-radius: 2px;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	overflow: hidden;
	font-family: roboto;
}

.loginmodal-container h1 {
	text-align: center;
	font-size: 1.8em;
	font-family: roboto;
}

.loginmodal-container input[type=submit] {
	width: 100%;
	display: block;
	margin-bottom: 10px;
	position: relative;
}

.loginmodal-container input[type=text], input[type=password] {
	height: 44px;
	font-size: 16px;
	width: 100%;
	margin-bottom: 10px;
	-webkit-appearance: none;
	background: #fff;
	border: 1px solid #d9d9d9;
	border-top: 1px solid #c0c0c0;
	/* border-radius: 2px; */
	padding: 0 8px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
}

.loginmodal-container input[type=text]:hover, input[type=password]:hover {
	border: 1px solid #b9b9b9;
	border-top: 1px solid #a0a0a0;
	-moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
	-webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
	box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}

.loginmodal {
	text-align: center;
	font-size: 14px;
	font-family: 'Arial', sans-serif;
	font-weight: 700;
	height: 36px;
	padding: 0 8px;
	/* border-radius: 3px; */
/* -webkit-user-select: none;
user-select: none; */
}

.loginmodal-submit {
	/* border: 1px solid #3079ed; */
	border: 0px;
	color: #fff;
	text-shadow: 0 1px rgba(0,0,0,0.1); 
	background-color: #4d90fe;
	padding: 17px 0px;
	font-family: roboto;
	font-size: 14px;
	/* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.loginmodal-submit:hover {
	/* border: 1px solid #2f5bb7; */
	border: 0px;
	text-shadow: 0 1px rgba(0,0,0,0.3);
	background-color: #357ae8;
	/* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.loginmodal-container a {
	text-decoration: none;
	color: #666;
	font-weight: 400;
	text-align: center;
	display: inline-block;
	opacity: 0.6;
	transition: opacity ease 0.5s;
} 

.login-help{
	font-size: 12px;
}
</style>
