<template>
    <div class="mobile">
      <header class="mobile-header">G1 DO CURSO</header>
      <Home v-if="$store.state.homeView === 'home'" />
      <Notify v-if="$store.state.homeView === 'notify'" />
      <Chat v-if="$store.state.homeView === 'chat'" />
      <Profile v-if="$store.state.homeView === 'profile'" />
      <Post v-if="$store.state.homeView === 'post'" />
  
      <nav class="footer-nav">
        <i class="fa-solid fa-house" :id="$store.state.homeView === 'home' ? 'activity' : ''" @click="setView('home')"></i>
        <i class="fa-solid fa-bell" :id="$store.state.homeView === 'notify' ? 'activity' : ''" @click="setView('notify')"></i>
        <i class="fa-solid fa-plus" :id="$store.state.homeView === 'post' ? 'activity' : ''" @click="setView('post')"></i>
        <i class="fa-solid fa-comment" :id="$store.state.homeView === 'post' ? 'activity' : ''" @click="setView('chat')"></i>
        <i class="fa-regular fa-user" :id="$store.state.homeView === 'post' ? 'activity' : ''" @click="setView('profile')"></i>
        
        
       
      </nav>
    </div>
  </template>

<script>
    import Home from "@/Mobile/Home/Home.vue"
    import Chat from "@/Mobile/Chats/Chat.vue"
    import Profile from "@/Mobile/Profile/Profile.vue"
    import Notify from "@/Mobile/Notify/Notify.vue"
    import Post from '@/Mobile/Post/Post.vue'
    export default{
        name:"MobileVue",
        components:{
            Home,Notify,Chat,Profile,Post
        },
        methods:{
            setView(route){
                this.$store.state.homeView = route
            }
        }
    }
</script>

<style scoped>
   
    #activity{
        color: #fafafa;
    }
    .mobile-header{
        background: #fafafa;
        color: #222;
        width: 100%;
        height: 50px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 20pt;
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    }
    .home,.post,.profile,.chat,.friends {
    width: 90%;
    height: 85vh;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
  }
  .footer-nav{
    width: 100%;
    margin: 0 auto;
    color: #d9d9d9ca;
    font-size: 20pt;
    border-top: 1px solid #fafafa;
    margin-top: 5px;
    padding-top: 10px;
    display: flex;
    justify-content: space-around;
    position: absolute;
    bottom: 0;
    padding-bottom: 20px;
    background: #222;
    left: 50%;
    transform: translate(-50%);
  }
</style>