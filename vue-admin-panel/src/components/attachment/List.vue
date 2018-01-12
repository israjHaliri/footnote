<template>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <table style="background-color:white" class="table table-responsive table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>NO</th>
                            <th>FILE</th>
                            <th width="10%">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr  v-for="val in exGetter">
                            <td>{{ val.rn }}</td>
                            <td>{{ val.file }}</td>
                            <td align="center">
                                <!--<button class="btn btn-danger" v-on:click="ex(4)">-->
                                <button class="btn btn-danger" v-on:click="exAction">
                                    Load
                                </button>
                            </td>
                        </tr>
                        <tr v-if="attachments.length == 0">
                            <td align="center" colspan="5">No Data</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    import {mapGetters} from 'vuex';
    export default {
        computed : {
            attachments (){
                console.log("attachemnt : ",this.$store.state.attachments);
                return this.$store.state.attachments
            },
//            exGetter(){
//                return this.$store.getters.exGetter
//            }
                ...mapGetters([
                        'exGetter'
                ])
        },
        methods : {
//            ex(data){
//                this.$store.dispatch('exAction',data);
//            }
                ...mapActions([
                        'exAction'
                ])
        }
    }
</script>

<style scoped>
</style>
