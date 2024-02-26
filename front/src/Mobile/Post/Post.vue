<template>
    <div class="post">
        <div class="form">
            <div class="anonimo">
                Publicação Anonima
                <div class="boxAnonimo" @click="setAnonimo">
                    <div class="boxAnonimoCheck" v-if="anonimo"></div>
                </div>
            </div>

            <br>

            <input type="text" class="title" placeholder="Titulo Da Publicação" v-model="title" >
            <textarea name="" id="" placeholder="Descrição da Publicação" v-model="description" cols="30" rows="10" class="desc"></textarea>
            <div class="display">
                <div class="img" v-for="(item, i) in imgs" :key="i">
                  <img :src="item" alt="" @click="setMediaBlur">
                  <i class="fa-solid fa-xmark closed" @click="removeMedia(i, 'img')"></i>
                </div>
                <div class="video" v-for="(item, i) in videos" :key="i">
                  <video :src="item" alt="" @click="setMediaBlur" />
                  <i class="fa-solid fa-xmark closed" @click="removeMedia(i, 'video')"></i>
                </div>
              </div>
            <label for="fileImg" class="send">Adicionar Videos e Imagens</label>
            <input type="file" @change="filesBase64" name="" id="fileImg" multiple style="display: none;">
            <div class="send" @click="sender">Publicar</div>
            
          
        </div>

        <div class="blur" v-show="blur == true" @click="setBlur">
            <div class="box">
                <img :src="currentMedia" v-if="currentMediaState == 'imagem' " alt="">
                <video :src="currentMedia" alt="" v-if="currentMediaState == 'video' "  controls />
            </div>
        </div>
        <div class="warn">
            aaa
        </div>
    </div>
</template>

<script>
    import url from '@/config.json'
    export default{
        name:"postVue",
        data(){
            return{
                title:"",
                description:"",
                imgs:[],
                videos:[],
                blur:false,
                currentMedia:"",
                currentMediaState:"",
                anonimo:false
            }
        },
        
        methods:{
            setAnonimo(){
                this.anonimo = !this.anonimo
            },
            renderWarn(txt,type){
                document.querySelector('.warn').textContent = txt
                document.querySelector('.warn').style.background = type
                document.querySelector('.warn').style.left = '35px'
                setTimeout(()=>{
                    document.querySelector('.warn').style.left = '-500px'
                },2000)
            },
            sender(){
                const localData = JSON.parse(localStorage.getItem("data"))
                console.log(localData)
                let data;

                if(!this.anonimo){
                     data = {
                    username:localData.username,
                    uuid_user:localData.uuid,
                    title:this.title,
                    description:this.description,
                    videos:this.videos,
                    imgs:this.imgs,
                }
                }else{
                     data = {
                    username:"Anonimo",
                    uuid_user:localData.uuid,
                    title:this.title,
                    description:this.description,
                    videos:this.videos,
                    imgs:this.imgs,
                }
                }

                console.log(JSON.stringify(data))

                if(this.title.length > 5){
                    if(this.description.length > 5){
                        fetch(url.post.create,{
                    method:'POST',
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify(data)
                }).then().then(()=>{
                    this.renderWarn("Publicação Enviada Com Sucesso","green")
                }).catch(()=>{
                    this.renderWarn("Erro Na Publicação","red")
                })
                        this.title = ""
                        this.description = ""
                        this.imgs = []
                        this.videos = []
                        this.anonimo = false
                    }else{
                        this.renderWarn("Descrição Muito Pequena")
                    }
                }else{
                    this.renderWarn("Titulo Muito Pequeno","red")
                }
               

            },
            filesBase64(e) {

            Array.from(e.target.files).forEach((file) => {
                const leitor = new FileReader();

                leitor.onload = (ev) => {
                    let url = ev.target.result;

                    if (url.startsWith('data:image')) {
                        this.imgs.push(url);
                    } else if (url.startsWith('data:video')) {
                        this.videos.push(url);
                    }

                   // console.log(url);
                };

                leitor.readAsDataURL(file);
            });
        },
            removeMedia(index, type) {

            if (type === "img") {
                this.imgs.splice(index, 1);
            } else if (type === "video") {
                this.videos.splice(index, 1);
            }
            },
            setBlur(){
                this.blur = !this.blur
            },
            setMediaBlur(e){
                this.setBlur()
                //console.log(e.target.src)
                if(e.target.src.startsWith("data:image")){
                    this.currentMediaState = 'imagem'
                } else if(e.target.src.startsWith("data:video")){
                    this.currentMediaState = 'video'
                }else{
                    console.log('Deu Erro')
                }

                this.currentMedia = e.target.src
            }
        }
    }
</script>


<style scoped>
    .anonimo{
        width: 90%;
        margin: 0 auto;
        background: #fafafa;
        color: #222;
        border-radius: 5px;
        padding: 10px;
        margin-top: 10px;
        display: flex;
        align-items: center;
        
        justify-content: space-between;
    }
    .boxAnonimo{
        width: 30px;
        height: 30px;
        border-radius: 5px;
        background: rgb(39, 39, 39);
        padding: 5px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .boxAnonimoCheck{
        width: 30px;
        height: 30px;
        border-radius: 5px;
        background: #fafafa;
    }

    .warn{
        position: absolute;
        background: red;
        height: 40px;
        padding: 10px;
        border-radius: 10px;
        width: 80%;
        bottom: 75px;
        left: -1000px;
        transition: 0.7s;
        color: #fafafa;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 20pt;
    }
    .blur{
        background-color: #2222229a;
        width: 100%;
        height: 100%;
        z-index: 1;
        position: absolute;
        top: 0;
        left: 0;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .box{
        z-index: 2;
        
        border-radius: 10px;
        width: 90%;
        height: 50%;
        display: flex;
        justify-content: center;
    }
    .box img,.box video{
        max-width: 100%;
        max-height: 100%;
        border-radius: 10px;
    }
    .closed{
    color: red;
    position: absolute;
    top: 5px;
    left: 5px;
    font-size: 20pt;
}
   .form{
    width: 100%;
    height: 85%;
    margin-top:20px;
    display: flex;
    align-items: center;
    flex-direction: column;
   }
   .title,.desc,.send{
    background: #fafafa;
    color: #222;
    border-radius: 5px;
    width: 90%;
    height: 40px;
    outline: none;
    border: none;
    padding: 0 10px;
    resize: none;
   }
   .desc{
    height: 180px;
    margin-top: 10px;
    padding-top: 10px;
   }
   .send{
    margin-top: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
   }
   .display{
    background: #fafafa;
    height: 180px;
    width: 90%;
    padding: 10px;
    border-radius: 5px;
    margin-top: 10px;
    display: flex;
    flex-wrap: wrap;
    overflow-y: scroll;
    align-items: center;
    justify-content: start;
    gap: 10px;
   }
   .img,.video{
    position: relative;
    width: 100px;
    height: 100px;
    margin: 0 auto;
   }
   .img img,.video video{
    width: 100%;
    height: 100%;
    border-radius: 10px;
    background: #222;
   }
</style>