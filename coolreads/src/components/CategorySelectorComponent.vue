<template>
    <main>
        <div class="dropdown">
        <button class="dropbtn" :value="categorySelected">{{ categorySelected }}</button>
        <div class="dropdown-content">
            <div @click="changeValue(`Age`)" id="row">Age</div>
            <div @click="changeValue(`Gender`)" id="row">Gender</div>
            <div @click="changeValue(`Country`)" id="row">Country</div>
        </div>
        </div>

    </main>
</template>
<script>
export default {
    data(){
        return {
            categorySelected:'Category'
        }
    },
    methods:{
        changeValue(category){
            this.categorySelected=category;
            this.$emit("category_selected",this.categorySelected);
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