<template>
    <div class="my-books-page">
      <NavComponent :username="username"></NavComponent>
      <b class="category-fantasy">Genre - {{ category }}</b>
      <input v-model="searchInput" type="text" class="my-search-box" placeholder="Search for books..." />
      <div class="sort-component">
        <div class="listbox-title" @click="showMenu">
          <img v-if="showDropdownMenu" class="cat-chevron-icon-d" alt="" src="/img/updroplist.svg">
          <img v-else class="cat-chevron-icon-d" alt="" src="/img/downdroplist.svg">
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
          <div class="catdiv">
                <div class="overlap">
                    <div class="my-book-row">
                        <div class="mybook">
                            <div class="my-overlap-group">
                            <div class="mywrapper" v-for="(value, index) in displayBooksPerPage" :key="index" > 
                            <BookComponent :title="value.title" :categories_page="true" :bookISBN="value.isbn" :cover="value.imageUrl" />
                          </div>
                        </div>
                      </div>
                  </div>
              </div>
          </div>
      <div class="my-pagination">
        <div class="my-pagination-child">
          <div class="parent">
            <img class="my-vector-icon" @click="backPage" alt="" src="/img/back.svg">
            <div class="mydiv3" :class="{ 'mychild': true }">{{ page }}</div>
            <img class="my-vector-icon1" @click="nextPage" alt="" src="/img/front.svg">
          </div>
        </div>
      </div>
      <div class="newfooter"></div>
    </div>
  </template>
  
  <script>
  import { ref, computed, watch, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { useStore } from 'vuex';
  import axios from 'axios';
  import NavComponent from '../components/NavComponent.vue';
  import BookComponent from '../components/BookCoverComponent.vue';
  
  export default {
    components: {
      NavComponent,
      BookComponent,
    },
    data(){
        return {
            activate : [],
            showDropdownMenu:false,
            page: 1,
            maxPerPage : 18,
            nrpages : 0,
            searchInput: '',
            filtered: [],
            newfiltered:[],
            username:'',
            category:'',
            selectedOption: 'Select Sorting Operation'
        }
    },
    created(){
      const route = useRoute();
      const store = useStore();
      this.username = computed(() => {if (store.state.auth.status.loggedIn) return store.state.auth.user.info.sub; else return '';});
      this.category = computed(() => route.params.category);
      this.fetchBooks();
    
    },methods:{
      showMenu(){
        this.showDropdownMenu = !this.showDropdownMenu
      },
      sortBooks(param){
            if(param=="Date"){
                this.filtered.sort((a,b)=> new Date(a.insertDate) - new Date(b.insertDate))
                console.log(this.filtered)
            }
            if(param=="Rate"){
                this.filtered.sort((a,b)=> a.rate-b.rate)
                console.log(this.filtered)
            }
            else{
                this.filtered.sort((a,b)=>a.title.localeCompare(b.title))
            }
        }
      ,
      async fetchBooks(){
        try {
          let mypage = 0;
          if(this.page>0) mypage = this.page - 1;
          const response = await axios.get("http://localhost:8080/api/book/genre?genre="+this.category.toLowerCase()+"&page="+mypage+"&size="+this.maxPerPage);
          if(response.data.length>0) this.filtered = response.data;
          else this.backPage();
          console.log('Books fetched successfully!', this.filtered);
        } catch (error) {
          console.error('Error fetching books by category:', error);
        }
      },
      nextPage(){
        console.log("next")
        this.page = this.page + 1;
        this.fetchBooks();
      },
      backPage(){
        if(this.page>1){
          this.page = this.page - 1;
          this.fetchBooks();
        }
      }
    },
    computed: {
        displayBooksPerPage(){
            if (this.searchInput !== '') {
                this.newfiltered = this.filtered.filter(b => b.title.toLowerCase().includes(this.searchInput.toLowerCase()))
            }else{
                this.newfiltered = this.filtered 
            }
            return this.newfiltered;
          }
    }
}
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Lato:wght@600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400&display=swap');

.my-books-page .my-book-row {
  display: flex;
  width: 1060px;
  align-items: flex-end;
  gap: 45px;
  position: absolute;
  top: 160px;
  left: -180px;
}

.my-books-page .mybook {
    position: relative;
    width: 180px;
    height: 280px;
} 

.my-books-page {
	width: 2000px;
	display: flex;
	flex-direction: row;
}
.my-books-page .div:not(#searchbox) {
  width: 1300px;
  height: 800px;
  position: absolute;
  left: 500px;
}

.my-books-page .text-wrapper-4 {
  position: absolute;
  top: 300px;
  left: 600px;
  font-family: "Inika-Regular", Helvetica;
  font-weight: 400;
  color: #e9e9e9;
  font-size: 80px;
  letter-spacing: 0;
  line-height: normal;
}

.my-books-page .remove {
    position: absolute;
    width: 30px;
    height: 30px;
    top: 0;
    left: 165px;
}

.my-books-page .book {
    position: relative;
    width: 180px;
    height: 280px;
    margin-bottom:40px;
} 
.my-books-page .book-add {
    position: absolute;
    width: 179px;
    height: 280px;
    top: 29px;
    left: 68px;
    cursor: pointer;
}
.my-books-page .div-wrapper {
    position: relative;
    width: 180px;
    height: 280px;
    box-shadow: var(--book-shadow);
    background: linear-gradient(180deg, rgba(221, 221, 221, 0) 0%, rgb(221, 221, 221) 100%);
}
.my-books-page .text-wrapper-3 {
    position: absolute;
    top: 87px;
    left: 68px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #000000;
    font-size: 80px;
    letter-spacing: 0;
    line-height: normal;
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
    cursor:pointer;
} 

.my-books-page .mypagination {
    position: absolute;
    width: 542px;
    height: 68px;
    top: 2400px;
    left: 800px;
    background-color: #f6f8ff;
    border-radius: 32.32px;
    box-shadow: 0px 4.97px 4.97px #00000033;
}

.my-books-page .overlap-2 {
    position: absolute;
    width: 445px;
    height: 42px;
    top: 13px;
    left: 80px;
}

.my-books-page .vector {
    top: 12px;
    left: 433px;
    position: absolute;
    width: 8px;
    height: 16px;
}

.my-books-page .element {
    position: absolute;
    width: 445px;
    height: 42px;
    top: 0;
    left: 0;
}


.search-bookshelf {
    position: relative;
    width: 739px;
    height: 57px;
    padding-left: 35px;
    background: url("/img/Search.svg") no-repeat left;
    background-color: #e6e6e66e;
    border-radius: 25px;
    font-size: 22px;
}

.search-bookshelf .search {
    position: absolute;
    width: 33px;
    height: 34px;
    top: 11px;
    left: 16px;
}
.search-bookshelf .text-wrapper {
    position: absolute;
    height: 26px;
    top: 14px;
    left: 61px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #4e4e4e;
    font-size: 20px;
    text-align: center;
    letter-spacing: 0;
    line-height: normal;
} 

.my-books-page .overlap-group-2 {
    position: absolute;
    width: 42px;
    height: 42px;
    top: 0;
    left: 0;
    background-color: #8698d4;
    border-radius: 21px;
}
.my-books-page .text-wrapper-5 {
    position: absolute;
    height: 20px;
    top: 10px;
    left: 10px;
    color: #ffffff;
    width: 21px;
    font-family: "Lato-SemiBold", Helvetica;
    font-weight: 600;
    font-size: 18px;
    text-align: center;
    letter-spacing: 0;
    line-height: normal;
    white-space: nowrap;
}
.my-books-page .frame {
    display: flex;
    width: 363px;
    height: 20px;
    align-items: flex-start;
    gap: 36px;
    position: absolute;
    top: 10px;
    left: 78px;
}
.my-books-page .text-wrapper-6 {
    position: relative;
    height: 19.5px;
    margin-top: -1.5px;
    color: #646464;
    width: 21px;
    font-family: "Lato-SemiBold", Helvetica;
    font-weight: 600;
    font-size: 18px;
    text-align: center;
    letter-spacing: 0;
    line-height: normal;
    white-space: nowrap;
}
.my-books-page .vector-2 {
    top: 26px;
    left: 14px;
    position: absolute;
    width: 8px;
    height: 16px;
}
.my-books-page .search-bookshelf {
    position: absolute;
    width: 739px;
    height: 57px;
    top: 444px;
    left: 615px;
    background-color: #e6e6e66e;
    border-radius: 25px;
}

.my-books-page .search-2 {
    position: absolute;
    width: 33px;
    height: 34px;
    top: 11px;
    left: 16px;
}
.my-books-page .text-wrapper-7 {
    position: absolute;
    height: 26px;
    top: 14px;
    left: 61px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #4e4e4e;
    font-size: 20px;
    text-align: center;
    letter-spacing: 0;
    line-height: normal;
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
  z-index: 20;
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
.my-vector-icon {
    width: 26px;
    position: absolute;
    height: 26px;
    left: -60px;
    top: -5px;
}

.my-vector-icon1 {
    width: 26px;
    position: absolute;
    height: 26px;
    left: 630px;
    top: -5px;
}
.my-vector-icon:hover {
	cursor:pointer;
}
.my-vector-icon1:hover {
    cursor:pointer;
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
.my-overlap-group {
    display: grid;
    grid-template-columns: auto auto auto auto auto auto;
    grid-template-rows : auto auto auto auto auto auto;
    grid-gap: 10px;
    grid-row-gap:70px;
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
  	top: 25.5px;
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
.catdiv {
    position: absolute;
    top: 555px;
    left: 580px;
    width: 641px;
    height: 42px;
}
.mypagination {
  	position: absolute;
  	left: 464px;
  	width: 642px;
  	height: 68.4px;
  	text-align: center;
  	font-size: 18px;
  	font-family: Lato;
}
.my-pagination-child {
  	position: absolute;
    top: 1900px;
    left: 600px;
  	box-shadow: 0px 4.972476959228516px 4.97px rgba(0, 0, 0, 0.2);
  	border-radius: 32.32px;
  	background-color: #f7f9ff;
  	width: 762px;
  	height: 68.4px;
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
.cat-chevron-icon-d {
  position: absolute;
  height: 30px;
  width: 30px;
  max-width: 100%;
  max-height: 100%;
  z-index: 11;
  margin-left: 310px;
  margin-top: 90px;
}

.cat-chevron-icon-d:hover {
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
