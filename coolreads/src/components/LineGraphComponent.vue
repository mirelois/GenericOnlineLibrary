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
            <Line :data="data" :options="options" />
        </div>
    </main>
</template>
<script>
export default {
    components:{
        Line
    },
    data(){
        return {
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [
                    {
                        label: 'Reading Over Time',
                        backgroundColor: '#f87979',
                        data: [40, 39, 10, 40, 39, 80, 40],
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