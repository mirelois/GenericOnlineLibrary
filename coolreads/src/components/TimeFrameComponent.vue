<template>
    <main>
        <div class="dropdown">
        <button class="dropbtn" :value="timeframeSelected">{{ timeframeSelected }}</button>
        <div class="dropdown-content">
            <div @click="changeValue(`Day`)" id="row">Day</div>
            <div @click="changeValue(`Week`)" id="row">Week</div>
            <div @click="changeValue(`Month`)" id="row">Month</div>
            <div @click="changeValue(`Year`)" id="row">Year</div>
        </div>
        </div>

    </main>
</template>
<script>
export default {
    data(){
        return {
            timeframeSelected:'Time Frame'
        }
    },
    methods:{
        changeValue(timeframe){
            this.timeframeSelected=timeframe;
            this.$emit("time_frame",this.timeframeSelected);
        }
    }

}
</script>
<style scoped>
.dropbtn {
  background-color: #808080;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  border-radius: 20px;
  cursor:pointer;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content #row {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content #row:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: rgb(39, 39, 39);}

</style>