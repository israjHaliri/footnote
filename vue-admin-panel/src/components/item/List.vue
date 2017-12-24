<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<div class="col-md-12">
					<Search></Search>
				</div>
				<div class="col-md-12">
					<table style="background-color:white" class="table table-responsive table-bordered table-hover">
						<thead>
							<tr>
								<th>TITLE</th>
								<th>DESCRIPTION</th>
								<th>TYPE</th>
								<th>INFORMATON</th>
								<th>ACTION</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(val, index) in listData">
								<td>{{ val.title }}</td>
								<td>{{ val.description }}</td>
								<td>{{ val.type }}</td>
								<td>{{ val.information }}</td>
								<td>{{ val.information }}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</template>    

<script>
import Search from '@/components/helper/Search'

export default {
	data(){
		return {
			listData : [],
			searchKeyword : "",
		}
	},
	components: {
		Search,
	},
	mounted () {
		this.getData();
	},
	methods:{
		getData(){
			this.$axios.request({
				url:'/secret/get/content',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
				params: {
					type: 'ARTICLE',
				},
			})
			.then( response => {
				this.listData = response.data.data.items;
			})
		},
		search: function(e) {
            console.log(e);
        }
	}
}
</script>

<style scoped>

</style>
