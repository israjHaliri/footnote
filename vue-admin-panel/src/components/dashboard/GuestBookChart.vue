<script>
import {Line} from 'vue-chartjs'
export default { 
	extends: Line,
	data () {
		return {
			listData: [],
			listLabel: [],
			datacollection: {},
			options: {
				type: 'line',
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true,
							reverse: false
						},
						gridLines: {
							display: true
						}
					}],
					xAxes: [ {
						gridLines: {
							display: true
						}
					}]
				},
				legend: {
					display: true
				},
				responsive: true,
				maintainAspectRatio: false
			}
		}
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
					this.listData.push(response.data.data[i].totalCount)
					this.listLabel.push(response.data.data[i].createDate)
				}

				this.datacollection = {
					labels: this.listLabel,
					datasets: [
					{
						label: 'Data Guest',
						pointBackgroundColor: 'white',
						borderWidth: 1,
						pointBorderColor: '#f87979',
						data: this.listData
					}
					]
				}
				this.renderChart(this.datacollection, this.options);
			})
		}
	}
}	
</script>