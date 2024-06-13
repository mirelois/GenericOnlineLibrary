<script setup>

import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

</script>

<template>
    <div class="notificationspage-child">
    </div>
        <b class="all-notifications">All Notifications</b>
        <div class="notification2all">
            <img class="clock-icon" alt="" src="/img/clock.svg">
            <b class="min">15 min</b>
            <b class="marlena-liked-your">Marlena liked your review of Soul by Alice Wilson.</b>
            <img class="clock-icon1" alt="" src="/img/clock.svg">
            <b class="h">1 h</b>
            <b class="johnny-liked-your">Johnny liked your review of Good Services by Lou Downe.</b>
        </div>
    <NavComponent></NavComponent>
    
</template>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.chevron-icon {
  	width: 34px;
  	position: relative;
  	height: 17px;
  	opacity: 0.8;
}
.profile-section {
  	position: absolute;
  	top: 0px;
  	left: 1032px;
  	width: 184px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	gap: 11px;
}
.settings-icon {
  	width: 31px;
  	position: relative;
  	height: 31px;
  	overflow: hidden;
  	flex-shrink: 0;
}
.settings-section {
  	position: absolute;
  	top: 0px;
  	left: 1216px;
  	width: 159px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	gap: 11px;
}
.vuesaxoutlinehome-2-icon {
  	width: 32.3px;
  	position: relative;
  	height: 32.3px;
}
.home-section {
  	position: absolute;
  	top: 0px;
  	left: 141px;
  	width: 144px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	gap: 17px;
}
.search-icon {
  	position: absolute;
  	top: 20px;
  	left: 20px;
  	width: 30px;
  	height: 30px;
}
.search-for-books {
  	position: absolute;
  	top: 22px;
  	left: 62px;
}
.search-box {
  	position: absolute;
  	top: 22px;
  	left: 462px;
  	border-radius: 15px;
  	background-color: #6b6b6b;
  	width: 556px;
  	height: 70px;
  	overflow: hidden;
  	font-size: 20px;
  	color: #2f3134;
}
.book-light-icon {
  	width: 34px;
  	position: relative;
  	height: 34px;
}
.books-section {
  	position: absolute;
  	top: -1px;
  	left: 285px;
  	width: 142px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	padding: 41px 7px;
  	box-sizing: border-box;
  	gap: 15px;
}
.bell-light-icon {
  	width: 33px;
  	position: relative;
  	height: 33px;
}
.notifications-section {
  	position: absolute;
  	top: 0px;
  	left: 1375px;
  	width: 235px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	gap: 8px;
}
.icon {
  	width: 36px;
  	position: relative;
  	height: 33px;
  	overflow: hidden;
  	flex-shrink: 0;
}
.bookshelf-section {
  	position: absolute;
  	top: -1px;
  	left: 1610px;
  	width: 209px;
  	height: 115px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	gap: 8px;
}
.navegador {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  	background-color: #000;
  	width: 1819px;
  	height: 115px;
  	overflow: hidden;
  	font-size: 24px;
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
.all-notifications {
    color: #fff;
  	position: absolute;
  	top: 284px;
  	left: 326px;
  	font-size: 60px;
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
  	left: 0px;
  	background-color: #fff;
  	width: 1821px;
  	height: 124px;
  	overflow: hidden;
  	text-align: center;
  	font-size: 16px;
  	font-family: Inter;
}
.clock-icon {
    color: white;
  	position: absolute;
  	top: -5px;
  	left: 9px;
  	width: 40px;
  	height: 40px;
  	overflow: hidden;
}
.clock-icon1 {
  	position: absolute;
  	top: 50px;
  	left: 10px;
  	width: 40px;
  	height: 40px;
  	overflow: hidden;
}
.min {
  	position: absolute;
  	top: 0px;
  	left: 78px;
}
.marlena-liked-your {
  	position: absolute;
  	top: 10px;
  	left: 240px;
}
.notification1all {
    color: #fff;
  	position: absolute;
  	top: 510px;
  	left: 326px;
  	width: 946px;
  	height: 60px;
}
.notification2all-child {
  	position: absolute;
  	top: -0.5px;
  	left: 210.5px;
  	border-right: 1px solid #fff;
  	box-sizing: border-box;
  	width: 1px;
  	height: 857px;
}

.h {
  	position: absolute;
  	top: 55px;
  	left: 87px;
}
.johnny-liked-your {
    color: #fff;
  	position: absolute;
  	top: 60px;
  	left: 240px;
}
.notification2all {
    color: white;
	padding-bottom: 20px;
  	position: absolute;
  	top: 470px;
  	left: 326px;
  	width: 946px;
  	height: 856px;
}
.notification2all img,
.notification2all b {
    margin-bottom: 20px; 
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


.view-profile {
  	position: relative;
}
.viewprofileoption {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	background-color: rgba(219, 160, 70, 0.85);
  	border: 1px solid #fff;
  	box-sizing: border-box;
  	width: 184px;
  	height: 60px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	cursor: pointer;
}
.signoutoption {
  	position: absolute;
  	top: 64px;
  	left: 0px;
  	background-color: rgba(219, 160, 70, 0.85);
  	border: 1px solid #fff;
  	box-sizing: border-box;
  	width: 184px;
  	height: 60px;
  	display: flex;
  	flex-direction: row;
  	align-items: center;
  	justify-content: center;
  	cursor: pointer;
}
.dropdown-profile {
  	width: 184px;
  	position: relative;
  	height: 124px;
  	max-width: 90%;
  	max-height: 90%;
  	overflow: auto;
  	text-align: left;
  	font-size: 24px;
  	color: #fff;
  	font-family: Inika;
}


</style>