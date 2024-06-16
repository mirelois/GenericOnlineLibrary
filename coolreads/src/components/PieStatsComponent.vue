<script setup>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Pie } from 'vue-chartjs'
ChartJS.register(ArcElement, Tooltip, Legend)
</script>
<template>
    <main>
        <div class="pie-container">
            <Pie :data="dados" :options="options" />
        </div>
    </main>
</template>
<script>
import axios from "axios";
export default {
    components:{
        Pie
    },
    data(){
        return {
            dados: {
                labels: ['VueJs', 'EmberJs', 'ReactJs', 'AngularJs'],
                datasets: [
                    {
                    backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
                    data: [40, 20, 80, 10]
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
        //this.getData();
    },
    methods:{
        getData(){
            axios.get("http://localhost:8080/book/"+this.isbn+"/stats/pie&category="+this.category+"&default="+this.default).then(resp=>{
                this.dados.datasets.data = resp.data.data;
                this.dados.labels = resp.data.labels;
            }).catch(error=>{
                console.log(error);
            })
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