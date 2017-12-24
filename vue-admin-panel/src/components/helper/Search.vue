<template>
	<div class="input-group" style="margin-bottom:15px;">
		<span class="input-group-addon" id="search"><i class="fa fa-search"></i></span>
		<input type="text" class="form-control" placeholder="Keywords ..." aria-describedby="search" v-model="searchKeyword" @change="search">
	</div>
</template>    

<script>
export default {

}
</script>

<style scoped>

</style>
