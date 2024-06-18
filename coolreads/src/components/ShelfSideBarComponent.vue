<template>
	<main>
	<div class="shelf-sidebar">
		<b class="title">{{translations.yourbookshelves}}</b>
		<div class="create-bookshelf-btn" id="createBookshelfBtnContainer">
			<button @click="showCreateBookshelfMenu" class="createbs">+ {{translations.createbookshelf}}</button>
			<img class="transfer-icon" alt="" src="/img/transfer.svg">
		</div>
		<div v-for="(bookshelf,index) in mybookshelves" :key="index" class="children-shelves-parent">
			<a class="prateleiralink" :href="`/bookshelves/${bookshelf.name}`">{{ bookshelf.name }}</a>
			<div class="shelves1">{{ bookshelf.bookCount }}</div>
		</div>
	</div>
	<div v-show="showPopup==true">
		<ToastComponent :msg="msg" @close_toast="showPopup=false"></ToastComponent>
	</div>
	<div v-if="showAdddBookshelf==true" id="createBookshelfInfoContainer" class="popup-overlay">
		<div class="create-bookshelf-info">
		<input v-model="bookshelfname" class="enter-bookshelf-name" placeholder="Enter Bookshelf Name"/>
		<div class="create-bookshelf-btn1">
		<button class="createbs1" @click="createBookshelf">CREATE</button>
		<img @click="showCreateBookshelfMenu" class="bookshelfcreate-close" src="/img/close.svg" />
		</div>
		</div>
	</div>
	</main>

</template>
<script>
import axios from "axios";
import ToastComponent from "./ToastComponent.vue";
import authHeader from '@/services/auth.header';

export default{
	props:{
		username:""
	},
	data(){
		return {
			showAdddBookshelf:false,
			showPopup:false,
			bookshelfname:'',
			msg:'',
			mybookshelves:[]
		};
	},
	computed: {
		translations() {
			return this.$store.getters['language/currentTranslations'];
    	},
    	selectedLanguage() {
      		return this.$store.state.language.selectedLanguage;
    	}
	},
	methods:{
		setLanguage(language) {
      		this.$store.dispatch('language/setLanguage', language);
    	},
		showCreateBookshelfMenu(){
			this.showAdddBookshelf= !this.showAdddBookshelf;
			this.bookshelfname="";
		},
		createBookshelf(){
			let header = authHeader();
			if(header=={}) this.handle_logout();
            let config = {headers:header}
            header['Content-Type']='application/json';
			axios.post("http://localhost:8080/api/customer/"+this.username+"/bookshelf",
				{
					name:this.bookshelfname,
					privacy: "public"
				},
				config 
				).then(resp =>{
					if(resp.status==200){
						this.msg="The bookshelf you inserted was created successfully.";
						this.mybookshelves.push({"name":this.bookshelfname,"privacy":"public","bookCount":0})
					} 
					else this.msg = "The bookshelf you inserted already exists";
					this.showPopup = true;
				}).catch(err=>{
					console.log(err)
				})
		},
		getBookshelves(){
			let header = authHeader();
			if(header=={}) this.handle_logout();
			let config = {headers:header}
            header['Content-Type']='application/json';
			axios.get("http://localhost:8080/api/customer/"+this.username+"/bookshelf",config).then(resp =>{
				this.mybookshelves = resp.data.sort((b1,b2)=>b1.name.localeCompare(b2.name));
				console.log(this.mybookshelves);
			}).catch(err=>{
				console.log(err)
			})
		},
		handle_logout(){
            this.$store.dispatch('auth/logout').then(
            () => {
                router.go()
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        } 
	},created(){
		this.getBookshelves();
	},components:{
		ToastComponent
	}
}
</script>
<style scoped>
.bookshelfcreate-close {
  position: absolute;
  margin-left: 50px;
  margin-top: 20px;
  cursor:pointer;
}

.title {
	position: absolute;
	font-size: 26px;
	letter-spacing: 0.03em;
	margin-left: -10px;
	background: #000;
	width: 400px;
	height: 125px;
	text-align: center;
}
.shelves {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 156.7px;
}

.shelves1 {
  	position: absolute;
  	top: 0px;
  	left: 280px;
  	display: inline-block;
	margin-bottom:20px;
  	width: 19px;
	height:30px;
}
.shelves-parent {
  	position: absolute;
  	top: 160px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.prateleiralink{
	color:white;
}
.children-shelves-parent {
	position:relative;
	padding: 0px 50px;
	left:-10px;
	top: 150px;
	height:40px;
	width:400px;
	background: #000;
}

.shelves3 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
}
.shelves-group {
  	position: absolute;
  	top: 200px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves5 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
	height:30px;
}
.shelves-container {
  	position: absolute;
  	top: 242px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves7 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
  	height: 25px;
}

.enter-bookshelf-name::-webkit-input-placeholder {
    color: white;
}
.enter-bookshelf-name:-moz-placeholder {
    color: white;
}
.enter-bookshelf-name::-moz-placeholder {
	color: white;
}

.enter-bookshelf-name:-ms-input-placeholder {
    color: white;
}

.enter-bookshelf-name::placeholder {
    color: white;
}

.group-div {
  	position: absolute;
  	top: 195px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves-parent1 {
  	position: absolute;
  	top: 227px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves10 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
  	cursor: pointer;
	height:30px;
}

.shelves-parent2 {
  	position: absolute;
  	top: 120px;
  	left: 41px;
	height:30px;
  	width: 262px;
  	height: 26px;
  	cursor: pointer;
}
.create-list-rectangle {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  	border-radius: 6px;
  	background-color: #e1e1e1;
}
.createbs {
	position: absolute;
	height: 40px;
	width: 73.32%;
	top: 25%;
	left: 12%;
	letter-spacing: 1px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 18px;
	cursor:pointer;
}
.transfer-icon {
  	position: absolute;
  	height: 29.23%;
  	width: 6.68%;
  	top: 37.5%;
  	right: 88.08%;
  	bottom: 33.27%;
  	left: 5.24%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
}
.create-bookshelf-btn {
  	position: absolute;
  	top: 60px;
  	left: 41px;
  	width: 262px;
  	height: 39px;
  	text-align: center;
  	color: #000;
}

.shelf-sidebar {
	width: 400px;
	position: absolute;
	border-radius: 5px;
	background: #000;
	text-align: left;
	font-size: 20px;
	color: #fff;
	font-family: Inika;
	padding: 10px;
	float: left;
	left: 30px; 
	top: 200px;
}

.menusclose-line-round-icon {
  	position: absolute;
  	top: 18px;
  	left: 16px;
  	width: 35px;
  	height: 35px;
  	overflow: hidden;
}
.enter-bookshelf-name {
	width: 300px;
	height: 30px;
	font-size: 22px;
	padding-left: 35px;
	border-radius: 4px;
	border-width: 0px;
	overflow: hidden;
	margin-top: 20px;
	margin-left: 20px;
	background-color: #0f0f0f;
	color: white;
}
.create-list-rectangle1 {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  	border-radius: 6px;
  	background-color: #e1e1e1;
}
.createbs1 {
  	position: absolute;
  	height: 50%;
  	width: 100%;
  	top: 25%;
  	left: 0%;
  	display: flex;
  	align-items: center;
  	justify-content: center;
	cursor:pointer;
}

.create-bookshelf-btn1 {
	position: absolute;
	left: 326px;
	width: 79px;
	height: 80px;
	text-align: center;
	font-size: 16px;
	color: #000;
	top: -5px;
}
.create-bookshelf-info {
	width: 500px;
	position: fixed;
	border-radius: 20px;
	background-color: #0f0f0f;
	height: 70px;
	overflow: hidden;
	max-width: 90%;
	max-height: 90%;
	text-align: left;
	font-size: 24px;
	color: #bababa;
	font-family: Inika;
	margin-left: -800px;
	margin-top: 350px;
}

</style>