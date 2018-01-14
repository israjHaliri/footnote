<template>
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="file">File:</label>
                        <vue-base64-file-upload input-class="v1-image" class="v1" accept="image/png,image/jpeg"
                                                :disable-preview="true" @file="onFile" @load="onLoad"/>
                    </div>
                </div>
                <div class="col-md-12">
                    <table style="background-color:white" class="table table-responsive table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>NO</th>
                            <th>NAME FILE</th>
                            <th>FILE</th>
                            <th width="10%">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!--<p>{{exGetter[0]}}</p>-->
                        <tr v-for="val in exGetter">
                            <td>{{ val.rn }}</td>
                            <td>{{ val.nameFile }}</td>
                            <td><img :src="'data:image/jpeg;base64,'+val.file" alt=""></td>
                            <td align="center">
                                <!--<button class="btn btn-danger" v-on:click="ex(4)">-->
                                <button class="btn btn-danger" v-on:click="deleteData(val.idAttachment)">
                                    Delete
                                </button>
                            </td>
                        </tr>
                        <tr v-if="exGetter.length == 0">
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
    import VueBase64FileUpload from 'vue-base64-file-upload';

    export default {
        components: {
            VueBase64FileUpload
        },
        props: ['id', 'type'],
        computed: {
//            attachments (){
//                console.log("attachemnt : ", this.$store.state.attachments);
//                return this.$store.state.attachments
//            },
//            exGetter(){
//                return this.$store.getters.exGetter
//            }
            ...mapGetters([
                'exGetter'
            ])
        },
        mounted () {
            this.getData();
        },
        methods: {
//            ex(data){
//                this.$store.dispatch('exAction',data);
//            }
            ...mapActions([
                'exAction'
            ]), onFile(file) {
                console.log(file);
            }, onLoad(dataUri) {
                var file = dataUri.replace(/^data:image\/\w+;base64,/, "");
                console.log(file);
                this.$axios({
                    url: '/secret/insert/attachment',
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        "Authorization": "Bearer " + localStorage.getItem("VueAdminPanelToken")
                    },
                    data: this.$querystring.stringify({
                        contentId: this.id,
                        contentType: this.type,
                        file: file
                    })
                }).then(response => {
                    console.log(response);
                    alert(response.data.message);
                    if (response.data.status == 200) {
                        this.getData();
                    }

                })
            }, getData(){
                this.$axios.request({
                    url: '/secret/get/attachment',
                    method: 'GET',
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Authorization": "Bearer " + localStorage.getItem("VueAdminPanelToken")
                    },
                    params: {
                        'search': "",
                        'start': 1,
                        'type': this.type
                    },
                }).then(response => {
                    this.exAction(response.data.contentData.attachments)
                })
            }, deleteData(idAttachment){
                this.$axios.request({
                    url: '/secret/delete/attachment/' + idAttachment + '/' + this.id + '/' + this.type + '',
                    method: 'DELETE',
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "Authorization": "Bearer " + localStorage.getItem("VueAdminPanelToken")
                    },
                }).then(response => {
                    this.getData();
                })
            }
        }
    }
</script>

<style>
    .v1-image {
        padding: 10px !important;
    }
</style>
