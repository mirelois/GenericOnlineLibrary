<script setup>
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
</script>

<template>
    <div class="notificationspage-child"></div>
    <b class="all-notifications">All Notifications</b>
    <div class="notification2all">
      <div class="notification">
        <img class="clock-icon" alt="" src="/img/clock.svg">
        <b class="time">15 min</b>
        <b class="message">Marlena liked your review of Soul by Alice Wilson.</b>
      </div>
      <div class="notification">
        <img class="clock-icon" alt="" src="/img/clock.svg">
        <b class="time">1 h</b>
        <b class="message">Johnny liked your review of Good Services by Lou Downe.</b>
      </div>
    </div>
    <NavComponent></NavComponent>
    
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.all-notifications {
  color: #fff;
  position: absolute;
  top: 284px;
  left: 326px;
  font-size: 60px;
}

.notificationspage-child {
  position: absolute;
  top: 459.5px;
  left: 536.5px;
  border-right: 1px solid #fff;
  box-sizing: border-box;
  width: 1px;
  height: 857px;
}

.notification2all {
  color: white;
  position: absolute;
  top: 470px;
  left: 326px;
  width: 946px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.notification {
  position: relative;
  display: flex;
  align-items: center;
  height: 60px;
}

.clock-icon {
  position: absolute;
  left: 0;
  margin-left: 40px;
  width: 40px;
  height: 40px;
  overflow: hidden;
}

.time {
  position: absolute;
  margin-left: 40px;
  left: 50px;
  font-size: 20px;
  color: #fff;
}

.message {
  position: absolute;
  margin-left: 150px;
  left: 120px;
  font-size: 20px;
  color: #fff;
}

.notificationspage {
  width: 100%;
  position: relative;
  background-color: #222831;
  height: 3383px;
  overflow: hidden;
  text-align: left;
  font-size: 30px;
  color: #fff;
  font-family: Inika;
}
</style>