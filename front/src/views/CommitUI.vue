<template>
    <div class="commitUI">
        <header>
            <router-link :to="`/new/${this.$route.params.uuid}`" class="icon"><i class="fa-solid fa-arrow-left-long"></i></router-link>
        </header>
        
        <main>
            <div class="commits-content">
                <div v-for="(item,i) in commits" :key="i">
                    <div class="commit">
                        <div class="sender">
                            <div class="avatar"><img src="" alt=""></div>
                            <div class="username">{{ item.sender }}</div>
                        </div>
                        <div class="content">{{ item.content }}</div>
                    </div>

                    <div class="responses">
                        
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
    import url from '@/config.json'
    export default{
        name:"CommitUIVue",
        data(){
            return{
                commits:[]
            }
        },
        created(){
            setTimeout(()=>{
                this.getCommits()
            },1000)
        },
        methods:{
            async getCommits(){
                const response = await fetch(url.commit.getAll).then((e)=>{
                    return e.json()
                })
                let filter = [];
                response.forEach((e)=>{
                    if(e.recived == this.$route.params.uuid_sender){
                        filter.push(e)
                    }
                })

                this.commits = filter
                
            }
        }
    }
</script>

<style scoped>
    header{
        display: flex;
        background: #fafafa;
        width: 100%;
        height: 50px;
        color: #222;
        align-items: center;
    }
    .icon {
        font-size: 20pt;
        margin-left: 20px;
        color: #222;
    }
    .commit{
        background: #3a3939;
        width: 90%;
        margin: 0 auto;
        padding: 10px;
        border-radius: 10px;
        margin-top: 10px;
        color: #fafafa;
        max-height: 300px;
    }
    .content{
        max-height: 200px;
        overflow-y: scroll;
        font-size: 14pt;
        letter-spacing: 2px;
    }
    .sender{
        display: flex;
        align-items: center;
        gap: 10px;
        padding: 5px;
        border-bottom: 1px solid #fafafa;
    }
    .avatar{
        height: 40px;
        width: 40px;
        border-radius: 100%;
        background: #fafafafa;
        overflow: hidden;
    }
    .avatar img{
        width: 100%;
        height: 100%;
    }
    .commits-content{
        height: 80vh;
        margin-top: 10px;
        display: block;
        
    }
</style>

