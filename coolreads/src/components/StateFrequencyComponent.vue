
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
export default {
    data(){
        return {
            labels: ['Currently Reading', 'Already Read', 'Did Not Finish', 'Want To Read'],
            frequencies: [40, 20, 80, 10]      
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