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
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						},
						gridLines: {
							display: true
						}
					}],
					xAxes: [ {
						gridLines: {
							display: false
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
				url:'/secret/barber/get/guest_book_one_month',
				method:'GET',
				headers : {
					"Content-Type" : "application/x-www-form-urlencoded",
					"Authorization" : "Bearer "+localStorage.getItem("LoginBarberShopToken")
				}
			})
			.then( response => {

				for (var i = 0; i < response.data.data.length; i++) {
					console.log("response : ",response);
					this.listData.push(response.data.data[i].totalCount)
					this.listLabel.push(response.data.data[i].createDate)
				}

				this.datacollection = {
					labels: this.listLabel,
					datasets: [
					{
						label: 'Data Guest',
						backgroundColor: '#f87979',
						pointBackgroundColor: 'white',
						borderWidth: 1,
						pointBorderColor: '#249EBF',
						data: this.listData
					}
					]
				}
				this.renderChart(this.datacollection, this.options);
			})
			.catch( error => {
				console.log("error : ",error);
				if(error.response.data.status == 401 || error.response.data.status == 403){
					this.$router.push("/");
				}
				alert(error.response.data.message);
			});
		}
	}
}	
</script>