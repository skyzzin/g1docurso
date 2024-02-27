<template>
    <div class="chat">
        <header class="chat-header"> <h2 >Chat Global</h2></header>
          <main>
            <div class="messages" ref="messagesContainer">
                <div class="message" v-for="(item,i) in messages" :key="i" :style="item.username == data.username ? 'background:#fafafa' : 'background:#f4dada'  ">
                    
                    <div class="user">
                        <div class="avatar"><i data-v-a11746ca="" class="fa-solid fa-server ico"></i></div>
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
            data:null,
         
        }
    },
    mounted(){

        try{
            this.renderMessages()
        } catch(e){
            console.log(e)
        }
        
        this.data = JSON.parse(localStorage.getItem("data"))

        this.socket = new WebSocket(url.chat.wss)

        this.socket.onopen = ()=>{
            console.log("Conexão Estabelecida ")
        }
        this.socket.onmessage = (e) => {
            const message = e.data;
            if (typeof message === 'string') {
                try {
                    const parsedMessage = JSON.parse(message);
                    this.messages.push(parsedMessage);
                    this.$nextTick(() => {
                        this.scrollToBottom();
                    });
                } catch (error) {
                    console.error('Erro ao analisar JSON:', error);
                    console.log('Mensagem com problema:', message);
                }
            } else if (message instanceof Blob) {
                
                const reader = new FileReader();
                reader.onload = () => {
                    try {
                        const parsedMessage = JSON.parse(reader.result);
                        this.messages.push(parsedMessage);
                        this.$nextTick(() => {
                            this.scrollToBottom();
                        });
                    } catch (error) {
                        console.error('Erro ao analisar JSON:', error);
                        console.log('Mensagem com problema:', reader.result);
                    }
                };
                reader.readAsText(message);
            } else {
                console.error('Tipo de mensagem não suportado:', typeof message);
            }
        };
    },
    methods: {
        sendMessage() {
            if(this.currentMessage.length > 0){
                const message = { 
                uuid_post:this.data.uuid,
                content: this.currentMessage,
                username: this.data.username,

            };
            this.socket.send(JSON.stringify(message));
            this.currentMessage = ""; 
            }
        },
        renderMessages(){
            fetch(url.chat.getAll).then(e=>e.json())
            .then((ev)=>{
                this.messages = ev;
                try{
                    this.scrollToBottom()
                }catch(e){
                    console.log(e)
                }
            }).catch((e)=>{
                console.log(e)
            })
        },
        scrollToBottom() {
            const messagesContainer = this.$refs.messagesContainer;
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
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
        font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
   
    }
    .messages{
        display: block;
        width: 100%;
        height: 70vh;
        margin-top: 10px;   
        overflow: scroll;   
    }
    .messages .message:last-child {
            margin-bottom: 50px;
        }
 
    .message{
        width: 90%;
        margin: 0 auto;
        padding: 10px;
        margin-top: 10px;
        background-color: #D9D9D9;
        border-radius: 10px;
        position: relative;
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
    .user{
        display: flex;
        gap: 10px;
        align-items: center;
        border-bottom: 1px solid #222;
        padding-bottom: 5px;
    }
    .user .avatar{
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 8px;
        background: #222;
        border-radius: 10px;
    }
    .content{
        margin-top: 5px;
    }
</style>