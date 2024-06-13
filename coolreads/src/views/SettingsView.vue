<script setup>
import { ref } from 'vue';
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
import ChangePasswordComponent from '../components/ChangePasswordComponent.vue';

</script>

<template>
<NavComponent></NavComponent>
  <div class="account-info">
    <div class="email">Email: </div>
    <div class="password">Password: </div>
    <div class="goodreadsuser1gmailcom">Goodreadsuser1@gmail.com</div>
    <div class="changepassword" @click="openChangePasswordPopup">
      <div class="changepassword-child">
        <div class="change-password-text">Change Password</div>
      </div>
    </div>
  </div>
  <ChangePasswordComponent v-if="showChangePasswordPopup" @close="closeChangePasswordPopup" />
  <div class="privacy-settings">
	<div class="public">Public</div>
	<div class="bookshelf1">BookShelf</div>
	<div class="friends-only">Friends Only</div>
	<div class="privacy-settings1">Privacy Settings</div>
	<div class="private">Private</div>
	<div class="privacy-settings-child">
	</div>
	<div class="privacy-settings-item">
	</div>
	<div class="privacy-settings-inner">
	</div>
   </div>

</template>

<script>
const showChangePasswordPopup = ref(false);

function openChangePasswordPopup() {
  showChangePasswordPopup.value = true;
}

function closeChangePasswordPopup() {
  showChangePasswordPopup.value = false;
}
</script>
<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
  @import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

  .email {
    position: absolute;
    color: white;
    font-size: 21px;
    top: 92px;
    left: 96px;
    white-space: pre-wrap;
  }

  .password {
    position: absolute;
    top: 178px;
    left: 75px;
    font-size: 21px;
    white-space: pre-wrap;
  }

  .goodreadsuser1gmailcom {
    position: absolute;
    top: 92px;
    font-size: 21px;
    left: 248px;
  }

  .changepassword-child {
    position: absolute;
    top: 0px;
    left: 0px;
    background-color: #464141;
    width: 173px;
    height: 59px;
  }

  .change-password-icon {
    position: absolute;
    top: 3.17px;
    left: 33.19px;
    width: 105px;
    height: 49.1px;
  }

  .change-password-text {
    margin-top: 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 18px;
  }

  .changepassword {
    position: absolute;
    top: 164px;
    left: 317px;
    width: 173px;
    height: 59px;
  }

  .account-info {
    position: absolute;
    top: 343px;
    left: 513px;
    border-radius: 20px;
    color: white;
    background-color: #131313;
    width: 805px;
    height: 285px;
    overflow: hidden;
  }

  .public {
    position: absolute;
    top: 92px;
    left: 322px;
  }

  .bookshelf1 {
    position: absolute;
    top: 200px;
    left: 38px;
  }

  .friends-only {
    position: absolute;
    top: 92px;
    left: 447px;
  }

  .privacy-settings1 {
    position: absolute;
    top: 33px;
    left: 314px;
  }

  .private {
    position: absolute;
    top: 92px;
    left: 640px;
  }

  .privacy-settings-child {
    position: absolute;
    top: 200px;
    left: 340px;
    border-radius: 4px;
    background-color: #fff;
    border: 1.3px solid #fff;
    box-sizing: border-box;
    width: 31px;
    height: 26px;
  }

  .privacy-settings-item {
    position: absolute;
    top: 200px;
    left: 662px;
    border-radius: 4px;
    background-color: #fff;
    border: 1.3px solid #fff;
    box-sizing: border-box;
    width: 31px;
    height: 26px;
  }

  .privacy-settings-inner {
    position: absolute;
    top: 200px;
    left: 501px;
    border-radius: 4px;
    background-color: #fff;
    border: 1.3px solid #fff;
    box-sizing: border-box;
    width: 31px;
    height: 26px;
  }

  .vector-icon {
    position: absolute;
    height: 2.32%;
    width: 1.86%;
    top: 66.56%;
    right: 54.78%;
    bottom: 31.11%;
    left: 43.35%;
    max-width: 100%;
    overflow: hidden;
    max-height: 100%;
    mix-blend-mode: normal;
  }

  .privacy-settings {
	color: #fff;
	font-size: 21px;
    position: absolute;
    top: 1071px;
    left: 513px;
    border-radius: 20px;
    background-color: #131313;
    width: 805px;
    height: 314px;
    overflow: hidden;
  }

  .select-your-prefered {
    position: absolute;
    top: 32px;
    left: 237px;
  }

  .flagpt-icon {
    position: absolute;
    top: 91px;
    left: 40px;
    width: 77px;
    height: 52px;
    overflow: hidden;
  }

  .portugus {
    position: absolute;
    top: 102px;
    left: 141px;
  }

  .english {
    position: absolute;
    top: 200px;
    left: 141px;
  }

  .flagus-icon {
    position: absolute;
    top: 189px;
    left: 40px;
    width: 78px;
    height: 52px;
    overflow: hidden;
  }

  .language-selection-child {
    position: absolute;
    top: 200px;
    left: 685px;
    border-radius: 4px;
    background-color: #fff;
    border: 1.3px solid #fff;
    box-sizing: border-box;
    width: 31px;
    height: 26px;
  }

  .language-selection-item {
    position: absolute;
    top: 107px;
    left: 685px;
    border-radius: 4px;
    background-color: #fff;
    border: 1.3px solid #fff;
    box-sizing: border-box;
    width: 31px;
    height: 26px;
  }

  .vector-icon1 {
    position: absolute;
    height: 4.21%;
    width: 1.86%;
    top: 73.33%;
    right: 12.14%;
    bottom: 22.46%;
    left: 86%;
    max-width: 100%;
    overflow: hidden;
    max-height: 100%;
    mix-blend-mode: normal;
  }

  .language-selection {
    position: absolute;
    top: 707px;
    left: 513px;
    border-radius: 20px;
    background-color: #131313;
    width: 805px;
    height: 285px;
    overflow: hidden;
  }

  .background {
    position: absolute;
    top: 0px;
    left: -3px;
    background-color: #6b6b6b;
    width: 1824px;
    height: 151px;
  }

  .linearity-gmb {
    position: absolute;
    width: 100%;
    top: calc(50% - 10.55px);
    left: 0%;
    line-height: 26px;
    display: inline-block;
    height: 21.1px;
    mix-blend-mode: normal;
  }

  .footer-ui1 {
    position: absolute;
    width: 100%;
    top: calc(50% + 40.89px);
    right: 0%;
    left: 0%;
    height: 21.1px;
  }

  .c1 {
    position: absolute;
    top: 0px;
    left: 108px;
    font-size: 96px;
    font-family: Inika;
    color: #c48930;
    text-align: left;
    display: inline-block;
    width: 57px;
    height: 101.5px;
  }

  .footer-ui {
    position: absolute;
    width: 16.2%;
    top: calc(50% - 62px);
    right: 40.69%;
    left: 43.11%;
    height: 124px;
  }

  .footer {
    position: absolute;
    top: 3259px;
    left: -2px;
    background-color: #fff;
    width: 1821px;
    height: 124px;
    overflow: hidden;
    font-size: 16px;
    font-family: Inter;
  }

  .settingspage {
    width: 100%;
    position: relative;
    background-color: #222831;
    height: 3383px;
    overflow: hidden;
    text-align: center;
    font-size: 24px;
    color: #fff;
    font-family: Inika;
  }
</style>
