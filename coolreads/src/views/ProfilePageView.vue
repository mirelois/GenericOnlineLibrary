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
	<div class="posFoot"><FooterComponent></FooterComponent></div>
		
	
<NavComponent></NavComponent>
</template>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');



.parent {
  	position: absolute;
  	height: 423.77%;
  	width: 105.71%;
  	top: -162%;
  	right: -2.85%;
  	bottom: -161.77%;
  	left: -2.86%;
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
	font-size: 40px;
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
.posFoot{
	position: absolute;
	top: 3259px;
  	left: 1px;
  	width: 671px;
  	height: 124px;
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


</style>