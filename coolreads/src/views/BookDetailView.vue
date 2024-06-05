<script setup>
import ReviewComponent from '../components/ReviewComponent.vue';
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import MyReviewComponent from '../components/MyReviewComponent.vue';
import Rating from 'primevue/rating';
</script>
<template>
	<div class="bookpagecomponent">
    	<div class="canva-brown-rusty-mystery-nove-parent">
    		<img class="canva-brown-rusty-mystery-nove-icon" alt="" :src="imageurl">
        		<div class="frame-child"></div>
      	</div>
		<div class="bookpagecomponent-child"></div>
    </div>
	<img class="capa-icon" alt="" :src="imageurl">
	<div>
			<div class="titulo">{{ title }}</div>
    		<div class="autor">by {{ author }}</div>
			<div class="group-categories">
				<span v-for="genre in genres" v-if="!genre">
					<div class="rectangle-div">
        			{{ genre }}	
	      			</div>
				</span>
			</div>
    		<div class="descricao"> {{ description }}</div>
			<div class="group-stars">
				<Rating id="estrelas" v-model="bookrate" readonly :cancel="false" />
			</div>
			<div class="classificacao">{{ ratingAverage }}/5</div>
    		<div class="nr-rates">{{ nrratings }} ratings</div>
    		<div class="nr-reviews">{{ nrreviews }} reviews</div>
			<div>
				<StateComponent :stateValue ="stateSelected" @bookStateSelected="getBookState"></StateComponent>
			</div>
    		<div class="separator">
    		</div>
			
	<div v-bind:style="{ 'color': reviewcolor, 'font-weight': reviewfont }" @click="changeTabStyle(`Reviews`)" class="reviews-title">Reviews 
			<img class="line-icon" alt="" src="/img/line.svg"> 
			<div v-if="activeTab=='Reviews'">
			<MyReviewComponent :username="username" :isbn="isbn"></MyReviewComponent>
			<div class="review-div" v-for="review in reviews" v-if="!review">
					<ReviewComponent :reviewRate="review.rating" :reviewDescription="review.description"
					:imageReviewer="review.customerUrl" :usernameReviewer="review.customerUsername"></ReviewComponent>					
			</div>
			</div>
    		</div>
	<div v-bind:style="{ 'color': authorcolor, 'font-weight': authorfont }" @click="changeTabStyle(`Author`)" class="author-title">Author 
			<div class="author-content" v-if="activeTab=='Author'">
				author info
			</div>
    		</div>
	</div>


	<NavComponent></NavComponent>
	<FooterComponent></FooterComponent>
</template>
<script>
import StateComponent from '../components/StateComponent.vue';
import axios from "axios";
export default {
	data(){
		return{
			bookrate:3,
			title:"",
			author:"",
			genres:["None"],
			description:"",
			imageurl:"",
			reviewcolor: "#ffffff",
			reviewfont: "bold",
			authorcolor: "#5d5d5e",
			authorfont: "normal",
			activeTab:"Reviews",
			stateSelected:"+ Want To Read",
			ratingAverage:0.0,
			reviews:[],
			nrreviews:0,
			nrratings:0,
			username:'techguru',
			isbn:'',
			nrpageReview:0
		}
	},
	components: {
        NavComponent,
		Rating,
		StateComponent,
		FooterComponent
    },created(){
		this.isbn = this.$route.params.bookisbn;
		this.getBook(this.isbn);
		this.getReviews(this.isbn);
	},methods:{
		getBook(isbn){
			axios.get("http://localhost:8080/book/"+isbn).then(book =>{
				this.title = book.data.title;
				this.author = book.data.authorUsername;
				this.genres = book.data.genres;
				this.description = book.data.description;
				this.imageurl = book.data.imageUrl;
				this.ratingAverage = book.data.ratingAverage;
				console.log(book);
			}).catch(err=>{
				console.log(err)
			})
		},
		getReviews(isbn){
			axios.get("http://localhost:8080/book/"+isbn+"/review?page="+this.nrpageReview+"&size=5").then(review =>{
				this.reviews=review.data
				let descreview = this.reviews.filter(b=> b.description!="");
				this.nrreviews = descreview.length;
				this.nrratings = this.reviews.length;
				console.log(review.data)
			}).catch(err=>{
				console.log(err)
			})
		},
		changeTabStyle(tab){
			if(tab=="Author"){
				this.authorcolor="#ffffff",
				this.authorfont= "bold"
				this.reviewcolor="#5d5d5e",
				this.reviewfont= "normal"
				this.activeTab="Author"
			}
			if(tab=="Reviews"){
				this.reviewcolor="#ffffff",
				this.reviewfont= "bold"
				this.authorcolor="#5d5d5e",
				this.authorfont= "normal"
				this.activeTab="Reviews"
			}
		},
		getBookState(state){
			console.log("heerree");
            console.log(state);
            console.log(this.stateSelected);
			this.stateSelected = "+ "+state;
		}
	}

}
</script>

<style>
@media (min-width: 1024px) {
  #app {
    padding: 0;
  }
}
#app {
  max-width: 3000px;
  font-weight: normal;
}
#estrelas{
	left:30px;
	top:10px;
}

.canva-brown-rusty-mystery-nove-icon {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 3000px;
	height: 1078px;
	object-fit: cover;
}

.review-div{
	padding-bottom: 20px;
}

.frame-child {
	position: absolute;
	top: 0px;
	left: 0px;
	background: linear-gradient(-0.12deg, #000, rgba(153, 153, 153, 0) 99.98%, rgba(89, 89, 89, 0.59) 99.99%);
	width: 3000px;
	height: 1078px;
	overflow: hidden;
}

.canva-brown-rusty-mystery-nove-parent {
	width: 3000px;
	position: relative;
	height: 1170px;
	overflow: auto;
	flex-shrink: 0;
}

.bookpagecomponent-child {
	width: 3000px;
	position: relative;
	background-color: #000;
	height: 2321px;
	margin-top: -108px;
}

.bookpagecomponent {
	width: 3000px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	justify-content: flex-start;
} 

.capa-icon {
  	position: absolute;
  	top: 431px;
  	left: 148px;
  	border-radius: 25px;
  	width: 291px;
  	height: 445px;
  	object-fit: cover;
}
.titulo {
	position: absolute;
	top: 400px;
	left: 485px;
	font-size: 70px;
	display: inline-block;
	width: auto;
	height: 185px;
	color: white;
}

.autor {
  	position: absolute;
  	top: 529px;
  	left: 485px;
  	font-size: 48px;
	color: white;
}
.descricao {
  	position: absolute;
  	top: 775px;
  	left: 486px;
  	font-family: 'Jacques Francois';
  	white-space: pre-wrap;
  	display: inline-block;
  	width: 1051px;
  	height: 207px;
	color: white;
}
.star-icon {
  	position: absolute;
  	top: 3px;
  	left: 0px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon1 {
  	position: absolute;
  	top: 3px;
  	left: 48px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon2 {
  	position: absolute;
  	top: 2px;
  	left: 96px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon3 {
  	position: absolute;
  	top: 0px;
  	left: 144px;
  	width: 35px;
  	height: 33.1px;
  	object-fit: cover;
}
.star-icon4 {
  	position: absolute;
  	top: 0px;
  	left: 192px;
  	width: 35px;
  	height: 33.1px;
  	object-fit: cover;
}
.group-stars {
  	position: absolute;
  	top: 625px;
  	left: 485px;
  	height: 33.1px;
	display: inline-block;
}

.classificacao {
  position: absolute;
  top: 615px;
  left: 655px;
  font-size: 36px;
  display: inline-block;
  width: 108px;
  height: 51px;
  color: white;
  font-weight:bold;
}

.nr-rates {
  	position: absolute;
  	top: 629px;
  	left: 859px;
	color: white;
	font-size: 20px;
}
.nr-reviews {
  	position: absolute;
  	top: 629px;
  	left: 1009px;
	color: white;
	font-size: 20px;
}

.separator {
  	position: absolute;
  	top: 643px;
  	left: 970px;
  	border-radius: 50%;
  	background-color: #d9d9d9;
  	width: 5px;
  	height: 5px;
}

.sophie-mayer {
  	color: #fff;
}
.group-categories {
	color: #e1e1e1;
	font-family: Lato;
	position: absolute;
	top: 700px;
	left: 488px;
	font-size: 30px;
	display: flex;
	width: 108px;
	height: 51px;
}

.rectangle-div {
	display: inline-block; 
	position: relative;
	border-radius: 4px;
	background-color: rgba(26, 26, 26, 0);
	border: 1px solid #a5a3a3;
	box-sizing: border-box;
	height: 41px;
	margin: 10px;
	align-items: center;
	display: flex;
	justify-content: center;
	padding:10px;
}

.reviews-title {
	position: absolute;
	top: 1044px;
	left: 155px;
	font-size: 32px;
	font-weight: 600;
	font-family: 'Open Sans';
	display: inline-block;
	width: 238px;
	height: 61px;
	cursor:pointer;
} 

.author-title{
	position: absolute;
	top: 1044px;
	left: 355px;
	font-size: 32px;
	font-weight: 600;
	font-family: 'Open Sans';
	display: inline-block;
	width: 238px;
	height: 61px;
	cursor:pointer;
}

.author-content {
	margin-top: 140px;
	margin-left: -200px;
}

.p-rating .p-rating-item.p-rating-item-active .p-rating-icon {
    color: #EAE600;
}

body {
	margin-left: -80px !important;
}


</style>