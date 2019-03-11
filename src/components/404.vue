<template class="app flex-row align-items-center">
	<div class="container">
        <div class="row justify-content-center">
            <div class="not-found col-md-5">
                <div class="clearfix">
                    <h1 class="float-left display-3 mr-4"><b>4</b><i>0</i><b>4</b></h1>
                    <h4 class="pt-3">Oops! You're lost.</h4>
                    <p style="color:rgba(152, 104, 35, 0.75) !important;" class="text-muted">
                    The page you are looking for was not found.
                	</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
	export default {
		contentData () {
			return {
			}
		}
	}
</script>

<style scoped>
	.not-found{
    margin: 20% 0px auto;
	}
</style>
