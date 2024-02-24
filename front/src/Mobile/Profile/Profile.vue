<template>
    <div class="profile">
        <div class="avatar">
           <span>{{ username }}</span>
           <input type="file" name="" style="display: none;" id="file">
           <div class="img"><i data-v-a11746ca="" class="fa-solid fa-server ico"></i></div>
          <!--  <label for="file">Colocar Foto De Perfil</label> -->
        </div>
        <h2 style="text-align: center; color: #fafafa;margin-top: 100px;">Detalhes Em Breve ...</h2>

        <div class="buttons">
            <div class="button" @click="setBlur">Minhas Publicações</div>
            <div class="button" @click="logout">Sair</div>
        </div>
    </div>
    <Posts v-if="this.$store.state.Profile.Posts"  />
</template>

<script>
    import Posts from "./Posts.vue"

    export default{
        name:"friendsVue",
        components:{
            Posts
        },
        data(){
            return{
                username:""
            }
        },
        mounted(){
            this.username = JSON.parse(localStorage.getItem("data")).username
            console.log(JSON.parse(localStorage.getItem("data")).username)
        },
        methods:{
            logout(){
                localStorage.removeItem("data")
                this.$store.state.isLoggin = false
            },
            setBlur(){
                this.$store.state.Profile.Posts = !this.$store.state.Profile.Posts
            }
        }
    }
</script>


<style scoped>
    .avatar{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
        color: #fafafa;
        margin: 0 auto;
        font-size: 30pt;
        gap: 10px;
        border-bottom: 1px solid #fafafa;
        padding-bottom: 10px;
    }
    .avatar img{
        width: 100px;
        height: 100px;
        border-radius: 100%;
        background: #fafafa;
    }
    .button{
        background: #fafafa;
        color: #222;
        width: 90%;
        height: 50px;
        border-radius: 10px;
        margin-top: 10px;
        font-size: 20pt;
        display: flex;
        align-items: center;
        justify-content: center;
       
    }
    .buttons{
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: auto;
        margin-bottom: 50px;
        padding-bottom: 10px;
    }
    .sair{
        margin-top: auto;
    }
    .avatar label{
        font-size: 20pt;
        background-color: rgb(75, 75, 75);
        color: #fafafa;
        
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        padding: 5px 10px;
    }
    .img{
        border: 1px solid #fafafa;
        border-radius: 100%;
        padding: 15px;
    }
    .img i{
        font-size: 50pt;
    }
</style>