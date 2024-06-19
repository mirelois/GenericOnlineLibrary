<script setup>
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import BookshelfDropdownComponent from '@/components/BookshelfDropdownComponent.vue';
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { useStore } from 'vuex';
import { computed } from 'vue';

const store = useStore();

const translations = computed(() => store.getters['language/currentTranslations']);
const setLanguage = (language) => store.dispatch('language/setLanguage', language);
const selectedLanguage = computed(() => store.state.language.selectedLanguage);

if (localStorage.getItem('selectedLanguage')) {
    setLanguage(localStorage.getItem('selectedLanguage'));
}
</script>

<template>
  <div class="frame">
    <div class="parent"></div>
  </div>
  <input
      id="image-upload"
      type="file"
      accept="image/png, image/jpeg"
      class="image-upload"
      style="display:none"
      ref="myImageUpload"
      required
      @change="handleFileChange"
    />
  <img @click="upload_pic" class="profilepic" alt="" :src="profileImageUrl">
  <div class="name">
    <div v-if="edit_activated==false" class="carmen-garca-lpez">{{ name }}</div>
    <input v-if="edit_activated==true" class="carmen-garca-lpez-aux" :value="name" />
    <div v-if="pronouns!=='' && pronouns!=='-' && edit_activated==false" class="theythem">({{pronouns}})</div>
    <select v-model="selected_pronoun" v-if="edit_activated==true" class="theythem-aux" name="pronouns" id="pronouns">
      <option value="she/her">She/Her</option>
      <option value="he/him">He/Him</option>
      <option value="they/them">They/Them</option>
      <option value="ze/hir">Ze/Hir</option>
      <option value="co/cos">Co/Cos</option>
      <option value="nd_pronoun">-</option>
    </select>

  </div>
  <div class="details">
    <div class="details-title">{{translations.details}}</div>
    <div class="detail-row">
      <b class="label">{{translations.age}}</b>
      <div v-if="edit_activated==false" class="value">{{birthDate}}</div>
      <Datepicker v-if="edit_activated==true" v-model="date" />

    </div>
    <div class="detail-row">
      <b class="label">{{translations.country}}</b>
      <div v-if="edit_activated==false" class="value">{{country}}</div>
        <select v-model="selected_country" class="value-aux" v-if="edit_activated==true" name="country">
          <option value="nd_country">-</option>
          <option v-for="country in countries">
          <span>{{ country.text }}</span>
        </option>
      </select>
    </div>
    <div class="detail-row">
      <b class="label">{{translations.gender}}</b>
      <div v-if="edit_activated==false" class="value">{{gender}}</div>
      <select v-model="selected_gender" v-if="edit_activated==true" class="value-aux" name="gender" id="gender">
        <option value="female">Female</option>
        <option value="male">Male</option>
        <option value="non_binary">Non-binary</option>
        <option value="other">Other</option>
        <option value="rather_not_say">Rather Not Say</option>
      </select>

    </div>
    <button class="edit-profile-wrapper" v-if="this.$route.params.username==null" @click="edit_activated=true">{{translations.editprofile}}</button>
    <button v-if="edit_activated==true" class="edit-profile-wrapper-2" @click="handle_edit()">{{translations.save}}</button>
  </div>
  <div class="section">
    <div class="container">
      <div class="about-me">{{translations.aboutme}}</div>
    </div>
    <div class="container1">
      <div class="theres-nothing-i-container">
        <span class="theres-nothing-i-container1">
          <p v-if="edit_activated==false" class="theres-nothing-i">{{description}} </p>
          <input v-if="edit_activated==true" class="theres-nothing-i-aux" v-model="description"/>
          
        </span>
      </div>
    </div>
    <div class="container2"></div>
    <div class="container3">
      <div class="about-me">{{translations.interests}}</div>
      <div class="container1">
        <div class="im-passionate-about-container">
          <span class="theres-nothing-i-container1">
            <p v-if="edit_activated==false" class="theres-nothing-i">{{interests}}</p>
            <input v-if="edit_activated==true" class="theres-nothing-i-aux" v-model="interests"/>
          </span>
        </div>
      </div>
    </div>
  </div>
  <div class="highlighted-bookshelf-my-top-parent">
    <div class="highlighted-bookshelf-my" v-if="highlightedBookshelf.name!=null" @click="navigateHighlighted">Highlight: {{highlightedBookshelf.name}}</div>
    <div class="book-row">
      <img v-for="(value,index) in highlightedBookshelf.personalBooks" @click="bookNavigate(value)" :v-if="!value && index<4" class="book-icon" :alt="index" :src="value.coverImage"/>
    </div>
    <div v-if="edit_activated==true">
      <BookshelfDropdownComponent @bookshelf_highlighted="setHighlighted" :username="username"></BookshelfDropdownComponent>
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
      selected_gender:'',
      highlightedBookshelf: '',
      selected_country:'',
      interests: '',
      date:'',
      selected_pronoun:'',
      profileImageUrl: '',
      pronouns: '',
      edit_activated:false,
      countries:[],
      fileName: "",
      preview: null,
      formData: null,
      selectedHighlighted:null
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
      if(decodedToken.info.exp<Date.now()/1000) {
        this.handle_logout();
      }
      this.setUsername(decodedToken.info.sub);
      this.getMyInfo();
    } catch (error) {
      console.error('Error parsing user token:', error);
    }
    this.getCountries();
  },
  watch: {
      '$route' (to, from) {
        this.getMyInfo();
      }
  },
	methods:{
		setUsername(username){
			this.username=username;
		},
    getMyInfo(){
      let header = authHeader();
      let config = {headers:header};
      let route = "http://localhost:8080/api/customer/me";
      console.log(this.$route.params.username);
      console.log(this.username);
      if (this.$route.params.username) {
        route = "http://localhost:8080/api/customer/username/" + this.$route.params.username;
      }
      axios.get(route, config).then(me=>{
        this.birthDate = me.data.birthDate ? (new Date().getYear()-new Date(me.data.birthDate).getYear()) : '-';
        this.date = new Date(me.data.birthDate);
        this.country = me.data.country ? me.data.country : '-';
        this.name = me.data.name ? me.data.name : this.username;
        this.description = me.data.description ? me.data.description : '';
        this.gender = me.data.gender ? me.data.gender : '-';
        console.log("qhats happening");
        console.log(me.data.highlightedBookshelf);
        this.highlightedBookshelf = me.data.highlightedBookshelf ? me.data.highlightedBookshelf : {'name':'-'};
        if(this.highlightedBookshelf!='') this.selectedHighlighted =  me.data.highlightedBookshelf;
        else this.selectedHighlighted='None';
        this.profileImageUrl = me.data.profileImageUrl ? me.data.profileImageUrl : 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png';
        this.interests = me.data.interests ? me.data.interests : '';
        this.pronouns = me.data.pronouns ? me.data.pronouns : '-';
        this.selected_country = me.data.country;
        this.selected_gender = me.data.gender;
        this.selected_pronoun = me.data.pronouns;
      }).catch(error=>{
        console.log(error);
      })
    },
    getCountries(){
      axios.get('https://trial.mobiscroll.com/content/countries.json').then(resp=>{
        console.log(resp.data)
        for (let i = 0; i < resp.data.length; ++i) {
          const country = resp.data[i];
          this.countries.push({ text: country.text, value: country.value })
        }
        console.log("heyyyy");
        console.log(this.countries);
      }).catch(error=>{
        console.log(error);
      })
    },
    handle_edit(){
      let header = authHeader();
      let config = {headers:header};
      let genre = null;
      console.log(this.name);
      if (this.selected_gender) {
        genre = this.selected_gender.replaceAll('-','_').toLowerCase();
      }
      console.log(genre);
      console.log(this.description);
      console.log(this.interests);
      console.log(this.profileImageUrl);
      console.log(this.selectedHighlighted);
			
      axios.put("http://localhost:8080/api/customer/me",
        {
					name:this.name,
					gender: genre,
					pronouns: this.selected_pronoun,
					birthDate: this.date,
					country: this.selected_country,
					description: this.description,
					interests: this.interests,
          profileImageUrl:this.profileImageUrl,
          profileBannerUrl:'',
          highlightedBookshelf:this.selectedHighlighted
				},
				config
      ).then((resp)=>{
        console.log(resp)
        if(resp.status==200) {
          this.name=this.name,
					this.gender= genre;
					this.pronouns = this.selected_pronoun ? this.selected_pronoun.replaceAll('_','-').toLowerCase() : null;
					this.country= this.selected_country,
					this.description= this.description,
					this.interests= this.interests,
          this.highlightedBookshelf = this.selectedHighlighted ? this.selectedHighlighted : {name:'-'};
          console.log("debug");
          console.log(this.highlightedBookshelf)
          this.edit_activated=false;
        }
      }
      )
      .catch(error=>{console.log(error)})

    },
    upload_pic(){
      const elem = this.$refs.myImageUpload;
      elem.click()
    },
    handleFileChange(event) {
      console.log(event);
    },
    setHighlighted(selected){
      console.log(selected);
      this.selectedHighlighted=selected;
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
    },
    bookNavigate(value) {
      this.$router.push('/book/'+value.bookISBN);
    },
    navigateHighlighted() {
      this.$router.push('/bookshelves/'+this.highlightedBookshelf.name);
    },
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
  cursor:pointer;
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
  word-wrap: break-word;
}

.theres-nothing-i-aux {
  margin-block-start: 25px;
  margin-block-end: 20px;
  height:100px;
  color: black;
  border-width: 0px;
  background-color: rgb(172, 172, 172);
  border-radius: 5px;
  width: 1400px;
  font-size: 20px;
  margin-left: -200px;
  word-wrap: break-word;
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

.carmen-garca-lpez-aux {
  position: relative;
  line-height: 30px;
  background-color: rgb(172, 172, 172);
  border-radius: 5px;
}

.theythem {
  position: relative;
  font-size: 32px;
  line-height: 17.5px;
  color: #fcfcfd;
  border-width: 0px
}

.theythem-aux {
  position: relative;
  line-height: 30px;
  color: black;
  border-width: 0px;
  background-color: rgb(172, 172, 172);
  border-radius: 5px;
  height:30px;
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
  font-size: 40px;
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
  height: 275px;
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
  position: relative;
  line-height: 30px;
}
.value-aux{
  color: black;
  border-width: 0px;
  height:30px;
  background-color: rgb(172, 172, 172);
  border-radius: 5px;
}
.highlighted-bookshelf-my {
  position: absolute;
  top: 12px;
  left: 18px;
  display: flex;
  align-items: center;
  width: 482px;
  height: 48px;
  cursor: pointer;
}
.book-icon {
  cursor: pointer;
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
  cursor:pointer;
  justify-content: center;
}

.edit-profile-wrapper-2 {
  position: absolute;
  bottom: 15px;
  right: 280px;
  border-radius: 36px;
  background-color: #f0effa;
  width: 201px;
  height: 50px;
  overflow: hidden;
  font-size: 20px;
  color: #000;
  display: flex;
  align-items: center;
  cursor:pointer;
  justify-content: center;
}

.edit-profile {
  font-size: inherit;
  color: inherit;
}
</style>
