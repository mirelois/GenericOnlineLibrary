<script setup>
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
    <div class="popup-overlay" @click.self="closePopup">
      <div class="change-password-popup">
        <div class="header">
          <img @click="closePopup" class="close-icon" src="/img/close.svg" alt="Close" />
        </div>
        <div class="content">
          <div class="input-group">
            <label for="current-password">{{ translations.currentPassword }}</label>
            <input v-model="currentPassword" type="password" id="current-password" />
          </div>
          <div class="input-group">
            <label for="new-password">{{ translations.newPassword }}</label>
            <input v-model="newPassword" type="password" id="new-password" />
          </div>
          <button class="confirm-button" @click="confirmChange">{{ translations.confirm }}</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentPassword: '',
        newPassword: ''
      };
    },
    methods: {
      closePopup() {
        this.$emit('close');
      },
      confirmChange() {
        console.log('Current Password:', this.currentPassword);
        console.log('New Password:', this.newPassword);
        this.closePopup();
      }
    }
  };
  </script>
  
  <style scoped>
  .popup-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .change-password-popup {
    background: #131313;
    padding: 20px;
    border-radius: 10px;
    width: 300px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  
  .header {
    display: flex;
    justify-content: flex-end;
  }
  
  .close-icon {
    cursor: pointer;
    width: 24px;
    height: 24px;
  }
  
  .content {
    display: flex;
    flex-direction: column;
    gap: 15px;
    color: white;
  }
  
  .input-group {
    display: flex;
    flex-direction: column;
    gap: 5px;
  }
  
  button.confirm-button {
    padding: 10px;
    background: #c48930;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  </style>
  