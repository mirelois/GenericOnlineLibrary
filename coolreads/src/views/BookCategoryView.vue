<script setup>

import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

</script>

<template>
  <div class="body">
    <b class="category-fantasy">Category - {{ category }}</b>
	<input type="text" class="my-search-box" placeholder="Search for books..." />
    <div class="separator"></div>
    <div class="separator"></div>
    <div class="books-search-result">
      <div class="book-row">
        <img class="book-icon" alt="" src="/img/desporto.png">
        <img class="book-icon" alt="" src="/img/aventura.png">
        <img class="book-icon" alt="" src="/img/misterio.png">
        <img class="book-icon" alt="" src="/img/biografia.png">
        <img class="book-icon" alt="" src="/img/ficcao.png">
      </div>
      <div class="book-row">
        <img class="book-icon" alt="" src="/img/fantasia.png">
        <img class="book-icon" alt="" src="/img/musica.png">
        <img class="book-icon" alt="" src="/img/romance.png">
        <img class="book-icon" alt="" src="/img/manga.png">
        <img class="book-icon" alt="" src="/img/fantasia.png">
      </div>
      <div class="book-row">
        <img class="book-icon" alt="" src="/img/manga.png">
        <img class="book-icon" alt="" src="/img/terror.png">
        <img class="book-icon" alt="" src="/img/manga.png">
        <img class="book-icon" alt="" src="/img/romance.png">
        <img class="book-icon" alt="" src="/img/musica.png">
      </div>
    </div>
  </div>
  <NavComponent></NavComponent>
</template>

<script>
import { defineComponent } from 'vue'
import { useRoute } from 'vue-router'

export default defineComponent({
  name: 'BookCategoryView',
  data() {
    return {
      category: this.$route.params.category
    }
  }
})
</script>


<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Lato:wght@600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400&display=swap');

.body {
margin: 0; line-height: normal;
}

.separator {
  width: 865px;
  left: 415px;
  height: 1px;
  background-color: #858585;
  margin-bottom: 20px;
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
.my-search-box {
  position: absolute;
  width: 865px;
  height: 70px;
  top: 405px;
  left: 415px;
  padding-left: 35px;
  font-size: 22px;
  background: url("/img/Search.svg") no-repeat left;
    background-color: rgba(0, 0, 0, 0);
  background-color: rgba(0, 0, 0, 0);
  border-radius: 15px;
  border-width: 3px;
    border-top-width: 3px;
    border-right-width: 3px;
    border-bottom-width: 3px;
    border-left-width: 3px;
  overflow: hidden;
  background-color: #6b6b6b;
  z-index: 32;
  border-style: solid;
  border-color: black;
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
.book-icon {
  	width: 125.9px;
  	position: relative;
  	height: 188.8px;
  	object-fit: cover;
}
.book-row {
  	display: flex;
  	flex-direction: row;
  	align-items: flex-end;
  	justify-content: flex-start;
  	gap: 56.7px;
}
.books-search-result {
  	position: absolute;
  	left: 427px;
  	width: 914px;
  	height: 672.3px;
  	display: flex;
  	flex-direction: column;
  	align-items: flex-start;
  	justify-content: flex-start;
  	gap: 52.9px;
}
.bookspage-child {
  	position: absolute;
  	top: 449px;
  	left: 427px;
  	border-radius: 25px;
  	background-color: #858585;
  	width: 856px;
  	height: 92px;
}
.search-icon1 {
  	position: absolute;
  	top: 468px;
  	left: 451px;
  	width: 46px;
  	height: 46px;
}
.search-for-books1 {
  	position: absolute;
  	top: 476px;
  	left: 519px;
  	font-size: 25px;
  	color: #3b3b3b;
}
.pagination-child {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	box-shadow: 0px 4.972476959228516px 4.97px rgba(0, 0, 0, 0.2);
  	border-radius: 32.32px;
  	background-color: #f7f9ff;
  	width: 542px;
  	height: 68.4px;
}
.vector-icon {
  	position: absolute;
  	height: 23.39%;
  	width: 1.66%;
  	top: 38.01%;
  	right: 82.63%;
  	bottom: 38.6%;
  	left: 15.71%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
  	object-fit: contain;
}
.child {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	border-radius: 50%;
  	background-color: #8798d4;
  	width: 42px;
  	height: 42px;
}
.div1 {
  	position: absolute;
  	top: 10.5px;
  	left: 10.5px;
  	font-weight: 600;
  	display: flex;
  	align-items: flex-end;
  	justify-content: center;
  	width: 21px;
  	height: 19.5px;
}
.div3 {
  	width: 21px;
  	position: relative;
  	font-weight: 600;
  	display: flex;
  	align-items: flex-end;
  	justify-content: center;
  	height: 19.5px;
  	flex-shrink: 0;
}
.vector-icon1 {
  	width: 9px;
  	position: relative;
  	height: 16px;
}
.parent {
  	position: absolute;
  	top: 10.5px;
  	left: 78px;
  	width: 363px;
  	height: 19.5px;
  	display: flex;
  	flex-direction: row;
  	align-items: flex-start;
  	justify-content: flex-start;
  	gap: 36px;
  	color: #656565;
}
.div {
  	position: absolute;
  	top: 13px;
  	left: 80px;
  	width: 441px;
  	height: 42px;
}
.pagination {
  	position: absolute;
  	top: 1567px;
  	left: 584px;
  	width: 542px;
  	height: 68.4px;
  	text-align: center;
  	font-size: 18px;
  	font-family: Lato;
}
.order-by {
  	position: absolute;
  	top: 0%;
  	left: 0%;
  	font-weight: 500;
}
.listbox-title {
  	position: absolute;
  	height: 6.92%;
  	width: 90.91%;
  	top: 0%;
  	right: 4.55%;
  	bottom: 93.08%;
  	left: 4.55%;
}
.listboxbg {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.1);
  	border-radius: 25px;
  	background-color: #e7e7e7;
}
.chevron-icon1 {
  	position: absolute;
  	height: 8.33%;
  	width: 3.57%;
  	top: 46.67%;
  	right: 7.86%;
  	bottom: 45%;
  	left: 88.57%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
  	opacity: 0.8;
}
.order-by1 {
  	position: absolute;
  	top: 0%;
  	left: 0%;
}
.placeholder-text {
  	position: absolute;
  	height: 33.33%;
  	width: 65.71%;
  	top: 33.33%;
  	right: 28.57%;
  	bottom: 33.33%;
  	left: 5.71%;
}
.listbox-main {
  	position: absolute;
  	height: 20.76%;
  	width: 90.91%;
  	top: 9.69%;
  	right: 4.55%;
  	bottom: 69.55%;
  	left: 4.55%;
  	color: #666;
}
.div9 {
  	position: absolute;
  	top: 12px;
  	left: 16px;
}
.item-1 {
  	align-self: stretch;
  	position: relative;
  	background-color: #fff;
  	height: 44px;
  	overflow: hidden;
  	flex-shrink: 0;
}
.dropdown-list {
  	position: absolute;
  	width: 90.91%;
  	top: 68px;
  	right: 4.55%;
  	left: 4.55%;
  	box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.1);
  	border-radius: 8px;
  	height: 1px;
  	overflow: hidden;
  	display: flex;
  	flex-direction: column;
  	align-items: center;
  	justify-content: center;
  	gap: 1px;
  	opacity: 0;
}
.clip-list-child {
  	position: absolute;
  	top: -19px;
  	left: 61px;
  	width: 100px;
  	height: 100px;
  	overflow: hidden;
}
.clip-list {
  	position: absolute;
  	height: 59.52%;
  	width: 92.86%;
  	top: 40.48%;
  	right: 0%;
  	bottom: 0%;
  	left: 7.14%;
  	overflow: hidden;
  	color: #333;
}
.sort-component {
  	position: absolute;
  	top: 575px;
  	left: 427px;
  	width: 308px;
  	height: 289px;
  	font-family: Montserrat;
}
.bookspage-item {
  	position: absolute;
  	top: 760px;
  	left: 426.5px;
  	border-top: 1px solid #fff;
  	box-sizing: border-box;
  	width: 857px;
  	height: 1px;
}
.category-fantasy {
  	position: absolute;
    color: #fff;
  	top: 319px;
  	left: 427px;
  	font-size: 44px;
    font-family: Inika;
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
  	font-family: Inter;
}
.bookspage {
  	width: 100%;
  	position: relative;
  	background-color: #222831;
  	height: 3383px;
  	overflow: hidden;
  	text-align: left;
  	font-size: 16px;
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

</style>
