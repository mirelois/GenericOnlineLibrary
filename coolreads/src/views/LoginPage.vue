<template>
	<div>
		<div class="form">
		<div class="title-wrapper">
			<div class="title">Login to your account</div>
		</div>
		<div class ="frame-parent">
			<div class ="frame-group">
				<div class="email-wrapper">
					<div class="email">Email or Username</div>
				</div>
				<div class = "input-text-wrapper">
					<div class ="input-text">
						<div class="context">example@example.com</div>
						<div class="input-text-child">
						</div> 
					</div>
				</div>
			</div>
			<div class="frame-group">
				<div class = "email-wrapper">
					<div class="email">Password</div>
					<div class="forgot">Forgot?</div>
				</div>
				<div class ="input">
					<div class ="input-text1">
						<div class="context">Enter your password</div>
						<div class="iconeye-wrapper">
							<img class="iconeye" alt="" src="/img/iconeye.svg">
						</div>
					</div>
				</div>
			</div>
        <div class="buttonlogin" id="buttonloginContainer">
          <div class="textButton">Login now</div>
        </div>
        <div class="dont-have-an-account-parent">
          <div class="dont-have-an">Don't have an account ?</div>
          <div class="sign-up"  @click="onSignUpTextClick">Sign up</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import router from "../router/index"
export default defineComponent({
  name: "LoginPage",
  methods: {
    onSignUpTextClick() {
      router.push({ path: '/signup' })
    }
  }
})

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
  	align-self: stretch;
  	flex: 1;
  	border-radius: 8px;
  	border: 3px solid #d1e9ff;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: flex-start;
  	padding: 12px 16px;
  	gap: 5px;
}
.input-text-wrapper {
  	align-self: stretch;
  	height: 48px;
  	display: flex;
  	flex-direction: row;
  	align-items: flex-start;
  	justify-content: flex-start;
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
}
.iconeye-wrapper {
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: flex-end;
}
.input-text1 {
  	align-self: stretch;
  	border-radius: 8px;
  	border: 1px solid #d0d5dd;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: flex-start;
  	padding: 12px 16px;
  	gap: 5px;
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
.dont-have-an-account-parent {
  display: flex;
  flex-direction: row;
  position: absolute;
  gap: 8px;
  bottom: 25px; 
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
</style>