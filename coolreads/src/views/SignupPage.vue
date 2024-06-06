<template>
  <div class="signupage">
    <div class="bg">
      <div class="bgChild">
        <div class="coolreads">
          <div class="welcomeToCoolreadsContainer">
            <span class="welcomeToCoolreadsContainer1">
              <p class="welcomeToCoolreads">Welcome to CoolReads! 📚 </p>
              <p class="welcomeToCoolreads">Join us in our book loving community today!</p>
            </span>
          </div>
          <div class="titlenameWrapper"></div>
          <div class="titlename">
            <div class="coolreadsWrapper">
              <i class="coolreads1">oolReads</i>
            </div>
            <div class="c">C</div>
          </div>
        </div>
      </div>
      <div class="form">
        <div class="titleWrapper">
          <div class="title">Create an account</div>
        </div>
        <div class="frameParent">
          <div class="frameGroup">
            <div class="emailWrapper">
              <div class="email">Email</div>
            </div>
            <div class="inputTextWrapper">
              <div class="inputText">
                <div class="context">example@example.com</div>
                <div class="inputTextChild"></div>
              </div>
            </div>
          </div>
          <div class="frameGroup">
            <div class="emailWrapper">
              <div class="email">Password</div>
            </div>
            <div class="input">
              <div class="inputText1">
                <div class="context">Enter your password</div>
              </div>
            </div>
          </div>
        </div>
        <div class="buttonParent">
          <div class="button">
            <div class="title">Create account</div>
          </div>
          <div class="button1">
            <div class="title">Continue with Google</div>
          </div>
          <div class="alreadyHaveAnAccountParent">
            <div class="alreadyHaveAn">Already have an account?</div>
            <div class="logIn" @click="onLogInTextClick">Log in</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { defineComponent } from 'vue'
export default defineComponent({
name: "SignupPage",
methods: {
onLogInTextClick() {

}}})

</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.bgChild {
  position: absolute;
  top: 0px;
  left: 0px;
  background: linear-gradient(#222831, #222831), #d9d9d9;
  width: 720px;
  height: 100%;
  }
  .coolreads {
  position: absolute;
  top: 64px;
  left: 80px;
  width: 177px;
  height: 28px;
  }
  
  .welcomeToCoolreads {
  margin: 10;
}

.welcomeToCoolreadsContainer1 {
  width: 100%;
}

.welcomeToCoolreadsContainer {
  width: 568px;
  position: absolute; 
  top: 420px; 
  left: 30px; 
  font-size: 56px;
  line-height: 1.2;
  font-family: Inika;
  background: linear-gradient(180deg, #fff, rgba(255, 255, 255, 0.44));
  color: #fff; 
  -webkit-background-clip: text;
  -moz-background-clip: text;
  background-clip: text;
  text-align: left;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

  .coolreads1 {
  position: absolute;
  top: 10px;
  left: 0px;
  letter-spacing: 0.1em;
  line-height: 100%;
  font-size: 30px;
  font-weight: 550;
  color: #fff;
  }
  .coolreadsWrapper {
  position: absolute;
  top: 16px;
  left: 32px;
  width: 153px;
  height: 28px;
  }
  .c {
  position: absolute;
  top: 9px;
  left: 0px;
  font-size: 40px;
  font-family: Inika;
  color: #c48930;
  }
  .titlename {
  position: absolute;
  top: 48px;
  left: 48px;
  width: 185px;
  height: 57px;
  }
  .titlenameWrapper {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 720px;
  height: 900px;
  overflow: hidden;
  font-size: 28px;
  font-family: Inika;
  }
  .bg {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 720px;
  height: 900px;
  }
  .title {
  position: relative;
  line-height: 100%;
  font-weight: 600;
  }
  .titleWrapper {
  align-self: stretch;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  font-size: 28px;
  }
  .email {
  flex: 1;
  position: relative;
  line-height: 100%;
  text-transform: capitalize;
  }
  .emailWrapper {
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
  .inputTextChild {
  width: 24px;
  height: 24px;
  }
  .inputText {
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
  .inputTextWrapper {
  align-self: stretch;
  height: 48px;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  font-size: 14px;
  }
  .frameGroup {
  align-self: stretch;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 12px;
  }
  .iconeye {
  width: 24px;
  position: relative;
  height: 24px;
  }
  .iconeyeWrapper {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  }
  .inputText1 {
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
  .frameParent {
  align-self: stretch;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 24px;
  color: #344054;
  }
  .button {
  align-self: stretch;
  border-radius: 8px;
  background-color: #2f3134;
  height: 48px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 16px;
  box-sizing: border-box;
  }
  .icongoogleOriginal {
  width: 20px;
  position: relative;
  height: 20px;
  overflow: hidden;
  flex-shrink: 0;
  }
  .button1 {
  align-self: stretch;
  border-radius: 8px;
  background-color: #d1e9ff;
  height: 48px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 16px;
  box-sizing: border-box;
  gap: 8px;
  color: #1570ef;
  }
  .alreadyHaveAn {
  position: relative;
  line-height: 100%;
  text-transform: capitalize;
  font-family: Inika;
  }
  .logIn {
  position: relative;
  line-height: 100%;
  text-transform: capitalize;
  color: #1570ef;
  cursor: pointer;
  }
  .alreadyHaveAnAccountParent {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 8px;
  color: #98a2b3;
  }
  .buttonParent {
  align-self: stretch;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  gap: 24px;
  color: #fcfcfd;
  }
  .form {
  position: absolute;
  top: 236px;
  left: 880px;
  border-radius: 10px;
  width: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 32px;
  font-size: 16px;
  color: #101828;
  font-family: Inika;
  }
  .signuppage {
  width: 100%;
  position: relative;
  background-color: #fff;
  height: 900px;
  text-align: left;
  font-size: 56px;
  color: #ffffff;
  font-family: Inika;
  }
  
.button .title,
.button1 .title {
  font-size: 16px;
  font-family: Inika;
}
  </style>