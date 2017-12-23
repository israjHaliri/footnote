<template>
	<div class="container-fluid">
		<div class="animated fadeIn">
			<div class="row">
				<GuestBookChart :width="1000" :height="475"></GuestBookChart>
			</div>
		</div>
	</div>
</template>    

<script>
import GuestBookChart from '@/components/dashboard/GuestBookChart'

export default {
	components: {
		GuestBookChart,
	}
}
</script>

<style scoped>

</style>
