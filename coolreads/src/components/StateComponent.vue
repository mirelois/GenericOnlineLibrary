<template>
    <div class="state-component">
        <div class="listbox-title">
            <img class="statedown-icon-d" @click='showMenu' alt="" src="/img/downdroplist.svg">
            </div>
            <div class="listbox-main">
            <button @click="choosen_state" class="listboxbg">{{stateValue}}
            </button>
        <div v-if="showStateMenu===true">
            <StateSelectorComponent @optionStateSelected="optionofStateSelected"></StateSelectorComponent>
        </div>
        </div> 
    </div>
</template>
<script>
import StateSelectorComponent from './StateSelectorComponent.vue';
export default{
    props: {
        stateValue: ''
    },
    data(){
        return {
            showStateMenu:false,
        }
    },methods:{
        showMenu(){
            this.showStateMenu = !this.showStateMenu
        },
        optionofStateSelected(state){
            this.$emit('bookStateSelected', state);
        },
        choosen_state(){
            this.$emit('choosen_state', this.stateValue);
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
    color: black;
    align-content: center;
    display: flex;
    justify-content: center;
    font-size: 20px;
    padding-top: 11px;
    padding-right: 11px;
}
.listboxbg:active{
    transform: scale(0.98); 
    box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24); 
}
.listboxbg:hover {
    cursor:pointer;
}
.statedown-icon-d {
  position: absolute;
  height: 35px;
  width: 25px;
  top: 370%;
  right: 4%;
  max-width: 100%;
  max-height: 100%;
  z-index: 11;
  padding-left: 5px;
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