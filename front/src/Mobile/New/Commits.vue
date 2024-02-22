<template>
    <div class="commits">
        <!--  <router-link :to="`/new/${item.uuid_post}/commit/${item.sender}`" class="commit" v-for="(item,i) in data" :key="i"> -->
        <div  class="commit" v-for="(item,i) in data" :key="i">
            <div class="sender">
                <div class="avatar"><img src="" alt=""></div>
                <div class="username">{{ item.sender }}</div>
                <div class="date">{{ item.createdat }}</div>
            </div>
            <div class="content">
                {{ item.content }}
            </div>

            <div class="sizeCommits">{{  }}</div>
           <!--  <i class="fa-solid fa-caret-down more" ></i> -->

        </div>

    
        
    </div>
</template>

<script>


    export default{
    name: "CommitsVue",
    data(){
        return{
           
        }
    },
    props: {
        data: {
            type: Object
        }
    },
    methods:{
        
    },
    mounted() {
        console.log(this.data);
    },
   
}
</script>

<style scoped>

.commits {
    width: 90%;
    height: 50%;
    background: #fafafa;
    border-radius: 10px;
    margin: 0 auto;
    margin-top: 10px;
    overflow-y: scroll;
    display: block;
    position: absolute;
    left: 50%;
    transform: translate(-50%);
    top: 300px;
    z-index: 2;
}
.more{
    color: #fafafa;
    position: absolute;
    right: 20px;
    bottom: 0px;
    font-size: 20pt;
}
.date{
    margin-left: auto;
}
.content{
    
    max-width: 95%;
    font-size: 14pt;
    letter-spacing: 2px;
    max-height: 200px;
    overflow-y: scroll;
}
.commit {
    background-color: rgb(22, 22, 29);
    max-height: 300px;
    width: 85%;
    display: flex;
    flex-direction: column;
    overflow-x: scroll;
    color: #fafafa;
    margin: 0 auto;
    border-radius: 5px;
    margin-top: 25px;
    padding: 10px;
    position: relative;
}
.commit:last-child{
    margin-bottom: 20px;
}
.sendMessage{
    background: #fafafa;
    color: #222;
    bottom: 20px;
    width: 200px;
    height: 40px;
    border-radius: 10px;
    left: 50%;
    font-size: 20pt;
    display: flex;
    align-items: center;
    justify-content: center;
    transform: translate(-50%);
    box-shadow: 0px 0px 5px #b7b4b4;
    position: absolute;  
    z-index: 2;  
}

.sender {
    border-bottom: 1px solid #fafafa;
    display: flex;
    gap: 10px;
    align-items: center;
    padding-bottom: 10px;
}

.sender img {
    width: 40px;
    height: 40px;
    border-radius: 100%;
    background: #fafafa;
}
</style>