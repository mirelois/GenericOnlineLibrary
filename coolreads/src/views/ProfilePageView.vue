<script setup>

import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

</script>
<template>
    <div class="frame">
        <div class="parent">
        </div>
    </div>
    <img class="unsplashj1jylllrpa4-icon" alt="" src="/img/PerfilPic.png">
    <div class="name">
            <div class="carmen-garca-lpez">Carmen García López</div>
            <div class="theythem">(They/Them) </div>
        </div>
        <div class="details">
            <b class="age">Age</b>
            <div class="div">19</div>
            <b class="country">Country</b>
            <b class="gender">Gender</b>
            <div class="spain">Spain</div>
            <div class="non-binary">Non-binary</div>
            <div class="details1">Details</div>
        </div>
    <div class = "section">
        <div class ="container">
            <div class="about-me">About me</div>
        </div>
        <div class="container1">
            <div class="theres-nothing-i-container">
                <span class="theres-nothing-i-container1">
                    <p class="theres-nothing-i">There’s nothing I enjoy more than curling up with a good book, letting myself get lost in the pages of heartfelt </p>
                    <p class="theres-nothing-i">stories and timeless love. My shelves are filled with tales of passion, heartache, and joy, each book a doorway to </p>
                    <p class="theres-nothing-i">another world. Visiting quaint bookstores and attending book clubs where I can share my latest literary </p>
                    <p class="discoveries-with-fellow">discoveries with fellow book lovers brings me immense joy.</p>
                </span>
            </div>
        </div>
        <div class ="container2">
        </div>
        <div class ="container3">
            <div class="about-me">Interests</div>
            <div class="container1">
                <div class="im-passionate-about-container">
                    <span class="theres-nothing-i-container1">
                        <p class="theres-nothing-i">I’m passionate about romance novels, finding joy in the heartfelt tales and timeless love stories that fill my </p>
                        <p class="theres-nothing-i">bookshelves. I also love exploring quaint bookstores and sharing my latest literary finds with fellow enthusiasts</p>
                        <p class="theres-nothing-i"> at book clubs.</p>
                    </span>
                </div>
            </div>
        </div>
        
    </div>
    <div class="highlighted-bookshelf-my-top-parent">
    <div class="highlighted-bookshelf-my">Highlighted Bookshelf: My Top 5!!!</div>
        <div class="book-row">
            <img class="book-icon" alt="" src="/img/aventura.png">
            <img class="book-icon" alt="" src="/img/misterio.png">
            <img class="book-icon" alt="" src="/img/biografia.png">
            <img class="book-icon" alt="" src="/img/ficcao.png">
            <img class="book-icon" alt="" src="/img/fantasia.png">
        </div>
    </div>
    <div class="view-more-wrapper">
        <div class="view-more">View More</div>
    </div>
        
<NavComponent></NavComponent>
</template>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');


body {
  	margin: 0;
  	line-height: normal;
}
.popup-overlay {
  	display: flex;
  	flex-direction: column;
  	position: fixed;
  	inset: 0;
}

.c {
  	position: relative;
}
.logo {
  	position: absolute;
  	top: 0px;
  	left: 33px;
  	height: 115px;
  	display: flex;
  	flex-direction: column;
  	align-items: center;
  	justify-content: center;
  	padding: 10px;
  	box-sizing: border-box;
  	font-size: 96px;
  	color: #c48930;
}
.vuesaxoutlineframe-icon {
  	width: 31.7px;
  	position: relative;
  	height: 31.7px;
}
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
}
.icon1 {
  	position: absolute;
  	height: 99.79%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0.21%;
  	left: 0%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
  	object-fit: cover;
  	mix-blend-mode: saturation;
}
.icon2 {
  	position: absolute;
  	height: 99.79%;
  	width: 100%;
  	top: 0.21%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
  	object-fit: cover;
  	mix-blend-mode: overlay;
}
.parent {
  	position: absolute;
  	height: 423.77%;
  	width: 105.71%;
  	top: -162%;
  	right: -2.85%;
  	bottom: -161.77%;
  	left: -2.86%;
}
.cherry-bomb-1 {
  	position: absolute;
  	height: 112.01%;
  	width: 36.86%;
  	top: -6.29%;
  	right: 8.43%;
  	bottom: -5.72%;
  	left: 54.71%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
  	object-fit: cover;
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
.unsplashj1jylllrpa4-icon {
  	position: absolute;
  	top: 178px;
  	left: 278px;
  	border-radius: 50%;
  	width: 271px;
  	height: 271px;
  	object-fit: cover;
}
.about-me {
  	position: relative;
  	line-height: 25px;
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
  	color: #fff;
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
.theythem {
  	position: relative;
  	font-size: 32px;
  	line-height: 17.5px;
  	color: #fcfcfd;
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
  	font-size: 64px;
  	color: rgba(255, 255, 255, 0.9);
}
.age {
  	position: absolute;
  	top: 85px;
  	left: 34px;
    color: rgba(255, 255, 255, 0.9);
  	text-transform: uppercase;
  	display: flex;
  	align-items: center;
  	width: 193px;
  	height: 44px;
}
.div {
  	position: absolute;
  	top: 85px;
  	left: 169px;
  	font-size: 32px;
  	display: inline-block;
  	width: 333px;
}
.country {
  	position: absolute;
  	top: 204px;
  	left: 34px;
  	text-transform: uppercase;
  	display: flex;
  	align-items: center;
  	width: 193px;
  	height: 44px;
}
.gender {
  	position: absolute;
  	top: 141px;
  	left: 34px;
  	text-transform: uppercase;
  	display: flex;
  	align-items: center;
  	width: 193px;
  	height: 44px;
}
.spain {
  	position: absolute;
  	top: 206px;
  	left: 169px;
  	font-size: 32px;
  	display: inline-block;
  	width: 333px;
}
.non-binary {
  	position: absolute;
  	top: 140px;
  	left: 169px;
  	font-size: 32px;
  	display: inline-block;
  	width: 333px;
}
.details1 {
  	position: absolute;
  	top: 31px;
  	left: 24px;
  	font-size: 48px;
  	line-height: 25px;
  	color: #fcfcfd;
}
.details {
  	position: absolute;
  	top: 545px;
  	left: 16px;
    color: rgba(255, 255, 255, 0.9);
  	background-color: #31363f;
  	width: 891px;
  	height: 277px;
  	overflow: hidden;
}
.highlighted-bookshelf-my {
  	position: absolute;
  	top: 12px;
  	left: 18px;
  	display: flex;
  	align-items: center;
  	width: 482px;
  	height: 48px;
}
.book-icon {
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
  	left: 1px;
  	background-color: #fff;
  	width: 1821px;
  	height: 124px;
  	overflow: hidden;
  	text-align: center;
  	font-size: 16px;
  	font-family: Inter;
}
.view-more {
  	position: absolute;
  	top: 15px;
  	left: 42px;
}
.view-more-wrapper {
  	position: absolute;
  	top: 455px;
  	left: 1590px;
  	border-radius: 72.66px;
  	background-color: #f0effa;
  	width: 201px;
  	height: 72px;
  	overflow: hidden;
  	font-size: 25px;
  	color: #000;
}
.profilepage {
  	width: 100%;
  	position: relative;
  	background-color: #222831;
  	height: 3383px;
  	overflow: hidden;
  	text-align: left;
  	font-size: 24px;
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