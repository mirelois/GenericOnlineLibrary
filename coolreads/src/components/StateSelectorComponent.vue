<template>
    <main>
        <div v-if="close_state_selector==false" class="state-card">
          
          <div class="select-the-state-container">
            <p class="select-the-state">Select the state for this book</p>
          </div> 
                <div>
                  <button class="state-book-row" @click="selectState(`Want To Read`)">
                    Want to read
                </button>
                </div>
                <div>
                  <button class="state-book-row" @click="selectState(`Currently Reading`)">
                    Currently Reading
                </button>
                </div>
                <div>
                  <button class="state-book-row" @click="selectState(`Read`)">
                    Read
                </button>
                </div>
                <img @click="closeSearchBox" class="menus-close-line" src="/img/close.svg" />
          </input>
        </div>
    </main>
</template>
<script>
  export default {
    data(){
      return{
        close_state_selector:false
      }
    },
    methods: {
      closeSearchBox(){
        this.close_state_selector = !this.close_state_selector
      },
      selectState(state){
        this.$emit('optionStateSelected', state);
        this.close_state_selector=true;
      }
    }
  }
</script>
<style>
.select-the-state {
  margin: 0;
}
.select-the-state-container {
  position: absolute;
  top: 15px;
  left: 60px;
  font-size: 24px;
  color: #fff;
} 

.state-card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: black;
  width: 500px;
  margin-top: 30%;
  border-radius: 14px;
  position: relative;
  z-index: 20;
  padding-left: 120px;
  height: 300px;
}

.state-card .state-book-row {
  position: relative;
  row-gap: 5px;
  
}

.state-book-row {
    text-align: center;
    font-size: 20px;
    color: #242424;
    background-color: #b1b1b1;
    margin: 10px;
    border-radius: 15px;
    width: 250px;
    height: 49px;
    border-width: 0px 0px 0px 0px;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}
.state-card .overlap-group {
  position: absolute;
  width: 1012px;
  height: 100px;
  top: 46px;
  left: 115px;
  background-color: #ffffff;
  border-radius: 25px;
}
.state-card .text-wrapper {
  position: absolute;
  top: 45px;
  left: 119px;
  font-family: "Inika-Regular", Helvetica;
  font-weight: 400;
  color: #959595;
  font-size: 35px;
  letter-spacing: 0;
  line-height: normal;
}


.menus-close-line {
  position: absolute;
  width: 40px;
  height: 40px;
  top:20px;
  left:440px;
  cursor: pointer;
  z-index: 22;
}
</style>