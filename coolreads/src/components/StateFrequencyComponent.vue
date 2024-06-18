
<template>
    <main>
        <div class="frequency-container">
            <div v-for="(value,index) in labels" :key="index">
               <div class="freq-row"> {{ labels[index] }} :  {{ frequencies[index] }}</div>
            </div>
        </div>
    </main>
</template>
<script>
import axios from "axios";
import authHeader from '@/services/auth.header';

export default {
    props:{
        isbn:''
    },
    data(){
        return {
            labels: ["Already Read","Currenly Reading", "Did Not Finish", "Want To Read"],
            frequencies: []      
        }
    },
    created(){
        this.getData();
    },
    methods:{
        getData(){
            console.log("ritaaa");
            console.log(this.isbn);
            let header = authHeader();
            let config = {headers:header};
            axios.get("http://localhost:8080/api/book/"+this.isbn+"/stats/numbers",config).then(resp=>{
                    this.frequencies.push(resp.data.alreadyRead)
                    this.frequencies.push(resp.data.currentlyReading)
                    this.frequencies.push(resp.data.didNotFinish)
                    this.frequencies.push(resp.data.wantToRead)
                }).catch(error=>{
                    console.log(error);
                })
    
        }
    }
}
</script>
<style>
.frequency-container{
    background-color: #31363F;
    width:600px;
    padding-left:120px;
    border-radius: 20px;
    color:white;
    margin:20px;
    padding-top:120px;
    height:600px;
}
.freq-row{
    margin:40px;
}
</style>