<template>
  <div class="home">
    <div class="news">
      <router-link v-for="item in news" :to="'/new/' + item.uuid" :key="item.uuid" class="new">
        <div class="title">
          <div class="avatar"><i class="fa-solid fa-server ico" style="color: yellow;"></i></div>
          {{ truncate(item.username, 30) }}
        </div>
        <div class="title">{{ truncate(item.title, 30) }}</div>
        <div class="desc">{{ truncate(item.description, 250) }}</div>
        <div class="img" v-if="item.imageLoaded">
          <img :src="getImageUrl(item.uuid)" alt="Imagem Não Foi Encontrada">
        </div>
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
      news: [],
      localUrl: "",
    };
  },
  methods: {
    async getNews() {
      try {
        const response = await fetch(url.post.getAll);
        const data = await response.json();
        if (data) {
          this.news = data.map(item => ({...item, imageLoaded: false}));
          await Promise.all(this.news.map(item => this.loadImage(item.uuid)));
        }
      } catch (error) {
        console.error("Error Detected => ", error);
      }
    },
    async loadImage(uuid) {
      try {
        await new Promise((resolve, reject) => {
          const img = new Image();
          img.onload = () => {
            resolve();
          };
          img.onerror = () => {
            reject();
          };
          img.src = this.getImageUrl(uuid);
        });
        this.news.find(item => item.uuid === uuid).imageLoaded = true;
      } catch (error) {
        console.error("Error loading image => ", error);
      }
    },
    truncate(text, length) {
      return text ? (text.length > length ? text.slice(0, length) + '...' : text) : "Titulo Não Encontrado";
    },
    getImageUrl(uuid) {
      return this.localUrl + uuid + '/img/img_0.jpeg';
    },
  },
  mounted() {
    this.localUrl = url.post.posts;
    this.getNews();
    const uuid = String(JSON.parse(localStorage.getItem('data')).uuid);
    if (uuid.length > 1) {
      this.$store.state.isLoggin = true;
    }
  },
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
  