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
								<th>SUBJECT</th>
								<th>DESCRIPTION</th>
								<th>DATE</th>
								<th>ACTION</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(val, index) in data.testimonial">
								<td>{{ val.rn }}</td>
								<td>{{ val.subject }}</td>
								<td>{{ val.description }}</td>
								<td>{{ val.createDate }}</td>
								<td>
									<button class="btn btn-danger" v-on:click="deleteData(val.idTestimonial)">
										<i class="fa fa-remove"></i> Delete
									</button>
								</td>
							</tr>
							<tr v-if="data.testimonial.length == 0">
								<td align="center" colspan="5">No Data</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-12">
					<Pagination  :pagination="data"
									 @paginate="getData()"
									 :offset=offset>
					</Pagination>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Search from '@/components/helper/pagination/Search'
import Pagination from '@/components/helper/pagination/Paginate'

export default {
	data(){
		return {
			search_keyword : "",
			data: {
				total: 0,
				per_page: 0,
				last_page: 0,
				from: 1,
				to: 5,
				current_page: 1,
				testimonial : ""
			},
			offset: 4,
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
			this.data.current_page = 1;
			this.search_keyword = keywords;
			this.getData();
		},
		getData(keywords){
			this.$axios.request({
				url:'/secret/get/testimonial',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
				params: {
					'search' : this.search_keyword,
					'start' : this.data.current_page,
				},
			})
			.then( response => {
				this.data.testimonial = response.data.contentData.testimonial;
				this.data.total = response.data.contentData.totalData;
				this.data.per_page = response.data.contentData.perPage;
				this.data.last_page = Math.ceil(this.data.total / this.data.per_page);
			})
		},deleteData(id){
			this.$axios.request({
				url:'/secret/delete/testimonial/'+id+'',
				method:'DELETE',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				},
			})
			.then( response => {
				this.getData();
			})
		},
		changePage (pageNum) {
			this.data.current_page = pageNum;
			this.getData();
		}
	}
}
</script>

<style scoped>
</style>
