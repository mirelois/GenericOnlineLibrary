<template>
    <main>
    <div class="bookshelves-options">
    <img @click="closeBookshelfOptions" class="close-bookshelves-options" src="/img/close.svg" />
    <label v-for="option in options" class="container">{{option.name}}
        <input type="checkbox" :id="option.name" :value="option.name" v-model="checkedOptions">
        <span class="checkmark"></span>
    </label>
    <label v-if="options.length==0" class="container">You have no public bookshelves yet.<br> Go create some!</label>
    <label class="container"><button class="insert-bookshelf-btn" @click="insertBook">INSERT</button></label>
    <div v-show="showPopup==true">
		  <ToastComponent :msg="msg" @close_toast="showPopup=false"></ToastComponent>
	  </div>
</div>
</main>
</template>
<script>
import axios from "axios";
import { ref } from 'vue';
import ToastComponent from "./ToastComponent.vue";
import authHeader from '@/services/auth.header';
export default {
    props:{
        username:'',
        bookISBN:''
    },
    data(){
        return {
            options:[],
            checkedOptions: ref([]),
            msg:'',
            showPopup:false
        }
    }
    ,components:{
      ToastComponent
    },
    methods:{
        getOptions(){
            let header = authHeader();
            let config = {headers:header}
            header['Content-Type']='application/json';
            axios.get("http://localhost:8080/api/customer/"+this.username+"/bookshelf",config).then(resp =>{
              for(let i=0;i<resp.data.length;i++){
                if(resp.data[i].name!="did_not_finish" && resp.data[i].name!="want_to_read" && resp.data[i].name!="currently_reading" && resp.data[i].name!="already_read" && resp.data[i].name!="all") this.options.push(resp.data[i]);
              }
			}).catch(err=>{
				console.log(err)
			})
        },
        closeBookshelfOptions(){
            this.$emit('closebookshelvesoptions');
        },
        insertBook(){
            console.log("selecionados:");
            console.log(this.checkedOptions);
            let header = authHeader();
            let config = {headers:header}
            header['Content-Type']='application/json';
            this.checkedOptions.forEach(bookshelf => {
                axios.post("http://localhost:8080/api/customer/"+this.username+"/bookshelf/"+bookshelf+"?isbn="+this.bookISBN,
				            {},
                    config 
            				).then(resp =>{
                      if(resp.status==200){
                        this.msg="The book was inserted successfully.";
                      }else{
                        this.msg="Something went wrong."
                      }
                    }).catch(err=>{
                      console.log(err)
                    })                
                });
            this.showPopup = true;
            }
      },
      created(){
          this.getOptions();
      }
}

</script>
<style>
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  margin-left:10px;
  color:white;
}

.close-bookshelves-options{
  cursor:pointer;
  margin-left:250px;
  margin-top:5px;
}

.insert-bookshelf-btn {
  color: white;
  background-color: black;
  height: 50px;
  width: 100px;
  font-size: 18px;
  border-radius: 10px;
  margin-top: 25px;
  cursor:pointer;
}

.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

.container:hover input ~ .checkmark {
  background-color: #ccc;
}

.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

.container input:checked ~ .checkmark:after {
  display: block;
}

.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

.bookshelves-options {
  background-color: #373636;
  width: 300px;
  position: absolute;
  border-radius: 20px;
  margin-top: -2700px;
  margin-left: 1000px;
  z-index: 22;
}
</style>