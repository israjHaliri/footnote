<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="form-group">
				<label for="address">Title:</label>
				<input type="text" class="form-control" v-model="title" id="title">
			</div>
			<div class="form-group">
				<label for="description">Description:</label>
				<textarea v-model="description" class="form-control"></textarea>
			</div>
			<div class="form-group">
				<label for="contentType">Content Type:</label>
				<select class="form-control" v-model="contentType">
					<option value="ARTICLE">ARTICLE</option>
					<option value="PRODUCT">PRODUCT</option>
				</select>
			</div>
			<div class="form-group">
				<label for="information">Information:</label>
				<input type="text" class="form-control" v-model="information" id="information">
			</div>
			<div align="right">
				<button class="btn btn-success" v-on:click="insertData">Submit</button>
			</div>
		</div>
	</div>
</template>    

<script>
export default {
	data () {
		return {
			idContent : "",
			title : "",
			description : "",
			createDate : "",
			updateDate : "",
			createBy : "",
			updateBy : "",
			contentType : "",
			information : "",
			rn : "",
			total_count : "",
		}
	},
	methods:{
		insertData(){
			this.$axios({
				url:'/secret/insert/content',
				method:'POST',
				headers : {
					"Content-Type" : "application/json",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
				data : 
				{
					idContent : this.idContent,
					title : this.title,
					description : this.description,
					createDate : this.createDate,
					updateDate : this.updateDate,
					createBy : this.createBy,
					updateBy : this.updateBy,
					contentType : this.contentType,
					information : this.information,
					rn : this.rn,
					total_count : this.total_count,
				}
			})
			.then( response => {
				console.log(response);
				alert(response.data.message);
				if(response.data.status ==  200){
					this.idContent = "";
					this.title = "";
					this.description = "";
					this.createDate = "";
					this.updateDate = "";
					this.createBy = "";
					this.updateBy = "";
					this.contentType = "";
					this.information = "";
					this.rn = "";
					this.total_count = "";
				}
				
			})
		}
	}
}
</script>

<style scoped>

</style>
