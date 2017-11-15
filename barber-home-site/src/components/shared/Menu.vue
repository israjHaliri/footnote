<template>
    <div class="sidebar">
        <nav class="sidebar-nav">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.html"><i class="icon-speedometer"></i> Dashboard</a>
                </li>

                <li class="nav-title">
                    Contents
                </li>
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-puzzle"></i> Components</a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <a class="nav-link" href="components-buttons.html"><i class="icon-puzzle"></i> New</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="components-social-buttons.html"><i class="icon-puzzle"></i> List</a>
                        </li>
                    </ul>
                </li>
                <li class="divider"></li>
                <li class="nav-title">
                    Master
                </li>
                <router-link tag="li" class="nav-item nav-dropdown" :to="{ path: '/menu'}" disabled>
                    <div class="nav-link nav-dropdown-toggle" @click="handleClickParent">
                        <i class="icon-people"></i> User
                    </div>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <router-link :to="'/category'" class="nav-link">
                                <i class="icon-note"></i> New
                            </router-link>
                        </li>
                        <li class="nav-item">
                            <router-link :to="'/dashboard'" class="nav-link">
                                <i class="icon-note"></i> List
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
