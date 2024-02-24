<template>
    <div class="posts"  >
        <div class="blur" @click="setBlur"></div>
        <div class="post" v-for="(item,i) in myPosts" :key="i">
            <router-link :to="`/new/${item.uuid}`" class="img"><img :src='`${url}${item.uuid}/img/img_0.jpeg`' alt=""></router-link>
            <router-link :to="`/new/${item.uuid}`"><div class="title">{{ item.title }}</div></router-link>
            
            <div class="edit">
              
                <i class="fa-solid fa-trash" @click="removePost(item.uuid)"></i>
            </div>
        </div>
    </div>
</template>

<script>
    import url from "@/config.json"
    export default{
        name:"PostsProfile",
        data(){
            return{
                myPosts:[],
                url:""
            }
        },
        methods:{
            setBlur(){
                this.$store.state.Profile.Posts = !this.$store.state.Profile.Posts
            },
            renderPosts(){
                fetch(url.post.getAll).then(e=>e.json()).then((ev)=>{
                const uuid = JSON.parse(localStorage.getItem('data')).uuid
                
                const filter = ev.filter(item=>item.uuid_user === uuid)
                this.myPosts = filter
            })
            },
            removePost(uuid){
                const data = {
                    uuid_user:uuid
                }
                
                fetch(url.post.delete,{
                    method:"DELETE",
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify(data)
                }).then(e=>e.text()).then((e)=>{
                    console.log(e)
                    this.renderPosts()

                })
            }
        },
        
        created(){
            this.url = url.post.posts
            this.renderPosts()
        }
    }
</script>

<style scoped>
    .posts{
        width: 100%;
        height: 100%;
        background: #222222a0;
        position: absolute;
        top: 0;
        z-index: 1;
    }
    .blur{
        width: 100%;
        height: 100%;
        background: #222222a0;
        position: absolute;
        top: 0;
        z-index: -1;
    }
    .post{
        width: 90%;
        margin: 0 auto;
        background: #fafafa;
        height: 100px;
        border-radius: 10px;
        margin-top: 10px;
        display: flex;
        align-items: center;
        z-index: 2;
    }
    .img{
        width: 80px;
        height: 80px;
        border-radius: 10px;
        margin-left: 10px;
        background: #222;
        
    }
    .img img{
        width: 100%;
        height: 100%;
    }
    .title{
        margin-left: 20px;
        color: #222;
    }
    .edit{
        display: flex;
        flex-direction: column;
        width: 30px;
        height: 100%;
        margin-left: auto;
        margin-right: 10px;
        color: #222;
        font-size: 20pt;
        justify-content: space-around;
       
    }
    .edit i{
        opacity: 0.9;
    }
    .edit i:hover{
        opacity: 1;
    }
</style>