<template>
    <header class="app-header navbar">
        <button class="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button">☰</button>
        <a class="navbar-brand" href="#"></a>
        <ul class="nav navbar-nav d-md-down-none">
            <li class="nav-item">
                <a class="nav-link navbar-toggler sidebar-toggler" href="#">☰</a>
            </li>
        </ul>
        <ul class="nav navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle nav-link" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" style="padding-right: 15px;">
                    <img src="/static/assets/img/service-icon-2.png" class="img-avatar" alt="No Image">
                    <span class="d-md-down-none">admin</span>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <div class="dropdown-header text-center">
                        <strong>Account</strong>
                    </div>
                    <a class="dropdown-item" href="#"><i class="fa fa-user"></i> Profile</a>
                    <div class="divider"></div>
                    <a class="dropdown-item" href="#" v-on:click="logout"><i class="fa fa-lock"></i> Logout</a>
                </div>
            </li>

        </ul>
    </header>
</template>
<script>

import axios from 'axios';

export default {
    data () {
        return {
        }
    },
    methods:{
        logout(){
            this.$axios({
                url:'http://localhost:9393/logout',
                method:'POST',
                headers : {
                   "Content-Type" : "application/x-www-form-urlencoded",
                   "Authorization" : "Bearer "+ localStorage.getItem("VueAdminPanelToken")
               }
           })
            .then( response => {
                console.log("response : ",response);
                localStorage.setItem('VueAdminPanelToken', "")
                this.$router.push("/")
            })
            .catch( error => {
                alert(error.response.data.message);
                this.$router.push("/")
            });
        }
    }
}
</script>

<style scoped>

</style>
