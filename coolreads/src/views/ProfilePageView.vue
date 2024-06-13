<script setup>
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
</script>

<template>
  <div class="frame">
    <div class="parent"></div>
  </div>
  <img class="profilepic" alt="" :src="profileImageUrl">
  <div class="name">
    <div class="carmen-garca-lpez">{{ name }}</div>
    <div v-if="pronouns!=='' && pronouns!=='-'" class="theythem">({{pronouns}})</div>
  </div>
  <div class="details">
    <div class="details-title">Details</div>
    <div class="detail-row">
      <b class="label">Age</b>
      <div class="value">{{birthDate}}</div>
    </div>
    <div class="detail-row">
      <b class="label">Country</b>
      <div class="value">{{country}}</div>
    </div>
    <div class="detail-row">
      <b class="label">Gender</b>
      <div class="value">{{gender}}</div>
    </div>
	<div class="edit-profile-wrapper" @click="navigateToEditProfile()" >
      <div class="edit-profile">Edit Profile</div>
    </div>
  </div>
  <div class="section">
    <div class="container">
      <div class="about-me">About me</div>
    </div>
    <div class="container1">
      <div class="theres-nothing-i-container">
        <span class="theres-nothing-i-container1">
          <p class="theres-nothing-i">{{description}} </p>
        </span>
      </div>
    </div>
    <div class="container2"></div>
    <div class="container3">
      <div class="about-me">Interests</div>
      <div class="container1">
        <div class="im-passionate-about-container">
          <span class="theres-nothing-i-container1">
            <p class="theres-nothing-i">{{interests}}</p>
          </span>
        </div>
      </div>
    </div>
  </div>
  <div class="highlighted-bookshelf-my-top-parent">
    <div class="highlighted-bookshelf-my">Highlighted Bookshelf: My Top 5</div>
    <div v-for="book in highlightedBookshelf" v-if="!book" class="book-row">
      <img class="book-icon" alt="" :src="book.cover">
    </div>
	<div class="posFoot"><FooterComponent></FooterComponent></div>
  </div>
	
<NavComponent :username="username"></NavComponent>
</template>
<script>
import router from '../router/index'
import axios from "axios";
import authHeader from '@/services/auth.header';
export default {
	data(){
		return{
			username:'',
      name:'',
      birthDate: '',
      country: '',
      description: '',
      gender: '',
      highlightedBookshelf: '',
      interests: '',
      profileImageUrl: '',
      pronouns: ''
		}
	},
	created() {
    const token = localStorage.getItem('user');
    if (!token || this.$store.state.auth.status.loggedIn===false) {
      router.push({name:'home'})
      return;
    }

    try {
      const decodedToken = JSON.parse(token);
      this.setUsername(decodedToken.info.sub);
      this.getMyInfo();
    } catch (error) {
      console.error('Error parsing user token:', error);
    }
  },
	methods:{
		setUsername(username){
			this.username=username;
		},
		navigateToEditProfile() {
			router.push({ name: 'editprofile'});
		},
    getMyInfo(){
      let header = authHeader();
      let config = {headers:header}
      axios.get("http://localhost:8080/customer/me",config).then(me=>{
        console.log(me.data);
        this.birthDate = me.data.birthDate ? (new Date().getYear()-new Date(me.data.birthDate).getYear()) : '-';
        this.country = me.data.country ? me.data.country : '-';
        this.name = me.data.name ? me.data.name : this.username;
        this.description = me.data.description ? me.data.description : '-';
        this.gender = me.data.gender ? me.data.gender : '-';
        this.highlightedBookshelf = me.data.highlightedBookshelf ? me.data.highlightedBookshelf : '-';
        this.profileImageUrl = me.data.profileImageUrl ? me.data.profileImageUrl : 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png';
        this.interests = me.data.interests ? me.data.interests : '-';
        this.pronouns = me.data.pronouns ? me.data.pronouns : '-';
      }).catch(error=>{
        console.log(error);
      })
    }


	}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.parent {
  position: absolute;
  height: 423.77%;
  width: 105.71%;
  top: -162%;
  right: -2.85%;
  bottom: -161.77%;
  left: -2.86%;
}
.frame {
  position: absolute;
  top: 114px;
  left: 0px;
  background-color: #b8dbe2;
  width: 900px;
  height: 199px;
  overflow: hidden;
}
.profilepic {
  position: absolute;
  top: 178px;
  left: 278px;
  border-radius: 50%;
  width: 271px;
  height: 271px;
  object-fit: cover;
}
.about-me {
  position: relative;
  line-height: 25px;
  font-size: 40px;
}
.container {
  align-self: stretch;
  height: 82px;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 24px 24px 0px;
  box-sizing: border-box;
}
.theres-nothing-i {
  margin-block-start: 0;
  margin-block-end: 20px;
}
.discoveries-with-fellow {
  margin: 0;
}
.theres-nothing-i-container1 {
  width: 100%;
}
.theres-nothing-i-container {
  width: 1300px;
  position: relative;
  line-height: 20px;
  display: flex;
  align-items: center;
  height: 138px;
  flex-shrink: 0;
}
.container1 {
  width: 1396px;
  height: 187px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 22px 24px;
  box-sizing: border-box;
  font-size: 25px;
  color:  #fff;
}
.container2 {
  width: 612px;
  height: 34px;
}
.container3 {
  align-self: stretch;
  height: 69px;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 24px 24px 0px;
  box-sizing: border-box;
}
.im-passionate-about-container {
  width: 1289px;
  position: relative;
  line-height: 20px;
  display: flex;
  align-items: center;
  height: 140px;
  flex-shrink: 0;
}
.section {
  position: absolute;
  top: 840px;
  left: 16px;
  box-shadow: 0px 0px 0px 1px rgba(140, 140, 140, 0.2);
  border-radius: 8px;
  background-color: #31363f;
  width: 1793px;
  height: 564px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding: 0px 0px 12px;
  box-sizing: border-box;
  font-size: 48px;
  color: #fcfcfd;
}
.carmen-garca-lpez {
  position: relative;
  line-height: 30px;
}
.theythem {
  position: relative;
  font-size: 32px;
  line-height: 17.5px;
  color: #fcfcfd;
}
.name {
  position: absolute;
  width: calc(100% - 984px);
  top: 476px;
  right: 929px;
  left: 55px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  gap: 4.4px;
  font-size: 40px;
  color: rgba(255, 255, 255, 0.9);
}
.details-title {
  font-size: 48px;
  line-height: 25px;
  color: #fcfcfd;
  margin-bottom: 30px;
}
.details {
  position: absolute;
  top: 545px;
  left: 16px;
  color: rgba(255, 255, 255, 0.9);
  background-color: #31363f;
  width: 891px;
  height: 277px;
  overflow: hidden;
  padding: 24px;
  box-sizing: border-box;
}
.detail-row {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}
.label {
  font-size: 20px;
  width: 150px;
  text-transform: uppercase;
  color: rgba(255, 255, 255, 0.9);
}
.value {
  font-size: 20px;
  color: #fff;
}
.highlighted-bookshelf-my {
  position: absolute;
  top: 12px;
  left: 18px;
  display: flex;
  align-items: center;
  width: 482px;
  height: 48px;
}
.book-icon {
  width: 125.9px;
  position: relative;
  height: 188.8px;
  object-fit: cover;
}
.book-row {
  position: absolute;
  top: 80px;
  left: 18px;
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  justify-content: flex-start;
  gap: 56px;
}
.highlighted-bookshelf-my-top-parent {
  position: absolute;
  top: 127px;
  left: 919px;
  color: rgba(255, 255, 255, 0.9);
  background-color: #31363f;
  width: 890px;
  height: 695px;
  overflow: hidden;
  font-size: 25px;
}
.posFoot {
  position: absolute;
  top: 3259px;
  left: 1px;
  width: 671px;
  height: 124px;
}
.view-more {
  position: absolute;
  top: 10px;
  left: 48px;
}
.view-more-wrapper {
  position: absolute;
  top: 455px;
  left: 1590px;
  border-radius: 72.66px;
  background-color: #f0effa;
  width: 201px;
  height: 50px;
  overflow: hidden;
  font-size: 20px;
  color: #000;
}

.edit-profile-wrapper {
  position: absolute;
  bottom: 15px;
  right: 42px;
  border-radius: 36px;
  background-color: #f0effa;
  width: 201px;
  height: 50px;
  overflow: hidden;
  font-size: 20px;
  color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
}
.edit-profile {
  font-size: inherit;
  color: inherit;
}
</style>
