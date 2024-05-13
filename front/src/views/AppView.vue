<template>
    <div v-if="this.$store.state.isLoggin">
        <Mobile v-if="platform" />
    </div>

    <div v-else>
        <Login />
    </div>
</template>

<script>
    import Mobile from '../Mobile/Mobile.vue'
    import Login from '@/Mobile/Login/Login.vue'

    export default {
        name:"AppViewVue",
        components:{
            Mobile,Login
        },
        data(){
            return{
                platform:true,
               
            }
        },
        
        mounted(){
            if(localStorage.getItem('data')){
                this.$store.state.isLoggin = true
            } else {
                this.$store.state.isLoggin = false
            }

            /* if(screen.width >= 900){
                this.platform = false
                console.log(screen.width)
            }else{
                this.platform = true
            } */
        }
    }
</script>