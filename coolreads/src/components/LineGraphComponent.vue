<script setup>
import { Chart as ChartJS,
  CategoryScale,LinearScale,PointElement,LineElement,Title,Tooltip,Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'

ChartJS.register(CategoryScale,LinearScale,PointElement,LineElement,Title,Tooltip,Legend)
</script>
<template>
    <main>
        <div class="line-container">
            <div v-if="dados.labels.length==0">No data available for those properties.</div>
            <Line :data="dados" :options="options" :key="updatekey" />
        </div>
    </main>
</template>
<script>
import axios from "axios";
import authHeader from '@/services/auth.header';
export default {
    components:{
        Line
    },
    props:{
        timeframe: {
            type: String,
            default: ''
        },
        bookshelf: {
            type: String,
            default: ''
        }
    },
    data(){
        return {
            mytimeframe:'',
            mydefault:'',
            updatekey:0,
            dados: {
                labels: [],
                datasets: [
                    {
                        label: 'Reading Over Time',
                        backgroundColor: '#f87979',
                        data: [],
                        borderColor: "rgb(80, 200, 120)",
                        pointBackgroundColor: "rgb(80, 200, 120)",
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
    created(){
        this.getData();
    },
    watch: {
        timeframe(newVal, oldVal) {
            this.mytimeframe = newVal;
            this.getData(this.mytimeframe,this.mydefault);
        },
        bookshelf(newVal, oldVal) {
            this.mydefault = newVal;
            this.getData(this.mytimeframe,this.mydefault);
        }
        
    },
    methods:{
        getData(){
            if(this.mytimeframe!="" && this.mydefault!=""){
                let mdefault = this.mydefault.toLowerCase().replaceAll(" ","_");
                axios.get("http://localhost:8080/api/book/"+this.isbn+"/stats/line?defaultBookshelf="+mdefault+"&timeFrame="+this.mytimeframe+"&pageNumber=0&pageSize=1")
                .then(resp=>{
                    this.dados.datasets = [
                    {
                        label: 'Reading Over Time',
                        backgroundColor: '#f87979',
                        data: resp.data.data,
                        borderColor: "rgb(80, 200, 120)",
                        pointBackgroundColor: "rgb(80, 200, 120)",
                    }
                    ]
                    this.dados.labels = resp.data.labels;
                    this.updatekey++;
                }).catch(error=>{
                    console.log(error);
                })

            }
        }
    }
}
</script>
<style>
.line-container{
    background-color: #31363F;
    width:1250px;
    height:800px;
    padding:20px;
    border-radius: 20px;
    color:white;
    margin:20px;
}
</style>