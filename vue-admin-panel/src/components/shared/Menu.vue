<template>
    <div class="sidebar">
        <nav class="sidebar-nav">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><i class="icon-speedometer"></i> Dashboard</a>
                </li>
                <li class="divider"></li>
                <li class="nav-title">
                    Navigator
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><i class="icon-pencil"></i> Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><i class="icon-pencil"></i> GuestBook</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><i class="icon-pencil"></i> Testimonial</a>
                </li>
                <router-link tag="li" class="nav-item nav-dropdown" :to="{ path: '/menu'}" disabled>
                    <div class="nav-link nav-dropdown-toggle" @click="handleClickParent">
                        <i class="icon-note"></i> Content
                    </div>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <router-link :to="'/category'" class="nav-link" style="padding-left : 30px;">
                                <i class="icon-pencil"></i> New
                            </router-link>
                        </li>
                        <li class="nav-item">
                            <router-link :to="'/dashboard'" class="nav-link" style="padding-left : 30px;">
                                <i class="icon-pencil"></i> List
                            </router-link>
                        </li>
                    </ul>
                </router-link>
            </ul>
        </nav>
    </div>
</template>
<script>
    export default {
        data () {
            return {

            }
        },
        methods: {
            handleClickParent (e) {
                e.preventDefault()
                e.target.parentElement.classList.toggle('open')
            }
        },
    }
</script>

<style scoped>

</style>
