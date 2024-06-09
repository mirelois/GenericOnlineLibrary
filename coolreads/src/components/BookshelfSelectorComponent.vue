<template>
    <main>
    <div class="bookshelves-options">
    <img @click="closeBookshelfOptions" class="close-bookshelves-options" src="/img/close.svg" />
    <label v-for="option in options" class="container">{{option.name}}
        <input type="checkbox" :id="option.name" :value="option.name" v-model="checkedOptions">
        <span class="checkmark"></span>
    </label>
    <label class="container"><button class="insert-bookshelf-btn" @click="insertBook">INSERT</button></label>
    <div v-show="showPopup" id="popup1" class="overlay">
    <div class="popup">
      <h2>{{ msg }}</h2>
      <a class="close" @click="showPopup=false" href="#">&times;</a>
    </div>
    </div>

</div>
</main>
</template>
<script>
import axios from "axios";
import { ref } from 'vue'
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
    ,
    methods:{
        getOptions(){
            axios.get("http://localhost:8080/customer/"+this.username+"/bookshelf").then(resp =>{
				this.options = resp.data;
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
			      const headers = {
        		  'Content-Type': 'application/json',
		        };
            const date = new Date();
			      const isoDateString = date.toISOString();
            this.checkedOptions.forEach(bookshelf => {
                axios.post("http://localhost:8080/customer/"+this.username+"/bookshelf/"+bookshelf,
				            {
                      pagesRead:0,
                      insertDate:isoDateString,
                      bookISBN: this.bookISBN
				            },
                    { headers: headers } 
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