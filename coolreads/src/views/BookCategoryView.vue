<script setup>

import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

</script>

<template>
	<div class="body">
	  <NavComponent></NavComponent>
	  <b class="category-fantasy">Category - {{ category }}</b>
	  <input type="text" class="my-search-box" placeholder="Search for books..." />
	  <div class="sort-component">
		<div class="listbox-title" @click="showMenu">
		  <img v-if="showDropdownMenu" class="chevron-icon-d" alt="" src="/img/updroplist.svg">
		  <img v-if="!showDropdownMenu" class="chevron-icon-d" alt="" src="/img/downdroplist.svg">
		  <div class="order-by-title">Order By</div>
		</div>
		<div class="listbox-main">
		  <div class="listboxbg"></div>
		  <div class="placeholder-text">
			<div class="order-by-placeholder">{{ selectedOption }}</div>
		  </div>
		</div>
		<div v-show="showDropdownMenu" class="clip-list">
		  <div class="dropdown-list">
			<div class="item-option-d" @click="sortBooks('Date')">
			  <div class="item-content">Date</div>
			</div>
			<div class="item-option-d" @click="sortBooks('Title')">
			  <div class="item-content">Title</div>
			</div>
			<div class="item-option-d" @click="sortBooks('Rate')">
			  <div class="item-content">Rate</div>
			</div>
		  </div>
		</div>
	  </div>
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
	  <div class="pagination">
            <div class="pagination-child">
            </div>
            <div class="div">
            <div class="parent">
            <img class="vector-icon" @click="backPage()" alt="" src="/img/back.svg">
            <div v-for="(n,index) in nrpages" class="div3" :class="activate[n -1]==true? 'child':''">{{n}}</div>
            <img class="vector-icon1" @click="nextPage()" alt="" src="/img/front.svg">
            </div>
            </div>
            <div class="newfooter">
                <FooterComponent></FooterComponent>
            </div>
        </div>
	</div>
  </template>
  

<script>
import { defineComponent } from 'vue';
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

export default defineComponent({
  components: {
    NavComponent,
    FooterComponent
  },
  data() {
    return {
      category: this.$route.params.category,
      showDropdownMenu: false,
      selectedOption: 'Select an option'
    };
  },
  methods: {
    showMenu() {
		this.showDropdownMenu = !this.showDropdownMenu;
	},
    sortBooks(option) {
		this.selectedOption = option;
		this.showDropdownMenu = false;
    },
	nextPage(){
		console.log(this.page)
		console.log(this.nrpages)
		if(this.page+2<=this.nrpages){
			this.activate[this.page] = !this.activate[this.page]
			this.page = this.page + 1;
			this.activate[this.page] = !this.activate[this.page]
		}
    },
    backPage(){
		if(this.page-1>=0){
			this.activate[this.page] = !this.activate[this.page]
			this.page = this.page - 1;
			this.activate[this.page] = !this.activate[this.page]
		}
	}
  }
});
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
  position: absolute;
  width: 865px;
  top: 655px;
  left: 415px;
  height: 1px;
  background-color: #858585;
  margin-bottom: 20px;
}
.my-search-box {
  position: absolute;
  width: 865px;
  height: 70px;
  top: 385px;
  left: 415px;
  padding-left: 35px;
  font-size: 22px;
  background: url("/img/Search.svg") no-repeat left;
  background-color: rgba(0, 0, 0, 0);
  border-radius: 15px;
  border-width: 3px;
  border-top-width: 3px;
  border-right-width: 3px;
  border-bottom-width: 3px;
  border-left-width: 3px;
  background-color: #6b6b6b;
  z-index: 32;
  border-style: solid;
  border-color: black;
}
.books-search-result {
  	position: absolute;
  	left: 427px;
  	width: 914px;
	top: 695px;
  	height: 672.3px;
  	display: flex;
  	flex-direction: column;
  	align-items: flex-start;
  	justify-content: flex-start;
  	gap: 52.9px;
}
.book-row {
  	display: flex;
  	flex-direction: row;
  	align-items: flex-end;
  	justify-content: flex-start;
  	gap: 56.7px;
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

.book-icon {
  	width: 125.9px;
  	position: relative;
  	height: 188.8px;
  	object-fit: cover;
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
	
	width: 9px;
  	position: relative;
  	height: 16px;
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
  font-family: "Inika-Regular", Helvetica;
  height: 6.92%;
  width: 90.91%;
  top: -25px;
  right: 4.55%;
  bottom: 93.08%;
  left: 4.55%;
  font-size: 25px;
}
.listboxbg {
  position: absolute;
  height: 70%;
  width: 100%;
  top: 0%;
  right: 0%;
  bottom: 0%;
  left: 0%;
  box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.1);
  border-radius: 25px;
  background-color: #c2c2c2;
}
.chevron-icon-d {
  position: absolute;
  height: 30px;
  width: 30px;
  top: 330%;
  right: 7.86%;
  bottom: 45%;
  left: 88.57%;
  max-width: 100%;
  max-height: 100%;
  z-index: 11;
}

.chevron-icon-d:hover {
  cursor: pointer;
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
.item-content {
  position: absolute;
  top: 12px;
  left: 16px;
}
.order-by-placeholder {
  position: absolute;
  top: -46%;
  left: 0%;
  font-size: 18px;
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
  width: auto;
  top: 0px;
  right: 7%;
  left: 0%;
  box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  height: 1px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 250px;
  z-index: 10;
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
  top: 12.48%;
  right: 0%;
  bottom: 0%;
  left: 7.14%;
  color: #333;
}

.sort-component {
  width: 400px;
  top: 500px;
  left: 400px;
  margin: 20px auto;
  text-align: left;
  font-size: 16px;
  color: #fff;
  font-family: Montserrat;
  position: absolute;
}
.item-option-d {
  align-self: stretch;
  position: relative;
  background-color: #c2c2c2;
  height: 44px;
  overflow: hidden;
  flex-shrink: 0;
}
.item-option-d:hover {
  cursor: pointer;
}

.category-fantasy {
  	position: absolute;
    color: #fff;
  	top: 259px;
  	left: 427px;
  	font-size: 44px;
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
