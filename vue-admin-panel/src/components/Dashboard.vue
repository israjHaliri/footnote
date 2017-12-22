<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<GuestBookLineChart :width="1000" :height="475"></GuestBookLineChart>
			</div>
		</div>
	</div>
</template>    

<script>
import GuestBookLineChart from '@/components/partial/GuestBookLineChart'

export default {
	components: {
		GuestBookLineChart,
	}
}
</script>

<style scoped>

</style>
