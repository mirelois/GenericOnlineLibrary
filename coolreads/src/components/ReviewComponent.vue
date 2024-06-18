<template>
	<main>
    <div :style="{'margin-bottom':marginReviewBottom}" class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" :src="imageReviewer">
		<div class="reviewer-options" v-if="options==true" @click="changeSettingsOpened">...</div>
		<div v-if="options==true && optionsDropdowned==true" class="dropdown-settings-content">
			<div id="row-option" @click="showConfirmation" >Delete</div>
		</div>
        <div class="reviewer">
        <span>review by </span>
        <span class="sophie-mayer">{{ usernameReviewer }}</span>
			<div>
				<Rating id="estrelas" :modelValue="reviewRate" @update:modelValue="reviewRate = $event" readonly :cancel="false" />
			</div>
		</div>
        <div class="review">{{ reviewDescription }}</div>
		<EmojiReactionComponent :reviewer="usernameReviewer" :isbn="isbn" :likes="likesCount" :emojiIds="emojiIds"></EmojiReactionComponent>
		<CommentSectionComponent :canInteract="canInteract" :reviewer="usernameReviewer" :isbn="isbn" @comment_opened="expandHeight"></CommentSectionComponent>
		<div v-if="confirmation==true">
			<ConfirmComponent :header_msg="msg" @confirmation_response="getResponse"></ConfirmComponent>
		</div>
	</div> 
	</main>
</template>
<script>
import Rating from 'primevue/rating';
import EmojiReactionComponent from './EmojiReactionComponent.vue';
import CommentSectionComponent from './CommentSectionComponent.vue';
import ConfirmComponent from './ConfirmComponent.vue';
import axios from "axios";
import authHeader from '@/services/auth.header';

export default{
	props:{
		reviewRate: 0,
		reviewDescription: '',
		imageReviewer:'',
		isbn:'',
		usernameReviewer:'',
		emojiIds:[],
		likesCount:{},
		marginReviewBottom:"100px",
		canInteract:Boolean,
		options:Boolean
	},
	data(){
		return {
			optionsDropdowned:false,
			confirmation:false,
			msg:"Do you want to remove your review?"
		}
	},
	components: {
		Rating,
		EmojiReactionComponent,
		CommentSectionComponent,
		ConfirmComponent
	},
	methods:{
		expandHeight(height){
			this.$emit("expandHeight");
		},
		changeSettingsOpened(){
			this.optionsDropdowned=!this.optionsDropdowned;
		},
		showConfirmation(){
			this.confirmation=!this.confirmation;
		},
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
		}

	}
}
</script>
<style scoped>
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
  margin-left: 200px;
  margin-top: -100px;
}
</style>

