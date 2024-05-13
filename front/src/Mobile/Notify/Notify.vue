<template>
    <div class="notify">
        <div class="notifys" v-for="(item,i) in notifys" :key="i" >
            <div id="notify" v-if="sender != item.sender" >
                <div class="target" v-if="item.target">{{ item.target }}</div>

                <router-link :to="renderUrl(item.uuid_post)" :class="`${item.type} sender`" v-if="item.sender" >
                    <div style="display: flex;justify-content: center;align-items: center;">
                        <i class="fa-solid fa-server ico"></i>
                    </div>
                     <span :style="!renderUrl(item.url) == '#' ">{{ item.sender === `G1 Do Duque` ? 'G1 Do Duque' : `${item.sender} Respondeu Sua Postagem` }} </span>
                </router-link>
                <div class="message" v-if="item.message">{{ item.message }}</div>
            </div>
        </div>
    </div>
    
</template>

<script>
    import url from '@/config.json'
    export default{
        name:"notifyVue",
        data(){
            return{
                sender:"",
                notifys:[
                    
                ]
            }
        },
        methods:{
            async renderCommits(){
                
                const posts = await fetch(url.post.getAll).then((e)=>{
                    return e.json()
                })

                const commits = await fetch(url.commit.getAll).then((e)=>{
                    return e.json()
                })

                const UUID = JSON.parse(localStorage.getItem('data')).uuid

               
                posts.forEach((e)=>{
                    if(e.uuid_user === UUID){
                        commits.forEach((commit)=>{
                            if(commit.uuid_post == e.uuid){
                                this.sender = commit.sender
                                const data = {
                                    message:commit.content,
                                    sender:commit.sender,
                                    uuid_post:commit.uuid_post,
                                    type:"response"
                                }
                                this.notifys.push(data)
                            }
                        })
                   
                    }
                })
                    
                    
                
            },
            renderUrl(uuid){
                if(uuid){
                    return `/new/${uuid}`
                } else{
                    return "#"
                }
            }
        },
        mounted(){
            this.renderCommits()
            this.notifys.push({
                        sender:"G1 Do Duque",
                        recived:"",
                        type:"server",
                        message:`
                        Olá Seja Bem Vindo Ao G1 Do Duque, Como o Site Está Em Fase De Desenvolvimento
                        Você Receberá Notificações Sobre Tudo Que Foi Implementado e Adicionado Como
                        Correção De Bugs e Novas Funcionalidades.
                        Aproveite o Site!!!

                        `
                    },)
           
        }
    }
</script>


<style scoped>

.notify{
    overflow-x: scroll;
    display: block;
    height: 85vh;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.server{
    background-color: #fafafa;
}
   .warn{
    background-color: yellow;
   }
   .viewed{
    opacity: 0.6;
   }
   .request{
    background-color: greenyellow;
   }
   #notify{
    width: 90%;
    margin: 0 auto;
    display: flex;
    margin-top: 10px;
    flex-direction: column;
    background: gray;
    padding: 10px;
    border-radius: 5px;
    gap: 10px;
   }
   .sender{
    border-radius: 10px;
    color: #222;
    display: flex;
    align-items: center;
    padding: 10px 0px;
    gap: 10px;
    text-decoration: none;
   }
   .sender div{
    width: 30px;
    height: 30px;
    border-radius: 10px;
    margin-left: 5px;
    background: #222;
   }
   .recived,.message,.target{
    background: #fafafa;
    color: #222;
    border-radius: 10px;
    padding: 10px;
   }
   .response{
    background-color: dodgerblue;
   }
   .message{
    font-size: 13pt;
    letter-spacing: 2px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
   }
   .ico{
    color: yellow;
    font-size: 14pt;
   }

  
</style>