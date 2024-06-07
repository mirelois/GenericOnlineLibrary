<template>
	<main>
	<div class="shelf-sidebar">
		<b class="title">Your Bookshelves</b>
		<div class="create-bookshelf-btn" id="createBookshelfBtnContainer">
			<button @click="showCreateBookshelfMenu" class="createbs">+ Create Bookshelf</button>
			<img class="transfer-icon" alt="" src="/img/transfer.svg">
		</div>
		<div class="shelves-parent">
			<div class="shelves"><a href="/bookshelves/want_to_read">Want To Read</a></div>
			<div class="shelves1">-</div>
		</div>
		<div class="shelves-group">
			<div class="shelves1">-</div>
			<div class="shelves3"><a href="/bookshelves/currently_reading">Currently Reading</a></div>
		</div>
		<div class="shelves-container">
			<div class="shelves1">-</div>
			<div class="shelves5"><a href="/bookshelves/read">Read</a></div>
		</div>
		<div class="shelves-parent2" id="groupContainer5">
			<div class="shelves10" id="shelvesText">All</div>
			<div class="shelves1">-</div>
		</div>
		<div v-for="(bookshelf,index) in mybookshelves" :key="index" class="children-shelves-parent">
			<div class="shelves1">-</div>
			<a :href="`/bookshelves/${bookshelf.name}`">{{ bookshelf.name }}</a>
		</div>
	</div>
	<div v-show="showPopup" id="popup1" class="overlay">
	<div class="popup">
		<h2>{{ msg }}</h2>
		<a class="close" @click="showPopup=false" href="#">&times;</a>
	</div>
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
export default{
	props:{
		username:"",
		profileImg:""
	},
	data(){
		return {
			showAdddBookshelf:false,
			showPopup:false,
			bookshelfname:'',
			msg:'',
			mybookshelves:[]
		}
	},methods:{
		showCreateBookshelfMenu(){
			this.showAdddBookshelf= !this.showAdddBookshelf;
			this.bookshelfname="";
		},
		createBookshelf(){
			console.log(this.bookshelfname);
			const headers = {
        		'Content-Type': 'application/json',
		    };
			axios.post("http://localhost:8080/customer/"+this.username+"/bookshelf",
				{
					name:this.bookshelfname,
					privacy: "public"
				},
				{ headers: headers } 
				).then(resp =>{
					if(resp.status==200){
						this.msg="The bookshelf you inserted was created successfully.";
						this.mybookshelves.push({"name":this.bookshelfname,"privacy":"public","nr":0})
					} 
					else this.msg = "The bookshelf you inserted already exists";
					this.showPopup = true;
				}).catch(err=>{
					console.log(err)
				})
		},
		getBookshelves(){
			axios.get("http://localhost:8080/customer/"+this.username+"/bookshelf").then(resp =>{
				this.mybookshelves = resp.data;
				console.log(this.mybookshelves);
			}).catch(err=>{
				console.log(err)
			})
		}
	},created(){
		this.getBookshelves();
	}
}
</script>
<style>
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
	margin-left: 40px;
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
  	left: 243px;
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

.children-shelves-parent {
	position:relative;
	top: 280px;
	left: 31px;
	height:40px;
	margin-bottom:5px;
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
	height: 100%;
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
	margin-top: -350px;
}

.overlay {
  position: fixed;
  top: 300px;
  bottom: 0;
  left: 0;
  right: 0;
  transition: opacity 500ms;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  transition: all 5s ease-in-out;
  z-index:30;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #111111;
}
</style>