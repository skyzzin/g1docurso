<template>
    <div class="notify">
        <div class="notifys" v-for="(item,i) in notifys" :key="i">
            <div id="notify" :class="i > 1 ? 'viewed' : '' ">
                <div class="target" v-if="item.target">{{ item.target }}</div>

                <div :class="`${item.type} sender`" v-if="item.sender" >
                    <div ></div> {{ item.sender }}
                </div>
                <div class="message" v-if="item.message">{{ item.message }}</div>
            </div>
        </div>
    </div>
    
</template>

<script>
    export default{
        name:"notifyVue",
        data(){
            return{
                notifys:[
                    {
                        sender:"Servidor",
                        recived:"Usuario",
                        type:"warn",
                        message:"Sua Conta Foi Acessada Caso Não Tenha Sido Você Considere Trocar a Senha Imediatamente"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"request",
                        message:"Usuario69 Enviou Um Pedido De Amizade para Você"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"response",
                        target:"Nome Da Publicaão",
                        message:"Usuario69 Respondeu Você"
                    },
                    {
                        sender:"Servidor",
                        recived:"Usuario",
                        type:"warn",
                        message:"Sua Conta Foi Acessada Caso Não Tenha Sido Você Considere Trocar a Senha Imediatamente"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"request",
                        message:"Usuario69 Enviou Um Pedido De Amizade para Você"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"response",
                        target:"Nome Da Publicaão",
                        message:"Usuario69 Respondeu Você"
                    },
                    {
                        sender:"Servidor",
                        recived:"Usuario",
                        type:"warn",
                        message:"Sua Conta Foi Acessada Caso Não Tenha Sido Você Considere Trocar a Senha Imediatamente"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"request",
                        message:"Usuario69 Enviou Um Pedido De Amizade para Você"
                    },
                    {
                        sender:"Usuario69",
                        recived:"Usuario171",
                        type:"response",
                        target:"Nome Da Publicaão",
                        message:"Usuario69 Respondeu Você"
                    },
                ]
            }
        }
    }
</script>


<style scoped>
.notify{
    overflow-x: scroll;
    display: block;
    height: 90vh;
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

  
</style>