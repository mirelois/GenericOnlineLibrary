<template>
<div class="state-component">
        <div class="listbox-title">
            <img class="statedown-icon-d" @click='showMenu' alt="" src="/img/downdroplist.svg">
            </div>
            <div class="listbox-main">
            <div class="listboxbg">
            </div>
        <div v-if="showStateMenu===true">
            <StateSelectorComponent></StateSelectorComponent>
        </div>
        </div>    
    </div>
</template>
<script>
import StateSelectorComponent from './StateSelectorComponent.vue';
export default{
    data(){
        return {
            showStateMenu:false,
        }
    },methods:{
        showMenu(){
            this.showStateMenu = !this.showStateMenu
        }
    },components:{
        StateSelectorComponent
    }
}
</script>
<style>

.listbox-title {
  position: absolute;
  font-family: "Inika-Regular", Helvetica;
  height: 6.92%;
  width: 90.91%;
  top: -20px;
  right: 4.55%;
  bottom: 93.08%;
  left: 4.55%;
  font-size: 25px;
}
.listboxbg {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.1);
  	border-radius: 15px;
    background-color: #c2c2c2;
}
.statedown-icon-d {
    position: absolute;
    height: 30px;
    width: 30px;
    top: 370%;
    right:4%;
    bottom: 45%;
    max-width: 100%;
    max-height: 100%;
    z-index:11;
}

.statedown-icon-d:hover {
    cursor:pointer;
}

.order-by-placeholder {
  	position: absolute;
  	top: 0%;
  	left: 0%;
    font-size: 18px;
}
.placeholder-text {
  	position: absolute;
  	height: 33.33%;
  	width: 65.71%;
  	top: 33.33%;
  	right: 28.57%;
  	bottom: 33.33%;
  	left: 5.71%;
}
.listbox-main {
  	position: absolute;
  	height: 20.76%;
  	width: 90.91%;
  	top: 9.69%;
  	right: 4.55%;
  	bottom: 69.55%;
  	left: 4.55%;
  	color: #666;
}

.clip-list-child {
  	position: absolute;
  	top: -19px;
  	left: 61px;
  	width: 100px;
  	height: 100px;
  	overflow: hidden;
}

.state-component {
  width: 300px;
  position: absolute;
  height: 260px;
  text-align: left;
  font-size: 16px;
  color: #fff;
  font-family: Montserrat;
  left: 900px;
  top: 680px;
}
</style>