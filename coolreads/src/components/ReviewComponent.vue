<template>
	<main>
    <div :style="{'margin-bottom':marginReviewBottom}" class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" :src="imageReviewer">
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
	</div> 
	</main>
</template>
<script>
import Rating from 'primevue/rating';
import EmojiReactionComponent from './EmojiReactionComponent.vue';
import CommentSectionComponent from './CommentSectionComponent.vue';
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
		canInteract:Boolean
	},
	data(){
		return{
			bookRate:2
		}
	},
	components: {
		Rating,
		EmojiReactionComponent,
		CommentSectionComponent
	},
	methods:{
		expandHeight(height){
			this.$emit("expandHeight");
		}
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
} 
</style>

