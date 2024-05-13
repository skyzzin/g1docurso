<template>
    <div class="home">
      <div class="news">
        <router-link :to="'/new/'+item.uuid" class="new" v-for="(item, i) in news" :key="i">
          <div class="title"> <div class="avatar"><i data-v-a11746ca="" class="fa-solid fa-server ico"></i></div> {{title(item.username)}}</div>
          <div class="title">{{ title(item.title) }}</div>
          <div class="desc">{{ description(item.description) }}</div>
          <div class="img"> <img :src="localUrl + item.uuid + '/img/img_0.jpeg'" alt=""></div>
        </router-link>
      </div>
    </div>
  </template>
  
  <script>
  import url from '@/config.json';
  export default {
    name: "HomeView",
    data() {
      return {
        news:null,
        localUrl:""
      };
    },
    methods:{
     
      getNews(){
        fetch(url.post.getAll).then(e=>e.json()).then((data)=>{
          if(data){
            this.news = data;
          }
        }).catch((err)=>{
          console.error("Error Detected => ",err)
        })
      },
      description(item){
        if(item){
          return item.length > 250 ? item.slice(0,250) + '...' : item
        }else{
          return "Titulo Não Encontrado"
        }
      },

      title(item){
        if(item){
          return item.length > 30 ? item.slice(0,30) + '...' : item
        }else{
          return "Titulo Não Encontrado"
        }
      },
    },
 
    mounted(){
      this.getNews();
      const uuid = String(JSON.parse(localStorage.getItem('data')).uuid)
      
      if(uuid.length > 1){
        this.$store.state.isLoggin = true
      }
    },

    created(){
      this.localUrl = url.post.posts
    }
  };
  </script>
  
  <style scoped>

  
  .news {
    overflow-x: scroll;
    display: block;
    height: 90vh;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.title{
 
    width: 90%;
    margin: 0 auto;
    display: flex;
    margin-top: 10px;
    align-items: center;
    background: #fafafa;
    color: #222;
    padding: 10px;
    border-radius: 10px;
    gap: 10px;
    
   }

.news a{
  text-decoration: none;

}

.new {
    width: 90%;
    margin: 0 auto;
    display: flex;
    margin-top: 10px;
    flex-direction: column;
    background: gray;
    padding: 10px;
    border-radius: 5px;
    
}
 .img{
    width: 95%;
    min-height: 300px;
    max-height: 300px;
    margin: 0 auto;
    margin-top: 10px;
    background: #fafafa;
    border-radius: 10px;
}
.title .avatar{
  width: 35px;
  height: 35px;
  background: #222;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
}
 .img img{
    width: 100%;
    height: 300px;
    border-radius: 10px;
}
 .desc {
    width: 90%;
    margin: 0 auto;
    display: flex;
    margin-top: 10px;
    flex-direction: column;
    background: #fafafa;
    color: #222;
    padding: 10px;
    border-radius: 10px;
    gap: 10px;
    max-height: 200px;
    overflow-y: auto; /* Adição para permitir rolagem vertical */
    word-wrap: break-word; /* Adição para quebrar o texto em várias linhas */
    letter-spacing: 2px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

</style>
  