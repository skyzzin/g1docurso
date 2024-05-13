<template>
    <div class="">
        <div class="warn">Menssagem De Erro</div>
        <div class="cadastro" v-if="login">
            <h1>Sejá Bem Vindo Ao G1 DO Duque</h1>
            <h3>Cadastro</h3>
            
            <input type="text" placeholder="Nome De Úsuario" v-model="username" class="c_username">
            <input type="text" placeholder="Email" v-model="email" class="c_email">
            <input type="password" placeholder="Senha" v-model="password1">
            <input type="password" placeholder="Senha (Repita)" v-model="password2">
            <div class="btnSubmit" @click="createUser">Criar Conta</div>
            
            <div class="line"></div>
            <div class="change" @click="setLogin">Já Tenho Conta</div>
            
        </div>
        
        <div class="login cadastro" v-if="!login">
            <h1>Sejá Bem Vindo Ao G1 DO Duque</h1>
            
            <h3>Login</h3>
            <input type="text" placeholder="Email" v-model="email" class="l_username">
            <input type="password" placeholder="Senha" v-model="password1" class="l_password">
            <div class="btnSubmit" @click="sign">Entrar</div>
            
            <div class="line"></div>
            <div class="change" @click="setLogin" >Não Tenho Conta</div>

        </div>

        <div class="disclaimer">
            <h2>Atenção</h2>
            <p>
                o Site Está Em Desenvolvimento Então Pode Haver Erros e Falta De Funcionalidades.
                novas funcionalidades ainda vão ser implementadas no futuro.
            </p>
                <br>
            <p>Site Não Otimizado Para Computadores Aperte F12 e Diminua A Janela Para Usar. </p>
        </div>
        
    </div>
</template>

<script>
    import url from "@/config.json"

    export default{
        name:"LoginVue",
        data(){
            return{
                login:false,
                username:"",
                email:"",
                password1:"",
                password2:""
            }
        },
        methods:{
            setLogin(){
                this.login = !this.login
            },
            async createUser() {
               
                if (this.login) {
                    if (this.password1 == this.password2){
                        const data = {
                            username: this.username,
                            email: this.email,
                            password: this.password1
                        };
                        if(
                            this.username.length > 2 ,
                            this.email.length > 2,
                            this.password1.length > 2,
                            this.password2.length > 2
                        ){
                            fetch(url.user.create, {
                                method: "POST",
                                headers: { "Content-Type": "application/json" },
                                body: JSON.stringify(data)
                            }).
                            then((ev)=>ev.text())
                            .then((evv)=>{
                                
                                if(String(evv).includes("Duplicate")){
                                    console.log("Email Ou Usuario Já Cadastrado")
                                    document.querySelector(".c_email").style.border = '1px solid red'
                                    document.querySelector(".c_username").style.border = '1px solid red'
                                    document.querySelector('.warn').style.display = 'flex'

                                    setTimeout(()=>{
                                        document.querySelector('.warn').style.right = '30px'
                                        document.querySelector('.warn').classList.add('red')
                                        document.querySelector('.warn').textContent = "Email ou Usuario Já Cadastrado"
                                        
                                    },10)

                                    setTimeout(()=>{
                                        document.querySelector('.warn').style.right = '500px'
                                    },2000)
                                }
                                
                                if(String(evv).includes("User Created")){
                                   
                                 
                                    document.querySelector(".c_email").style.border = 'none'
                                    document.querySelector(".c_username").style.border = 'none'
                                    document.querySelector('.warn').style.display = 'flex'


                                    setTimeout(()=>{
                                        document.querySelector('.warn').style.right = '30px'
                                        document.querySelector('.warn').classList.add('green')
                                        document.querySelector('.warn').textContent = "Usuario Criado Com Suceso"
                                        
                                    },10)

                                    setTimeout(()=>{
                                        document.querySelector('.warn').style.right = '500px'
                                        this.login = false
                                    },2000)
                                    
                                }
                            })
                        }


                    } else {
                        console.log("As senhas não coincidem.");
                    }
                }
            },
             sign(){
                const data = {
                        email: this.email,
                        password: this.password1
                    };
                    console.log(data)
                 
                         fetch(url.user.find, {
                            method: "POST",
                            headers: { "Content-Type": "application/json" },
                            body: JSON.stringify(data)
                        }).then((e)=>{
                            if(e.status !== 200){
                                
                                document.querySelector('.warn').textContent = "Email ou Senha Incorreto"
                                document.querySelector('.warn').style.right = '30px'
                                setTimeout(()=>{
                                    document.querySelector('.warn').style.right = '500px'
                                },2000)
                            }else{
                                return e.json()
                            }
                        }).then((ev)=>{
                            console.log(ev)
                             if(ev){
                                localStorage.setItem('data',JSON.stringify(ev))
                                this.$store.state.isLoggin = true 
                             }
                        })
                        
                        
                        
                       
                        
                  
            }

        }
    }
</script>

<style scoped>
    .warn{
        padding: 10px;
        height: 40px;
        border-radius: 10px;
        background: #fafafa;
        width: 80%;
        top: 20px;
        transition: 1s;
        right: 500px;
        position: absolute;
        
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .red{
        box-shadow: 0px 0px 10px red;
    }
    .green{
        box-shadow: 0px 0px 10px green;

    }
    .cadastro{
        margin:0 auto;
        margin-top:100px;
        display: flex;
        flex-direction: column;
        align-items: center;
        color: #fafafa;
        gap: 10px;
    }
    .cadastro input,.btnSubmit{
        width: 80%;
        border-radius: 10px;
        border: none;
        padding: 10px;
        outline: none;
        background: #fafafa;
        color: #222;
    }
    .line{
        width: 80%;
        background: #fafafa;
        height: 1px;
    }
    .btnSubmit{
        text-align: center;
    }
    .change{
        text-decoration: underline;
    }
    .cadastro h1{
        font-size: 14pt;
    }
    .disclaimer{
        color: red;

        width: 75%;
        margin: 0 auto;
        margin-top: 100px;
        background: #fafafa;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid red;
    }

    </style>