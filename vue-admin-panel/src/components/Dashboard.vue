<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<LineChart v-if="listLineData.length > 0" :data='listLineData' :label='listLineLabel' :width="1000" :height="475"></LineChart>
			</div>
		</div>
	</div>
</template>    

<script>
import LineChart from '@/components/chart/LineChart'

export default {
	data () {
		return {
			listLineData: [],
			listLineLabel: [],
		}
	},
	components: {
		LineChart,
	},
	mounted () {
		this.getData();
	},
	methods:{
		getData(){
			this.$axios({
				url:'/secret/get/guest_book_one_month',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
				}
			})
			.then( response => {
				for (var i = 0; i < response.data.data.length; i++) {
					this.listLineData.push(response.data.data[i].totalCount)
					this.listLineLabel.push(response.data.data[i].createDate)
				}
			})
		}
	}
}
</script>

<style scoped>

</style>
