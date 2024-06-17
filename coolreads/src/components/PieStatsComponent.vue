<script setup>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Pie } from 'vue-chartjs'
ChartJS.register(ArcElement, Tooltip, Legend)
</script>
<template>
    <main>
        <div class="pie-container">
            <div v-if="dados.labels.length==0">Change the search properties to show statistics.</div>
            <Pie :data="dados" :options="options" :key="updatekey" />
        </div>
    </main>
</template>
<script>
import axios from "axios";
import authHeader from '@/services/auth.header';
export default {
    props: {
        categoria: {
            type: String,
            default: ''
        },
        bookshelf: {
            type: String,
            default: ''
        },
        isbn:''
    },
    components:{
        Pie
    },
    data(){
        return {
            mycategory:'',
            mydefault:'',
            updatekey: 0,  
            dados: {
                labels: [],
                datasets: [
                    {
                    backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
                    data: []
                    }
                ]
            },
            options: {
            plugins: {
                legend: {
                    labels: {
                        color: 'white',
                        usePointStyle: true,
                        font: {
                            size: 18
                        }
                    }
                }
            }
            }       
        }
    },
    watch: {
        categoria(newVal, oldVal) {
            this.mycategory = newVal;
            this.getData(this.mycategory,this.mydefault);
        },
        bookshelf(newVal, oldVal) {
            this.mydefault = newVal;
            this.getData(this.mycategory,this.mydefault);
        }
        
    },
    methods:{
        getData(mycategory, mydefault){
            if(mycategory!='' && mydefault!=''){
                let header = authHeader();
                let config = {headers:header};
                let categ = mycategory;
                let newcateg = categ.toLowerCase(); 
                let bookshf = mydefault;
                let newbookshf = bookshf.toLowerCase().replaceAll(" ","_"); 
                axios.get("http://localhost:8080/book/"+this.isbn+"/stats/pie/"+newcateg+"?defaultBookshelf="+newbookshf,config).then(resp=>{
                    this.dados.datasets = [{
                        backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
                        data: resp.data.data
                        }
                    ]
                    this.dados.labels = resp.data.labels;
                    this.updatekey++;
                    console.log(this.dados);
                }).catch(error=>{
                    console.log(error);
                })
            }
        }
    }
}
</script>
<style>
.pie-container{
    background-color: #31363F;
    width:600px;
    padding:20px;
    border-radius: 20px;
    color:white;
    margin:20px;
}
</style>