<script setup>
import { ref, computed } from 'vue';
import { useStore } from 'vuex';
import NavComponent from '../components/NavComponent.vue';
import ChangePasswordComponent from '../components/ChangePasswordComponent.vue';

const showChangePasswordPopup = ref(false);
const Bookshelfprivacy = ref('public');

const store = useStore();

const loggedIn = computed(() => store.state.auth.status.loggedIn);
const email = computed(() => {if (store.state.auth.status.loggedIn) return store.state.auth.user.email; else return '';});
const username = computed(() => {if (store.state.auth.status.loggedIn) return store.state.auth.user.info.sub; else return '';});
const translations = computed(() => store.getters['language/currentTranslations']);
const selectedLanguage = computed(() => store.state.language.selectedLanguage);

function setLanguage(language) {
  store.dispatch('language/setLanguage', language);
}

function setPrivacy(option) {
  Bookshelfprivacy.value = option;
}

function openChangePasswordPopup() {
  showChangePasswordPopup.value = true;
}

function closeChangePasswordPopup() {
  showChangePasswordPopup.value = false;
}
</script>

<template>
  <NavComponent :username="username"/>
  <div v-if="loggedIn" class="account-info">
    <div class="email">{{ translations.email }}</div>
    <div class="password">{{ translations.password }}</div>
    <div class="goodreadsuser1gmailcom">{{ email }}</div>
    <div class="changepassword" @click="openChangePasswordPopup">
      <div class="changepassword-child">
        <div class="change-password-text">{{ translations.changePassword }}</div>
      </div>
    </div>
  </div>
  <ChangePasswordComponent v-if="showChangePasswordPopup" @close="closeChangePasswordPopup" />
  <div class="language-selection">
    <div class="select-your-prefered">{{ translations.selectLanguage }}</div>
    <div class="language-option" @click="setLanguage('portuguese')" :class="{ selected: selectedLanguage === 'portuguese' }">
      <img class="flagpt-icon" alt="" src="/img/PT.svg">
      <div class="language-name">Português</div>
    </div>
    <div class="language-option" @click="setLanguage('english')" :class="{ selected: selectedLanguage === 'english' }">
      <img class="flagus-icon" alt="" src="/img/US.svg">
      <div class="language-name">English</div>
    </div>
  </div>
  <div class="privacy-settings">
    <div class="privacy-settings-title">{{ translations.privacyTitle }}</div>
    <div class="privacy-options">
      <div class="privacy-option" @click="setPrivacy('public')" :class="{ selected: Bookshelfprivacy === 'public' }">
        {{ translations.public }}
      </div>
      <div class="privacy-option" @click="setPrivacy('friends')" :class="{ selected: Bookshelfprivacy === 'friends' }">
        {{ translations.friends }}
      </div>
      <div class="privacy-option" @click="setPrivacy('private')" :class="{ selected: Bookshelfprivacy === 'private' }">
        {{ translations.private }}
      </div>
    </div>
  </div>
</template>

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

  .privacy-settings-title {
  margin: 20px;
  font-size: 24px;
  text-align: center;
}

.privacy-options {
  flex-direction: column;
  display: flex;
  justify-content: space-around;
}

.privacy-option {
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  padding: 10px;
  margin-bottom: 15px;
  border: 1px solid transparent;
  transition: all 0.3s;
}

.privacy-option:hover {
  border-color: #fff;
}

.selected {
  background-color: #fff;
  color: #131313;
}
  .private {
    position: absolute;
    top: 92px;
    left: 640px;
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
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.select-your-prefered {
  margin: 20px;
  font-size: 24px;
  text-align: center;
}

.language-name {
  font-size: 20px;
}
.flagpt-icon, .flagus-icon {
  width: 50px;
  height: 30px;
  margin-right: 10px;
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
  .language-selection {
    position: absolute;
    top: 707px;
    left: 513px;
    border-radius: 20px;
    color: #fff;
    background-color: #131313;
    width: 805px;
    height: 285px;
    overflow: hidden;
  }
  .language-option {
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  padding: 10px;
  margin-bottom: 15px;
  border: 1px solid transparent;
  transition: all 0.3s;
}

.language-option:hover {
  border-color: #fff;
}
  .background {
    position: absolute;
    top: 0px;
    left: -3px;
    background-color: #6b6b6b;
    width: 1824px;
    height: 151px;
  }

</style>
