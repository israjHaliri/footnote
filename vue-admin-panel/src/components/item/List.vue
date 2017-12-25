<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<div class="col-md-12">
					<Search @searching="searchData"></Search>
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
				<div class="col-md-12">
					<pagination :current-page="page.currentPage" :total-pages="page.totalPages" @page-changed="changePage">
					</pagination>
				</div>
			</div>
		</div>
	</div>
</template>    

<script>
import Search from '@/components/helper/Search'
import Pagination from '@/components/helper/Paginate'

export default {
	data(){
		return {
			listData : [],
			searchKeyword : "",
			page: {
				currentPage: 1,
				totalPages: 10
			}
		}
	},
	components: {
		Search,
		Pagination,
	},
	mounted () {
		this.getData("");
	},
	methods:{
		searchData (keywords) {
			this.page.currentPage = 1
			this.searchKeyword = keywords;
			this.getData();
		},
		getData(keywords){
			this.$axios.request({
				url:'/secret/get/content',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
				params: {
					'type' : 'ARTICLE',
					'search[value]' : this.searchKeyword,
					'start' : this.page.currentPage,
				},
			})
			.then( response => {
				this.listData = response.data.data.items;
			})
		},
		changePage (pageNum) {
			this.page.currentPage = pageNum
			this.getData();
		}
	}
}
</script>

<style scoped>

</style>
