<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<table>
					<thead>
						<tr>
							<th v-for="key in columns">
								{{key | capitalize}}
							</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="entry in data">
							<td v-for="key in columns">
								{{entry[key]}}
							</td>
						</tr>
					</tbody>
				</table>
				<div id="grid-view-model">
					<form id="search">
						Search
						<input name="query" v-model="searchQuery">
					</form>
					<!-- <grid :data="gridData | orderByBusinessRules | filterBy searchQuery | limitBy rowsPerPage startRow" :columns="gridColumns"> -->
					</grid>
					<div id="page-navigation">
						<button @click=pageMoves(-1)>Back</button>
						<p>{{startRow / rowsPerPage + 1}} out of {{gridData.length / rowsPerPage}}</p>
						<button @click=pageMoves(1)>Next</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>    

<script>
import Vue from 'vue'
// Make a list of 100 of random dummy data
var baseData = [{
	name: 'w2way com',
	power: Infinity
}, {
	name: 'w3free com',
	power: 9000
}, {
	name: 'ng4free com',
	power: 7000
}, {
	name: 'Jet Li',
	power: 8000
}];

// looping through data display using vuejs
var gridData = Array(250).fill(null).map(function() {
	return Object.assign({}, baseData[Math.floor(Math.random() * 4)]);
});

// here id or uniq register the grid simple component
Vue.component('grid', {
	template: '#grid-template',
	props: {
		data: Array,
		columns: Array
	}
});

// simple Create the view-model in vuejs
var gridViewModel = new Vue({
	el: '#grid-view-model',
	data: {
		searchQuery: '',
		gridColumns: ['name', 'power'],
		gridData: gridData,
		startRow: 0,
		rowsPerPage: 10
	},
	methods: {
		pageMoves: function(amount) {
			var startrownew = this.startRow + (amount * this.rowsPerPage);
			if (startrownew >= 0 && startrownew < gridData.length) {
				this.startRow = startrownew;
			}
		}
	},
	filters: {
		orderByBusinessRules: function(data) {
			return data.slice().sort(function(a, b) {
				return a.power - b.power;
			});
		}
	}
})


export default {

}
</script>

<style scoped>

</style>
