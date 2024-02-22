<template>
    <div class="chat">
        <header class="chat-header"> <h2>Chat Global</h2></header>
          <main>
            <div class="messages">
                <div class="message" v-for="(item,i) in messages" :key="i">
                    
                    <div class="user">
                        <div class="avatar"><img :src="item.avatar" alt=""></div>
                        <div class="name">{{ item.username }}</div>
                    </div>

                    <div class="content">{{ item.content }}</div>
                    
                </div>
            </div>
        </main>
        <div class="keyboard">
            <input type="text" placeholder="Envie Uma Menssagem" v-model="currentMessage">
            <i class="fa-solid fa-paper-plane" @click="sendMessage"></i>
        </div>

    </div>
    
</template>

<script>
    import url from '@/config.json'
    export default {
    name: "chatVue",
    data() {
        return {
            socket:null,
            messages:[],
            currentMessage:"",
            data:null
        }
    },
    mounted(){
        this.data = JSON.parse(localStorage.getItem("data"))

        this.socket = new WebSocket(url.wss)

        this.socket.onopen = ()=>{
            console.log("Conexão Estabelecida ")
        }
        this.socket.onmessage = (e)=>{

            try {
            this.messages.push(JSON.parse(e.data));
            console.log(this.messages);
        } catch (error) {
            console.error('Erro ao analisar JSON:', error);
            console.log('Mensagem com problema:', e.data);
        }
            console.log(this.messages)
        }
    },
    methods: {
    sendMessage() {
        const message = { 
            uuid_post:this.data.uuid,
            content: this.currentMessage,
            username: this.data.username,

        };
        this.socket.send(JSON.stringify(message));
        this.currentMessage = ""; 
    }
  }
   
    }
</script>


<style scoped>
    .chat-header{
        display: flex;
        width: 100%;
        height: 40px;
        background: #D9D9D9;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
        margin-top: 5px;
   
    }
    .messages{
        display: block;
        width: 100%;
        height: 70vh;
        margin-top: 10px;   
        gap: 20px;     
        overflow-y: scroll;
    }
    .message{
        width: 90%;
        margin: 0 auto;
        padding: 10px;
        margin-top: 10px;
        background-color: #D9D9D9;
        border-radius: 10px;
    }
    .keyboard{
        width: 100%;
        position: absolute;
        bottom: 50px;
        left: 0px;
        background-color: #343434;
        display: flex;
        justify-content: space-between;
        align-items: center;
        color:#fafafa;
    }
    .keyboard i{
        font-size: 20pt;
        margin-right: 20px;
    }
    .keyboard input{
        width: 80%;
        height: 60px;
        border: none;
        outline: none;
        background-color: #343434;
        color: #D9D9D9;
        padding-left: 15px;
        
    }
    .keyboard input::placeholder{
        padding: 15px 10px;
    }
</style>