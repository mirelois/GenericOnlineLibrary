<template>
	<main>
    <div class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" :src="profileImg">
		<div class="reviewer-options" @click="changeSettingsOpened">...</div>
		<div v-if="optionsDropdowned==true" class="dropdown-settings-content">
			<div id="row-option" @click="showConfirmation" >Delete</div>
		</div>
        <div class="reviewer">
			<div class="mystar-class">
				<Rating id="estrelas" v-model="bookrate" :cancel="false" />
			</div>
		</div>
        <input type="text" v-model="textreview" class="review-area"></input> 
        <button class="publish" @click="publishReview">Publish</button> 
		<EmojiReactionComponent v-if="ownReview!=null" :canInteract="canInteract" :reviewer="username" :isbn="isbn" :likes="ownReview.likes" :emojiIds="emojiIds"></EmojiReactionComponent>
		<CommentSectionComponent v-if="ownReview!=null" :canInteract="canInteract" :reviewer="username" :isbn="isbn" @comment_opened="expandHeight"></CommentSectionComponent>	
		<div v-if="confirmation==true">
			<ConfirmComponent :header_msg="msg" @confirmation_response="getResponse"></ConfirmComponent>
		</div>
    </div>
	</main>
</template>
<script>
import EmojiReactionComponent from './EmojiReactionComponent.vue';
import CommentSectionComponent from './CommentSectionComponent.vue';
import ConfirmComponent from './ConfirmComponent.vue';
import router from '@/router';
import Rating from 'primevue/rating';
import axios from "axios";
import authHeader from '@/services/auth.header';

export default{
	props:{
		username:'',
		isbn:'',
		profileImg:'',
		canInteract: Boolean,
		ownReview:null,
		emojiIds : ["r"+1,"r"+2,"r"+3,"r"+4,"r"+5],
	},
	data(){
		return{
			bookrate:0,
			textreview:'',
			optionsDropdowned:false,
			confirmation:false,
			msg:"Do you want to remove your review?",
		}
	},
	watch: {
      'ownReview' (to, from) {
        this.setOwnReview();
      }
    },
	created() {
		this.setOwnReview();
	},
	computed: {
		selectedLanguage() {
			return this.$store.state.language.selectedLanguage;
		},
	},
	methods:{
		getResponse(response){
			this.optionsDropdowned=false;
			this.confirmation=false;
			if(response=="yes"){
				let header = authHeader();
	            let config = {headers:header}
				axios.delete('http://localhost:8080/api/book/'+this.isbn+"/review?username="+this.usernameReviewer,config)
				.then(resp=>{
					console.log(resp)
					if(resp.status==200) this.$emit('review_deletion');
				}).catch(error=>{
					console.log(error);
				})
				
			}
		},
		showConfirmation(){
			this.confirmation=!this.confirmation;
			console.log(this.confirmation);
		},
		changeSettingsOpened(){
			this.optionsDropdowned=!this.optionsDropdowned;
		},
		profilePage() {
			this.$router.push('/user/' + this.username);
		},
		expandHeight(height){
			this.$emit("expandHeight");
		},
		setOwnReview() {
			console.log(this.ownReview);
			if (this.ownReview) {
				this.bookrate = this.ownReview.rating ? this.ownReview.rating : 0;
				this.textreview = this.ownReview.description;
			}
			if (this.$store.state.auth.storedReview) {
				this.textreview = this.$store.state.auth.storedReview;
			}
		},
		publishReview(){
			if(this.canInteract===false) {
				this.handle_logout();
			}
			let header = authHeader();
            let config = {headers:header}
            header['Content-Type']='application/json';

			const date = new Date();
			const isoDateString = date.toISOString();
			if(this.textreview!="" || this.bookrate!=0) {
				axios.post("http://localhost:8080/api/book/"+this.isbn+"/review?username="+this.username,
				{
					description:this.textreview,
					postDate: isoDateString
				},
				config 
				).then(resp =>{
					console.log(resp)
					this.publishRating();
				}).catch(err=>{
					console.log(err)
				})
			}
			if (this.selectedLanguage == "portuguese") {
				this.$toast.success("Avaliação publicada com sucesso!");
			} else {
				this.$toast.success("Review successfully published!");
			}
		},
		publishRating(){
			let header = authHeader();
            let config = {headers:header}
            header['Content-Type']='application/json';

			if(this.canInteract===false) {
				this.handle_logout();
			}
			let rating = this.bookrate.toFixed(1);
			axios.post("http://localhost:8080/api/book/"+this.isbn+"/rate?username="+this.username+"&rating="+rating,{},
				config
				).then(resp =>{
					this.$emit('newpost');
					console.log(resp)
				}).catch(err=>{
					console.log(err)
				});
		},
		handle_logout(){
			this.$store.dispatch('auth/storeReview', this.textreview);
            this.$store.dispatch('auth/logout', this.$route.path).then(
            () => {
				router.push({path:'/signup'})
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
      		);
    	},
	},
	components: {
		Rating,
		EmojiReactionComponent,
		CommentSectionComponent,
		ConfirmComponent,
	}
}
</script>
<style>
.review-box {
  	position: absolute;
  	top: 25.72px;
  	left: 0px;
  	border-radius: 30px;
  	background-color: #212121;
  	width: 929px;
	height: 180px;
	mix-blend-mode: normal;
}
.review-area {
    width: 500px;
    position: absolute;
    border-radius: 20px;
    background-color: #5b5b5b;
    height: 50px;
    top: 50%;
    left: 50%;
}

.foto-icon {
  	position: absolute;
  	top: 0px;
  	left: 24px;
  	width: 79px;
  	height: 74.1px;
  	object-fit: cover;
	border-radius: 50%;
}
.sophie-mayer {
  	color: #fff;
}
.reviewer {
  	position: absolute;
  	top: 37.41px;
  	left: 137.7px;
  	font-weight: 600;
  	display: inline-block;
  	width: 196.4px;
  	height: 21.9px;
}
.review {
  	position: absolute;
	top: 110px;
	left: 93.33px;
  	font-weight: 300;
  	color: #fff;
  	display: inline-block;
  	width: 827.8px;
  	height: 72.9px;
}

.my-review {
	width: 100%;
	position: relative;
	height: 185px;
	text-align: left;
	font-size: 16px;
	color: #a9a0a0;
	font-family: 'Open Sans';
	margin-bottom: 50px;
}
.publish {
    position: absolute;
    top: 90px;
	left: 700px;
    width: 121.8px;
    height: 49px;
    text-align: center;
    font-size: 20px;
    color: #afafaf;
	background-color: #313030;
	border-radius: 30px;
	border-width: 0px 0px 0px 0px;
	box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}
.publish:hover{
	cursor:pointer;
}
.publish:active { 
    transform: scale(0.98); 
    /* Scaling button to 0.98 to its original size */ 
    box-shadow: 3px 2px 22px 1px rgba(0, 0, 0, 0.24); 
    /* Lowering the shadow */ 
} 
.reviewer-options {
	position: absolute;
	top: 35px;
	left: 800px;
	font-weight: 600;
	display: inline-block;
	width: 196.4px;
	height: 21.9px;
	font-size: 33px;
	cursor: pointer;
}

.dropdown-settings-content {
  position: relative;
  top: 85px;
  left: 800px;
  background-color: #f9f9f9;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-settings-content #row-option {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
#row-option:hover{
	background-color:rgb(139, 139, 139);
}
</style>

