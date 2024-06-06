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
            <div class="textButton">Create account</div>
          </div>
          <div class="button1">
            <div class="textButton">Continue with Google</div>
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
import router from "../router/index"
export default defineComponent({
  name: "SignupPage",
  methods: {
    onLogInTextClick() {
      router.push({ path: '/login' })
    }
  }
})
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.bgChild {
  position: absolute;
  top: 0px;
  left: 0px;
  background: linear-gradient(#222831, #222831), #d9d9d9;
  width: 648px; /* 720px * 0.9 */
  height: 100%;
}
.coolreads {
  position: absolute;
  top: 64px;
  left: 72px; /* 80px * 0.9 */
  width: 160px; /* 177px * 0.9 */
  height: 25px; /* 28px * 0.9 */
}

.welcomeToCoolreads {
  margin: 9; /* 10 * 0.9 */
}

.welcomeToCoolreadsContainer1 {
  width: 100%;
}

.welcomeToCoolreadsContainer {
  width: 511px; /* 568px * 0.9 */
  position: absolute;
  top: 378px; /* 420px * 0.9 */
  left: 27px; /* 30px * 0.9 */
  font-size: 50.4px; /* 56px * 0.9 */
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
  top: 9px; /* 10px * 0.9 */
  left: 0px;
  letter-spacing: 0.1em;
  line-height: 100%;
  font-size: 27px; /* 30px * 0.9 */
  font-weight: 550;
  color: #fff;
}
.coolreadsWrapper {
  position: absolute;
  top: 6px; /* 7px * 0.9 */
  left: 29px; /* 32px * 0.9 */
  width: 138px; /* 153px * 0.9 */
  height: 25px; /* 28px * 0.9 */
}
.c {
  position: absolute;
  top: 0px;
  left: 0px;
  font-size: 36px; /* 40px * 0.9 */
  font-family: Inika;
  color: #c48930;
}
.titlename {
  position: absolute;
  top: 43px; /* 48px * 0.9 */
  left: 43px; /* 48px * 0.9 */
  width: 166px; /* 185px * 0.9 */
  height: 51px; /* 57px * 0.9 */
}
.titlenameWrapper {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 648px; /* 720px * 0.9 */
  height: 810px; /* 900px * 0.9 */
  overflow: hidden;
  font-size: 25.2px; /* 28px * 0.9 */
  font-family: Inika;
}
.bg {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 648px; /* 720px * 0.9 */
  height: 810px; /* 900px * 0.9 */
}
.title {
  position: relative;
  line-height: 100%;
  text-align: center;
  font-weight: 600;
  margin-bottom: 21.6px; /* 24px * 0.9 */
}
.textButton {
  position: relative;
  line-height: 100%;
  text-align: center;
  font-weight: 600;
}
.titleWrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
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
  font-size: 14.4px; /* 16px * 0.9 */
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
  width: 21.6px; /* 24px * 0.9 */
  height: 21.6px; /* 24px * 0.9 */
}
.inputText {
  align-self: center;
  flex: none;
  border-radius: 7.2px; /* 8px * 0.9 */
  border: 2.7px solid #d1e9ff; /* 3px * 0.9 */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  padding: 7.2px 10.8px; /* 8px 12px * 0.9 */
  width: 270px; /* 300px * 0.9 */
  gap: 4.5px; /* 5px * 0.9 */
}
.inputTextWrapper {
  align-self: center;
  height: auto;
  display: flex;
  flex-direction: row;
  align-items:center;
  justify-content: center;
  font-size: 12.6px; /* 14px * 0.9 */
}
.frameGroup {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 10.8px; /* 12px * 0.9 */
}
.iconeye {
  width: 21.6px; /* 24px * 0.9 */
  position: relative;
  height: 21.6px; /* 24px * 0.9 */
}
.iconeyeWrapper {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
}
.inputText1 {
  align-self: center;
  flex: none;
  border-radius: 7.2px; /* 8px * 0.9 */
  border: 0.9px solid #d0d5dd; /* 1px * 0.9 */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  padding: 7.2px 10.8px; /* 8px 12px * 0.9 */
  width: 270px; /* 300px * 0.9 */
  gap: 4.5px; /* 5px * 0.9 */
}
.input {
  align-self: center;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: auto;
  font-size: 12.6px; /* 14px * 0.9 */
  color: #98a2b3;
}
.frameParent {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 21.6px; /* 24px * 0.9 */
  color: #344054;
}
.button {
  align-self: center;
  border-radius: 7.2px; /* 8px * 0.9 */
  background-color: #2f3134;
  height: 36px; /* 40px * 0.9 */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 270px; /* 300px * 0.9 */
  padding: 14.4px; /* 16px * 0.9 */
  box-sizing: border-box;
}
.icongoogleOriginal {
  width: 18px; /* 20px * 0.9 */
  position: relative;
  height: 18px; /* 20px * 0.9 */
  overflow: hidden;
  flex-shrink: 0;
}
.button1 {
  align-self: center;
  border-radius: 7.2px; /* 8px * 0.9 */
  background-color: #d1e9ff;
  height: 43.2px; /* 48px * 0.9 */
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 10.8px 14.4px; /* 12px 16px * 0.9 */
  box-sizing: border-box;
  width: 270px; /* 300px * 0.9 */
  gap: 7.2px; /* 8px * 0.9 */
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
  align-items: center;
  justify-content: center;
  gap: 7.2px; /* 8px * 0.9 */
  font-size: 12.6px; /* 14px * 0.9 */
  color: #98a2b3;
}
.buttonParent {
  align-self: stretch;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  gap: 21.6px; /* 24px * 0.9 */
  color: #fcfcfd;
}
.form {
  position: absolute;
  top: 0px;
  left: 765px; /* 850px * 0.9 */
  background-color: #fff;
  width: 917px; /* 1019px * 0.9 */
  height: 1080px; /* 1200px * 0.9 */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 43.2px 64.8px; /* 48px 72px * 0.9 */
  box-sizing: border-box;
  gap: 28.8px; /* 32px * 0.9 */
  font-size: 25.2px; /* 28px * 0.9 */
}
.signuppage {
  width: 100%;
  position: relative;
  background-color: #fff;
  height: 900px; /* 1000px * 0.9 */
  text-align: left;
  font-size: 50.4px; /* 56px * 0.9 */
  color: #ffffff;
  font-family: Inika;
}

.button .textButton,
.button1 .textButton {
  font-size: 14.4px; /* 16px * 0.9 */
  font-family: Inika;
}
</style>
