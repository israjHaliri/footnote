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
							<th>NO</th>
							<th>TITLE</th>
							<th>DESCRIPTION</th>
							<th>TYPE</th>
							<th>ACTION</th>
						</tr>
						</thead>
						<tbody>
						<tr  v-for="(val, index) in data.item">
							<td>{{ val.rn }}</td>
							<td>{{ val.title }}</td>
							<td>{{ val.description }}</td>
							<td>{{ val.contentType }}</td>
							<td>
								<button class="btn btn-danger" v-on:click="deleteData(val.idItem)">
								    <i class="fa fa-remove"></i> Delete
							    </button>
                                <button class="btn btn-info" v-on:click="updateData(val.idItem, val.contentType)">
								    <i class="fa fa-edit"></i> Update
							    </button>
                            </td>
						</tr>
						<tr v-if="data.item.length == 0">
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
					item : []
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
					url:'/secret/get/item',
					method:'GET',
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded",
						"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
					},
					params: {
						'search' : this.searchKeyword,
						'start' : this.data.current_page,
					},
				})
						.then( response => {
							this.data.item = response.data.contentData.item;
							this.data.total = response.data.contentData.totalData;
							this.data.per_page = response.data.contentData.perPage;
							this.data.last_page = Math.ceil(this.data.total / this.data.per_page);
						})
			},deleteData(id){
                this.$axios.request({
                    url:'/secret/delete/item/'+id+'',
                    method:'DELETE',
                    headers : {
                        "Content-Type" : "application/x-www-form-urlencoded",
                        "Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
                    },
                })
                .then( response => {
                    this.data.current_page = 1;
                    this.getData();
                })
            },updateData(id,type){
                    this.$router.push("/item/update/"+id+"/"+type+"")
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
