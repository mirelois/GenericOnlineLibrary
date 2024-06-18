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
	  	<div class="confirm-position" v-if="showConfirm==true">
				<ConfirmComponent @confirmation_response="book_management" :header_msg="confirm_msg"></ConfirmComponent>
		</div>
		<div class="bookpagecomponent-child"></div>
    </div>
	<img class="capa-icon" alt="" :src="imageurl">
	<div>
			<div class="titulo">{{ title }}</div>
    		<div class="autor">by {{ author }}</div>
			<div class="group-categories">
				<span v-for="genre in genres">
					<div class="rectangle-div" v-if="!genre" @click="navigateToCategory(genre)">
        			{{ genre }}	
	      			</div>
				</span>
			</div>
    		<div class="descricao"> {{ description }}</div>
			<div class="group-stars">
				<Rating id="estrelas" v-model="ratingAverage" readonly :cancel="false" />
			</div>
			<div class="classificacao">{{ ratingAverage }}/5</div>
    		<div class="nr-rates">{{ nrratings }} ratings</div>
    		<div class="nr-reviews">{{ nrreviews }} reviews</div>
			<div v-show="showPopup==true">
		  		<ToastComponent :msg="msg" @close_toast="showPopup=false"></ToastComponent>
			</div>
			<div>
				<StateComponent v-if="username!==''" @choosen_state="showConfirm=true" :stateValue ="stateSelected" @bookStateSelected="getBookState"></StateComponent>
			</div>
			<AddToBookshelfComponent v-if="username!==''" @click="showAddtoBookshelf=true"></AddToBookshelfComponent>			
			<div v-if="showAddtoBookshelf==true">
				<BookshelfSelectorComponent :bookISBN="isbn" :username="username" @closebookshelvesoptions="showAddtoBookshelf=false"></BookshelfSelectorComponent>
			</div>
    		<div class="separator">
    		</div>
			
	<div v-bind:style="{ 'color': reviewcolor, 'font-weight': reviewfont }" @click="changeTabStyle(`Reviews`)" class="reviews-title">Reviews 
			<img class="line-icon" alt="" src="/img/line.svg"> 
			<div v-if="activeTab=='Reviews'">
			<MyReviewComponent :canInteract="can_interact" @newpost="getReviews(this.isbn,1)" :username="username" :profileImg="profileImg" :isbn="isbn"></MyReviewComponent>
			<div class="review-div" v-for="review in reviews" v-if="!review">
					<ReviewComponent @review_deletion="getReviews(this.isbn,1)" :options="review.myown" :canInteract="can_interact" :isbn="isbn" :marginReviewBottom="marginReviewBottom" @expandHeight="increaseHeight" :likesCount="review.likes" :emojiIds="review.emojiIds" :reviewRate="review.rating" :reviewDescription="review.description"
					:imageReviewer="review.customerUrl" :usernameReviewer="review.customerUsername"></ReviewComponent>					
			</div>
			<div v-show="showLoading==true">
					<img alt="" width="30px" height="30px" src="/img/kOnzy.gif">
				</div>
			<div v-if="showMoretxt==true" :style="{'margin-top':loadtxt_margintop}" @click="getMoreReviews" class="loadtxt">Load More Reviews
			</div>
			</div>
    		</div>
	<div v-bind:style="{ 'color': authorcolor, 'font-weight': authorfont }" @click="changeTabStyle(`Author`)" class="author-title">Author 
			<div class="author-content" v-if="activeTab=='Author'">
				<img :src="authorImage" class="authorImage"/>
				<br>Author: {{ authorName }}</br>
				<br>Publisher: {{ publisher }}</br>
				<br>Launch Date: {{ launchDate }}</br>
		</div>
    	</div>
	<div v-bind:style="{ 'color': statisticscolor, 'font-weight': statisticsfont }" @click="changeTabStyle(`Statistics`)" class="statistics-title">Statistics 
			<div class="author-content" v-if="activeTab=='Statistics'">
					<br><div class="stats-row"><CategorySelectorComponent @category_selected="setCategorySelected"></CategorySelectorComponent><DefaultSelectorComponent @default_bookshelf="setDefaultSelected"></DefaultSelectorComponent></div></br>
					<br><div class="stats-row"><PieStatsComponent :categoria="categorySelected" :bookshelf="defaultSelected" :isbn="isbn"></PieStatsComponent><StateFrequencyComponent :isbn="isbn"></StateFrequencyComponent></div></br>
					<br><TimeFrameComponent @time_frame="setTimeFrameSelected"></TimeFrameComponent></br>
					<div class="stats-row"><LineGraphComponent :timeframe="timeframeSelected" :bookshelf="defaultSelected"></LineGraphComponent></div>	
			</div>
    		</div>
	</div>


	<NavComponent :username="username"></NavComponent>
	<FooterComponent></FooterComponent>
</template>
<script>
import StateComponent from '../components/StateComponent.vue';
import axios from "axios";
import ConfirmComponent from '@/components/ConfirmComponent.vue';
import AddToBookshelfComponent from '@/components/AddToBookshelfComponent.vue';
import BookshelfSelectorComponent from '@/components/BookshelfSelectorComponent.vue';
import authHeader from '@/services/auth.header';
import ToastComponent from '@/components/ToastComponent.vue'
import PieStatsComponent from '@/components/PieStatsComponent.vue';
import StateFrequencyComponent from '@/components/StateFrequencyComponent.vue';
import LineGraphComponent from '@/components/LineGraphComponent.vue';
import CategorySelectorComponent from '@/components/CategorySelectorComponent.vue';
import DefaultSelectorComponent from '@/components/DefaultSelectorComponent.vue';
import TimeFrameComponent from '@/components/TimeFrameComponent.vue';
export default {
	data(){
		return{
			title:"",
			author:"",
			publisher:"",
			launchDate:"",
			genres:["None"],
			description:"",
			imageurl:"",
			reviewcolor: "#ffffff",
			reviewfont: "bold",
			authorcolor: "#5d5d5e",
			authorfont: "normal",
			statisticscolor: "#5d5d5e",
			statisticsfont: "normal",
			activeTab:"Reviews",
			stateSelected:"Want To Read",
			ratingAverage:0.0,
			reviews:[],
			nrreviews:0,
			nrratings:0,
			username:'',
			profileImg: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png',
			isbn:'',
			nrpageReview:0,
			msg:'',
			showLoading:false,
			showMoretxt:true,
			showPopup:false,
			showConfirm:false,
			showAddtoBookshelf:false,
			confirm_msg:'Are you sure you want to add the book to Want to Read ?',
			loadtxt_margintop:"20px",
			marginReviewBottom:"10px",
			can_interact:true,
			authorName:'',
			authorImage:'',
			categorySelected:'',
			defaultSelected:'',
			timeframeSelected:'',
			loginMsg:'Log in to your account to see this information.'
		}
	},
	components: {
        NavComponent,
		Rating,
		StateComponent,
		FooterComponent,
		ConfirmComponent,
		AddToBookshelfComponent,
		ToastComponent,
		PieStatsComponent,
		LineGraphComponent,
		CategorySelectorComponent,
		DefaultSelectorComponent,
		TimeFrameComponent
    },created(){
		this.isbn = this.$route.params.bookisbn;
		this.getBook(this.isbn);
		this.getReviews(this.isbn,0);
		const token = localStorage.getItem('user');
		if (!token || this.$store.state.auth.status.loggedIn===false) {
			this.can_interact=false;
			console.warn('User token not found in localStorage');
			return;
		}
		try {
			const decodedToken = JSON.parse(token);
			if(decodedToken.info.exp<Date.now()/1000) {
				this.can_interact=false;
			}
			this.setUsername(decodedToken.info.sub);
		} catch (error) {
			console.error('Error parsing user token:', error);
		}
	},methods:{
		navigateToCategory(category) {
        this.$router.push({ name: 'bookCategoria', params: { category } });
        },
		getBook(isbn){
			axios.get("http://localhost:8080/api/book/"+isbn).then(book =>{
				this.title = book.data.title;
				this.author = book.data.authorUsername;
				this.genres = book.data.genres;
				this.description = book.data.description;
				this.imageurl = book.data.imageUrl;
				this.ratingAverage = book.data.ratingAverage;
				this.publisher = book.data.publisherName;
				this.launchDate = book.data.launchDate;
				console.log(book);
			}).catch(err=>{
				console.log(err)
			})
		},
		checkMyOwnReview(){
			for(let i=0;i<this.reviews.length;i++){
				if(this.reviews[i].customerUsername==this.username){
					this.reviews[i]["myown"]= true;
				}else this.reviews[i]["myown"]= false;
			}
		},
		getReviews(isbn,update){
			if(update==1) this.nrpageReview=0;
			axios.get("http://localhost:8080/api/book/"+isbn+"/review?page="+this.nrpageReview+"&size=1").then(review =>{
				if(review.data.length==0){
					this.showMoretxt=false;
					return;
				}
				if(update==0) this.reviews= this.reviews.concat(review.data);
				else{
					this.reviews = review.data;
					console.log("pssst");
					console.log(review.data);
				} 
				console.log("checkar");
				this.checkMyOwnReview();
				console.log(this.reviews);
				let descreview = this.reviews.filter(b=> b.description!="");
				this.nrreviews = descreview.length;
				this.nrratings = this.reviews.length;
				for (let i = 0; i < this.reviews.length; i++) {
					let ids = ["r"+(5*i+1),"r"+(5*i+2),"r"+(5*i+3),"r"+(5*i+4),"r"+(5*i+5)];
					this.reviews[i]["emojiIds"]=ids;
				}
				console.log("the reviews");
				console.log(review.data);
			}).catch(err=>{
				console.log(err)
			})
		},
		getMoreReviews(){
			let h = this.marginReviewBottom.replace('px','');
            h=parseInt(h)+220+"px";
            this.marginReviewBottom=h;

			this.nrpageReview = this.nrpageReview + 1;
			this.getReviews(this.isbn,0);
			this.showLoading = false;
		},
		changeTabStyle(tab){
			if(tab=="Author"){
				this.authorcolor="#ffffff",
				this.authorfont= "bold",
				this.reviewcolor="#5d5d5e",
				this.reviewfont= "normal",
				this.statisticscolor="#5d5d5e",
				this.statisticsfont= "normal",
				this.activeTab="Author"
				this.getAuthorInfo();
			}
			if(tab=="Reviews"){
				this.reviewcolor="#ffffff",
				this.reviewfont= "bold",
				this.authorcolor="#5d5d5e",
				this.authorfont= "normal",
				this.statisticscolor="#5d5d5e",
				this.statisticsfont= "normal",
				this.activeTab="Reviews"
			}
			if(tab=="Statistics"){
				this.statisticscolor="#ffffff",
				this.statisticsfont= "bold",
				this.authorcolor="#5d5d5e",
				this.authorfont= "normal",
				this.reviewcolor="#5d5d5e",
				this.reviewfont= "normal",
				this.activeTab="Statistics"
			}
		},
		getBookState(state){
			console.log("heerree");
            console.log(state);
            console.log(this.stateSelected);
			this.confirm_msg = 'Are you sure you want to add the book to '+ state+' ?';
			this.stateSelected = state;
		},
		book_management(resp){
			if(resp=='no') this.showConfirm=false;
			else{

				let header = authHeader();
	            let config = {headers:header}
    	        header['Content-Type']='application/json';
        	    let state = this.stateSelected.replaceAll(" ","_").toLowerCase();
				//const date = new Date();
				//const isoDateString = date.toISOString();
				axios.post("http://localhost:8080/api/customer/"+this.username+"/bookshelf/"+state+"?isbn="+this.isbn,
				    {},
                    config 
            	).then(resp =>{
                    if(resp.status==200){
						if(this.$store.state.language.selectedLanguage !== 'portuguese'){
							this.$toast.success("The book was inserted to your "+state+" collection.");
						} else {
							this.$toast.success("O livro foi adicionado à coleção " + state + ".");
						}
                    }else{
						if(this.$store.state.language.selectedLanguage !== 'portuguese'){
							this.$toast.error("Something went wrong.");
						} else{
							this.$toast.error("Aconteceu algo de mal.");
						}
                    }
					this.showConfirm=false;
					console.log(resp)
                    }).catch(err=>{
						this.$toast.error(err.response.data);
                      console.log(err)
                    })
			}
		},
		setUsername(username){
			this.username=username;
		},
		increaseHeight(){
			let h = this.marginReviewBottom.replace('px','');
            h=parseInt(h)+140+"px";
            this.marginReviewBottom=h;
		},
		getAuthorInfo(){
			axios.get("http://localhost:8080/api/customer/username/"+this.author).then(resp=>{
				this.authorName = resp.data.name;
				this.authorImage = resp.data.profileImageUrl;
			}).catch(error=>{
				console.log(error)
			})
		},
		setDefaultSelected(defaultselected){
			this.defaultSelected = defaultselected;
		},
		setCategorySelected(categoryselected){
			this.categorySelected = categoryselected;
		},
		setTimeFrameSelected(timeframeselected){
			this.timeframeSelected = timeframeselected;
		}
	}

}
</script>

<style scoped>
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

.authorImage{
	border-radius: 50%;
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

.loadtxt{
	font-size:20px;
	color:#a5a3a3;
	text-decoration:underline;
}

.stats-row{
	display:inline-flex;
	float: left;
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

.confirm-bookshelfadd {
  width: 100%;
  position: absolute;
  height: 539px;
  text-align: center;
  font-size: 30px;
  color: #000;
  font-family: Michroma;
  z-index: 22;
  margin-top: -500px;
  margin-left: 1000px;
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
	cursor: pointer;
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

.statistics-title{
	position: absolute;
	top: 1044px;
	left: 525px;
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
	color:#a5a3a3;
	font-size: 25px;
}

.p-rating .p-rating-item.p-rating-item-active .p-rating-icon {
    color: #EAE600;
}

body {
	margin-left: -80px !important;
}


</style>