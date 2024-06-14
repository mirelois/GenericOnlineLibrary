<template>
	<main>
    <div class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" :src="profileImg">
        <div class="reviewer">
			<div class="mystar-class">
				<Rating id="estrelas" v-model="bookrate" @click="publishRating" :cancel="false" />
			</div>
		</div>
        <input type="text" v-model="textreview" class="review-area"></input> 
        <button class="publish" @click="publishReview">Publish</button> 
    </div> 
	</main>
</template>
<script>
import router from '@/router';
import Rating from 'primevue/rating';
import axios from "axios";
export default{
	props:{
		username:'',
		isbn:'',
		profileImg:'',
		canInteract: Boolean
	},
	data(){
		return{
			bookrate:0,
			textreview:''
		}
	},
	methods:{
		publishReview(){
			if(this.canInteract===false) this.handle_logout();
			const headers = {
        		'Content-Type': 'application/json',
		    };
			const date = new Date();
			const isoDateString = date.toISOString();
			if(this.textreview!=""){
				axios.post("http://localhost:8080/book/"+this.isbn+"/review?username="+this.username,
				{
					description:this.textreview,
					postDate: isoDateString
				},
				{ headers: headers } 
				).then(resp =>{
					this.$emit('newpost');

					console.log(resp)
				}).catch(err=>{
					console.log(err)
				})
			}
			this.textreview='';
			this.bookrate=0;
		},
		publishRating(){
			let rating = this.bookrate.toFixed(1);
			const headers = {
        		'Content-Type': 'application/json',
		    };
			if(rating!=0){
				axios.post("http://localhost:8080/book/"+this.isbn+"/rate?username="+this.username+"&rating="+rating,
				{ headers: headers } 
				).then(resp =>{
					console.log(resp)
				}).catch(err=>{
					console.log(err)
				})
			}else{
				console.log("obrigar utilizador a inserir rating");
				return;
			} 
		},
		handle_logout(){
            this.$store.dispatch('auth/logout').then(
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
		Rating
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
    position: relative;
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
</style>

