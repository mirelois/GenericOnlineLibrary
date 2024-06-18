<template>
    <main>
        <div class="dropdown">
        <button class="dropbtn" :value="bookshelfSelected">{{ bookshelfSelected }}</button>
        <div class="dropdown-content">
            <div v-for="(key, index) in myoptions" :key="index" @click="changeValue(key)" id="row">{{key.name}}</div>
        </div>
        </div>

    </main>
</template>
<script>
import authHeader from '@/services/auth.header';
import axios from "axios"
export default {
    props:{
        username:''
    },
    data(){
        return {
            bookshelfSelected:'Select Your Highlighted Bookshelf',
            myoptions:[]
        }
    },
    methods:{
        changeValue(option){
            this.bookshelfSelected=option.name;
            this.$emit("bookshelf_highlighted",option);
        },
        getPublicBookshelves(){
            let header = authHeader();
			let config = {headers:header}
            header['Content-Type']='application/json';
			axios.get("http://localhost:8080/customer/"+this.username+"/bookshelf",config).then(resp =>{
                for(let i=0;i<resp.data.length;i++){
                if(resp.data[i].privacy=="PUBLIC"){
                    this.myoptions.push(resp.data[i])
                }
                }
            console.log(this.myoptions);
            return this.myoptions;
                }).catch(err=>{
                    console.log(err)
                })
        }
        
    },
    created(){
        this.getPublicBookshelves();
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
  margin-left: 600px;
  margin-top: 10px;
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