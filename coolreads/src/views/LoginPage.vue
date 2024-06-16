<template>
	<div>
		<div class="form">
		<div class="title-wrapper">
		  <div class="title">{{ translations.loginTitle }}</div>
		  <div class="language-selection">
			<img class="flag-icon" alt="Português" src="/img/PT.svg" @click="setLanguage('portuguese')"/>
			<img class="flag-icon" alt="English"   src="/img/US.svg" @click="setLanguage('english')"   />
		  </div>
		</div>
		<div class="frame-parent">
		  <div class="frame-group">
			<div class="email-wrapper">
			  <div class="email">{{ translations.emailOrUsername }}</div>
			</div>
			<div class="input-text-wrapper">
			  <input v-model="username" class="input-text" placeholder="example@example.com"/>
			  <div class="input-text-child"></div>
			</div>
		  </div>
		  <div class="frame-group">
			<div class="email-wrapper">
			  <div class="email">{{ translations.password }}</div>
			  <div class="forgot">{{ translations.forgot }}</div>
			</div>
			<div class="input">
			  <input v-model="password" class="input-text1" :type="type" :placeholder="translations.passwordPlaceholder"/>
			  <div class="iconeye-wrapper">
				<img class="iconeye" @click="changeInputType" alt="" src="/img/iconeye.svg">
			  </div>
			</div>
		  </div>
		  <button @click="handleLogin" class="buttonlogin" id="buttonloginContainer">{{ translations.loginnow }}</button>
		  <div class="dont-have-an-account-parent">
			<div class="dont-have-an">{{ translations.noAccount }}</div>
			<div class="sign-up"><a class="sign-upcolor" href="/signup"> {{ translations.signup }}</a></div>
		  </div>
		</div>
	  </div>
    <ToastComponent v-if="error_msg !== ''" :msg="error_msg" @close_toast="closemsg"></ToastComponent>
  </div>
</template>

<script>
import router from "../router/index";
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import User from "@/models/user";
import ToastComponent from "@/components/ToastComponent.vue";
export default{
  data(){
	return {
		username:'',
		password:'',
		message:'',
		error_msg:'',
		type:'password'
	}
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
	translations() {
      return this.$store.getters['language/currentTranslations'];
    },
    selectedLanguage() {
      return this.$store.state.language.selectedLanguage;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
		if(this.username==='' || this.password===''){
			this.error_msg = "Existem campos vazios. Introduza o username e password.";
			return;
		}
        this.$store.dispatch('auth/login', new User(this.username,'',this.password)).then(
            (u) => {
				console.log("Login successful, user:", u);
            	this.$router.push('/profile');
            },
            error => {
              this.error_msg = "Username ou password inválidos. Tente novamente."
            }
          );
        },
		closemsg(){
			this.error_msg='';
		},
		changeInputType(){
			if(this.type==='password') this.type='text';
			else this.type='password'
		},
		setLanguage(language) {
      this.$store.dispatch('language/setLanguage', language);
    	},
  	},
		components:{
			ToastComponent	
	}
};

</script>

<style scoped>

/*@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');
*/

.title {
  	position: relative;
  	line-height: 100%;
  	font-weight: 600;
}
.textButton {
  	position: relative;
  	line-height: 100%;
  	font-weight: 600;
}
.title-wrapper {
  	align-self: stretch;
  	display: flex;
  	flex-direction: column;
  	align-items: center;
  	justify-content: flex-start;
}
.email {
  	flex: 1;
  	position: relative;
  	line-height: 100%;
  	
}
.email-wrapper {
  	align-self: stretch;
  	display: flex;
  	flex-direction: row;
  	align-items: flex-start;
  	justify-content: flex-start;
}
.context {
  	flex: 1;
  	position: relative;
  	line-height: 100%;
}
.input-text-child {
  	width: 24px;
  	height: 24px;
}
.input-text {
  	border-radius: 8px;
  	border: 3px solid #d1e9ff;
  	display: flex;
  	flex-direction: row;
	width: 515px;
  	padding: 12px 16px;
  	gap: 5px;
}
.input-text-wrapper {
	width: 515px;
  	height: 48px;
  	flex-direction: row;
  	font-size: 14px;
}
.frame-group {
  	align-self: stretch;
  	display: flex;
  	flex-direction: column;
  	align-items: flex-start;
  	justify-content: flex-start;
  	gap: 12px;
}
.forgot {
  	position: relative;
  	line-height: 100%;
  	text-transform: capitalize;
  	color: #1570ef;
  	text-align: right;
}
.iconeye {
  	width: 24px;
  	position: relative;
  	height: 24px;
	top: 40px;
	cursor:pointer;
}
.iconeye-wrapper {
  position: absolute;
  margin-top: -32px;
  margin-left: 470px;
}
.input-text1 {
  border-radius: 8px;
  border: 1px solid #d0d5dd;
  padding: 12px 16px;
  width: 515px;
}
.input {
  	align-self: stretch;
  	display: flex;
  	flex-direction: column;
  	align-items: flex-start;
  	justify-content: flex-start;
  	font-size: 14px;
  	color: #98a2b3;
}
.frame-parent {
  	align-self: stretch;
  	display: flex;
  	flex-direction: column;
  	align-items: flex-start;
  	justify-content: flex-start;
  	gap: 24px;
  	font-size: 16px;
  	color: #344054;
}
.form-child {
  	align-self: stretch;
  	height: 92px;
}
.form {
  	position: absolute;
  	top: 75px;
  	left: 500px;
	font-family: Inika;
  	border-radius: 20px;
  	background-color: #fff;
  	width: 659px;
  	height: 523px;
  	display: flex;
  	flex-direction: column;
  	align-items: center;
  	justify-content: center;
  	padding: 48px 72px;
  	box-sizing: border-box;
  	gap: 32px;
  	font-size: 28px;
}
.buttonlogin {
  	top: 529px;
  	left: 658px;
  	border-radius: 8px;
  	background-color: #2f3134;
  	width: 515px;
  	height: 52px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	padding: 16px;
  	box-sizing: border-box;
  	cursor: pointer;
  	color: #fcfcfd;
}
.dont-have-an {
  	position: relative;
  	line-height: 100%;
	align-items: center;
	justify-content: center; 
  	text-transform: capitalize;
}
.sign-up {
  	position: relative;
  	line-height: 100%;
	align-items: center;
	justify-content: center; 
  	text-transform: capitalize;
  	color: #1570ef;
  	cursor: pointer;
}
.sign-upcolor {
  	color: #1570ef;
}
.dont-have-an-account-parent {
  display: flex;
  flex-direction: row;
  position: absolute;
  gap: 8px;
  bottom: 30px; 
  left: 50%; 
  transform: translateX(-50%);
  color: #98a2b3;
  font-family: Inika;
}
.icongoogle-original {
  	width: 15px;
  	position: relative;
  	height: 15px;
  	overflow: hidden;
  	flex-shrink: 0;
}
.buttongoogle {
  	top: 600px;
  	left: 658px;
  	border-radius: 8px;
  	background-color: #d1e9ff;
  	width: 515px;
  	height: 52px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	padding: 16px;
  	box-sizing: border-box;
  	gap: 8px;
  	cursor: pointer;
  	color: #1570ef;
}
.bg-icon {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	width: 1440px;
  	height: 900px;
}
.buttongoogle .textButton,
.buttonlogin .textButton {
  font-size: 18px;
  font-family: Inika;
}
.language-selection {
  display: flex;
  gap: 10px;
  position: absolute;
  top: 10px;
  right: 10px;
}
.flag-icon {
  cursor: pointer;
  width: 30px;
  height: 20px;
}
</style>