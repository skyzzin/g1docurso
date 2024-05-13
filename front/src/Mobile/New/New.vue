<template>
    <div class="new">
      <header class="new-header">
        <router-link to="/"><i class="fa-solid fa-arrow-left-long"></i></router-link>
        {{ title }}
      </header>
  
      <main>
        <div class="midias">
          <div class="box">
            <div class="left"></div>
            <img :src="current_midia.value" v-if="current_midia.type == 'img'" alt="">
            <video :src="current_midia.value" v-if="current_midia.type == 'video'" controls></video>
            
            <div class="right"></div>
          </div>
          <div class="list">
            <img :src="item" class="img" v-for="(item,i) in midia.imgs" :key="i" alt="" @click="setMidia">
            <video :src="item" class="video" v-for="(item,i) in midia.videos" :key="i" @click="setMidia"></video>
    
          </div>
          <div class="showDesc" @click="setBlur">Ver Detalhes</div>
        </div>
      </main>
        
      <div class="desc" v-show="this.$store.state.New.description">
        <div class="description">
          {{ description }}
          
      </div>

      <Commits :data="commits"  />
      
      <div class="btnCommit" @click="setCommit">Comentar</div>

        <div class="commit-container" v-if="commitDisplay" >
          <div class="blur-commit" @click="setCommit"></div>
          <div class="keyboard" >
            <input type="text" placeholder="Digite Um Comentario" v-model="sendCommitValue">
            <i data-v-1d289d70="" class="fa-solid fa-paper-plane" @click="sendCommit"></i>
          </div>
       </div>

        <div class="blur" @click="setBlur"></div>
        <h1 style="position: absolute;">test</h1>
      </div>
  
    </div>
  </template>
  
  <script>
  import Commits from './Commits.vue';
  import url from '@/config.json'
  
  export default {
    name: "NewVue",
    data() {
      return {
        id: "",
        midia: [],
        commits: [],
        commitDisplay:false,
        title:"",
        description:"",
        current_midia:"",
        sendCommitValue:""
      };
    },
  
    methods: {
      setCommit(){
        this.commitDisplay = !this.commitDisplay
      },
     
      setBlur(event) {
        this.$store.state.New.description = !this.$store.state.New.description;
        event.stopPropagation();
      },
     
      getNew(){
        fetch(url.post.get + this.id).then(e=>e.json()).then((ev)=>{
          //console.log(ev)
          this.title = ev.title
          this.description = ev.description
          this.getMidia(ev.uuid)
          this.getCommits();
        }).catch((err)=>{
          console.error("Error Detected => ",err)
        })
      },
      async getMidia(id){

        const response = await fetch(url.post.midia + id).then((res)=>{
          return res.json()
        })

        this.midia = response
        console.log(response)
          
        if(response.imgs.length > 0){
          this.current_midia = {type:"img",value:response.imgs[0]}
          
        }else if(response.videos.length > 0){
          this.current_midia = {type:"video",value:response.videos[0]}
        }
        

      },

      setMidia(e){
        let div = e.target.classList.value
        if(div === 'img'){
          this.current_midia = {type:"img",value:e.target.src}
        }else if(div === 'video'){
          this.current_midia = {type:"video",value:e.target.src}

        }else{
          alert("Um belo Pau No Cu De Quem upou esse Arquivo Pq é pra ter video e img nn arquivo desconhecido")
        }
        
      },
      async getCommits(){
        const response = await fetch(url.commit.getAll).then((res)=>{
          return res.json()
        })
        const filter = response.filter(ev=>ev.uuid_post == this.$route.params.uuid)
        this.commits = filter
      },
      sendCommit(){
        const localData = JSON.parse(localStorage.getItem('data'))
        const data = {
          uuid_post:this.$route.params.uuid,
          sender:localData.username,
          recived:null,
          content:this.sendCommitValue
        }
        fetch(url.commit.post,{
          method:"POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(data)
        }).then((e)=>{return e.json}).then((ev)=>{
          this.getNew()
          this.setCommit()
          this.sendCommitValue = ""
          console.log("Resposta Do Server: ",ev)})
        .catch((err)=>{console.error(err)})
      }
    
    },
    mounted() {
      this.id = this.$route.params.uuid;
      this.getNew()
    },
    components: { Commits },
  };
  </script>

<style scoped>
.btnCommit{
  position: absolute;
  bottom: 20px;
  width: 85%;
  background: #fafafa;
  z-index: 2;
  left: 50%;
  transform: translate(-50%);
  padding: 10px;
  border-radius: 10px;
  font-size: 20pt;
  color: #222;
  display: flex;
  align-items: center;
  justify-content: center;

}

.keyboard{
  position: absolute;
  bottom: 0;
  width: 100%;
  display: flex;
  align-items: center;
  background: #3a3a3a;
  color: #fafafa;
  font-size: 20pt;
  z-index: 3;
}
.keyboard input{
  border: none;
  background: #3a3a3a;
  width: 90%;
  height: 60px;
  outline: none;
  color: #fafafa;
  padding: 10px;
  font-size: 16pt;

}
.commit-container{
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: #22222296;
  z-index: 2;
}
.blur-commit{
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: #22222296;
  z-index: 2;
}


.description {
    background: #fafafa;
    color: #222;
    width: 88%;
    padding: 10px;
    margin: 0 auto;
    height: 200px;
    border-radius: 10px;
    margin-top: 50px;
    position: absolute;
    top: 20px;
    left: 50%;
    transform: translate(-50%);
    z-index: 2;
    word-break: break-word;
    overflow-y: scroll;
    font-size: 14pt;
    letter-spacing: 2px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}



.showDesc {
    background: #fafafa;
    height: 50px;
    width: 100%;
    color: #222;
    font-size: 14pt;
    letter-spacing: 2px;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
}

.blur {
    position: fixed;
    /* Mude para position: fixed; */
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #222222c4;
    z-index: 1;
}

.new-header {
    background: #fafafa;
    color: #222;
    width: 100%;
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20pt;
    position: relative;
    font-family: "Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif";
}

.new-header a {
    position: absolute;
    left: 20px;
    color: #222;
}

.midias {
    width: 90%;
    margin: 0 auto;
    height: 50vh;
    margin-top: 20px;
}

.box {
    width: 100%;
    height: 80%;
    border: 1px solid #fafafa;
    position: relative;
    border-radius: 10px;
    overflow: hidden;
}
.box img{
  width: 100%;
  height: 100%;
}
.box video{
  width: 100%;
  height: 100%;
}

.list {
    display: block;
    overflow-x: scroll;
    width: 100%;
    height: 220px;
    margin: 0 auto;
    margin-top: 10px;
}

.list img {
    width: 90px;
    height: 90px;
    border: 1px solid #fafafa;
    margin: 10px;
    border-radius: 10px;
}
.list video {
    width: 90px;
    height: 90px;
    border: 1px solid #fafafa;
    margin: 10px;
    border-radius: 10px;
}

.left,
.right {
    position: absolute;
    font-size: 20pt;
    height: 100%;
    width: 100px;
}

.left {
    left: 0;
}

.right {
    right: 0;
}</style>