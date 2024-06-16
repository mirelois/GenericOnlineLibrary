<template>
    <main>
        <div class="dropdown">
        <button class="dropbtn" :value="defaultSelected">{{ defaultSelected }}</button>
        <div class="dropdown-content">
            <div @click="changeValue(`All`)" id="row">All</div>
            <div @click="changeValue(`Currently Reading`)" id="row">Currently Reading</div>
            <div @click="changeValue(`Already Read`)" id="row">Already Read</div>
            <div @click="changeValue(`Did Not Finish`)" id="row">Did Not Finish</div>
            <div @click="changeValue(`Want To Read`)" id="row">Want To Read</div>
        </div>
        </div>

    </main>
</template>
<script>
export default {
    data(){
        return {
            defaultSelected:'Default Bookshelf'
        }
    },
    methods:{
        changeValue(namedefault){
            this.defaultSelected=namedefault;
            this.$emit("default_bookshelf",this.defaultSelected);
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