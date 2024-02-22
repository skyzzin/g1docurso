<template>
    <div class="profile">
        <div class="avatar">
           <span>{Username}</span>
           <img src="" alt="">
        </div>

        <div class="buttons">
            <div class="button">{Email}</div>
            <div class="button">{Trocar Senha}</div>
            <div class="button" @click="logout">Sair</div>
        </div>
    </div>
    
</template>

<script>
    export default{
        name:"friendsVue",
        methods:{
            logout(){
                localStorage.removeItem("data")
                this.$store.state.isLoggin = false
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
        width: 100%;
        height: 50px;
        border-radius: 10px;
        margin-top: 10px;
        font-size: 20pt;
        display: flex;
        align-items: center;
        justify-content: center;
        opacity: 0.8;
    }
    .buttons{
        border-bottom: 1px solid #fafafa;
        padding-bottom: 10px;
    }
    .sair{
        margin-top: auto;
    }
</style>