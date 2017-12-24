<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="form-group">
				<label for="address">Address:</label>
				<input type="text" class="form-control" v-model="address" id="address">
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<input type="email" class="form-control" v-model="email" id="email">
			</div>
			<div class="form-group">
				<label for="lat">Latitude:</label>
				<input type="text" class="form-control" v-model="lat" id="lat">
			</div>
			<div class="form-group">
				<label for="lon">Longitude:</label>
				<input type="text" class="form-control" v-model="lon" id="lon">
			</div>
			<div class="form-group">
				<label for="phone">Phone:</label>
				<input type="text" class="form-control" v-model="phone" id="phone">
			</div>
			<div align="right">
				<button class="btn btn-success" v-on:click="updateData">Submit</button>
			</div>
		</div>
	</div>
</template>    

<script>
export default {
	data () {
		return {
			address : "",
			email : "",
			lat : "",
			lon : "",
			phone : "",
			createDate : "",
			updateDate : "",
		}
	},
	mounted () {
		this.getData();
	},
	methods:{
		getData(){

			this.$axios({
				url:'/secret/get/profile',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				}
			})
			.then( response => {
				console.log(response);
				this.address = response.data.data.address;
				this.email = response.data.data.email;
				this.lat = response.data.data.lat;
				this.lon = response.data.data.lon;
				this.phone = response.data.data.phone;
				this.createDate = response.data.data.createDate;
				this.updateDate = response.data.data.updateDate;
			})
		},updateData(){
			this.$axios({
				url:'/secret/update/profile',
				method:'PUT',
				headers : {
					"Content-Type" : "application/json",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
				data : 
				{
					address : this.address,
					email : this.email,
					lat : this.lat,
					lon : this.lon,
					phone : this.phone,
					createDate : this.createDate,
				}
			})
			.then( response => {
				console.log(response);
				alert(response.data.message);
				if(response.data.status ==  200){
					this.getData();
				}
				
			})
		}
	}
}
</script>

<style scoped>

</style>
