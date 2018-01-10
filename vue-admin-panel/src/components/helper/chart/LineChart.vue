<script>
import {Line} from 'vue-chartjs'
export default { 
	extends: Line,
	props: ['data','label'],
	data () {
		return {
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
		this.drawChart(this.data,this.label);
	},
	methods: {
		drawChart (data,label) {
			this.datacollection = {
				labels: label,
				datasets: [
				{
					label: 'Data Guest',
					pointBackgroundColor: 'white',
					borderWidth: 1,
					pointBorderColor: '#f87979',
					data: data
				}
				]
			}
			this.renderChart(this.datacollection, this.options);
		}
	}
}	
</script>