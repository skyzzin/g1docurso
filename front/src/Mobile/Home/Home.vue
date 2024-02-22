<template>
    <div class="home">
      <div class="news">
        <router-link :to="'/new/'+item.uuid" class="new" v-for="(item, i) in news" :key="i">
          <div class="title">{{ item.title }}</div>
          <div class="img"> <img :src="localUrl + item.uuid + '/img/img_0.jpeg'" alt=""></div>
          <div class="desc">{{ description(item.description) }}</div>
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
          return item.slice(0,100) + '...'
        }else{
          return "Titulo Não Encontrado"
        }
      },
    },
 
    mounted(){
      this.getNews();
    },

    created(){
      this.localUrl = url.post.posts
    }
  };
  </script>
  
  <style scoped>

  
  .news {
  width: 100%;
  height: 100%;
  flex-direction: column;
  overflow-y: scroll; 
  align-items: center;
  gap: 20px;
}
.title{
  font-size: 16pt;
}
.news a{
  text-decoration: none;

}
.commits{

}

.new {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 90%;
  height: 400px;
  margin-top: 10px;
  color: #fafafa;
}

.new .title{
    width: 100%;
}
.new .img{
    width: 100%;
    min-height: 300px;
    margin: 10px 0px;
}
.new .img img{
    width: 100%;
    height: 100%;
    border-radius: 10px;
}
.new .desc{
    width: 100%;
    height: 100%;
    font-size: 14pt;
    letter-spacing: 2px;
    border-bottom: 1px solid #fafafa;
}

</style>
  