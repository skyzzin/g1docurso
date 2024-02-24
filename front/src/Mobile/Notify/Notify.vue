<template>
    <div class="notify">
        <div class="notifys" v-for="(item,i) in notifys" :key="i">
            <div id="notify" :class="i > 1 ? 'viewed' : '' ">
                <div class="target" v-if="item.target">{{ item.target }}</div>

                <router-link :to="renderUrl(item.uuid_post)" :class="`${item.type} sender`" v-if="item.sender" >
                    <div style="display: flex;justify-content: center;align-items: center;">
                        <i class="fa-solid fa-server ico"></i>
                    </div>
                     <span :style="!renderUrl(item.url) == '#' ">{{ item.sender }} Comentou Na Sua Postagem</span>
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
                notifys:[
                    {
                        sender:"G1 Do Duque News",
                        recived:"",
                        type:"server",
                        message:`
                        Olá Seja Bem Vindo Ao G1 Do Duque, Como o Site Está Em Fase De Desenvolvimento
                        Você Receberá Notificações Sobre Tudo Que Foi Implementado e Adicionado Como
                        Correção De Bugs e Novas Funcionalidades.
                        Aproveite o Site!!!

                        `
                    },
                   
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

                posts.forEach((e,i)=>{
                    if(e.uuid == commits[i].uuid_post){
                        const data = {
                            sender:commits[i].sender,
                            type:"response",
                            uuid_post:commits[i].uuid_post,
                            message:commits[i].content
                        }
                        this.notifys.push(data)
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
        }
    }
</script>


<style scoped>

.notify{
    overflow-x: scroll;
    display: block;
    height: 90vh;
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