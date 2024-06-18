<template>
    <div class="books-page">
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
          <div class="div">
                <div class="overlap">
                    <div class="my-book-row">
                        <div class="book">
                            <div class="overlap-group">
                            <div class="mywrapper" v-for="(row, index) in paginatedBooks" :key="index"> 
                            <BookComponent :categories_page="true" v-for="book in row" :bookISBN="book.isbn" :cover="book.imageUrl" />
                          </div>
                        </div>
                      </div>
                  </div>
              </div>
          </div>
      <div class="pagination">
        <div class="pagination-child">
          <div class="parent">
            <img class="vector-icon" @click="backPage" alt="" src="/img/back.svg">
            <div v-for="(n, index) in nrPages" :key="index" class="div3" :class="{ 'child': activate[n - 1] }" @click="goToPage(n - 1)">{{ n }}</div>
            <img class="vector-icon1" @click="nextPage" alt="" src="/img/front.svg">
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
    setup() {
      const route = useRoute();
      const store = useStore();
      const username = computed(() => {if (store.state.auth.status.loggedIn) return store.state.auth.user.info.sub; else return '';});
      const category = computed(() => route.params.category);
  
      const showDropdownMenu = ref(false);
      const selectedOption = ref('Select an option');
      const searchInput = ref('');
      const page = ref(0);
      const maxPerPage = 10;
      const activate = ref([]);
      const books = ref([]);
      const filtered = ref([]);
  
      const showMenu = () => {
        showDropdownMenu.value = !showDropdownMenu.value;
      };
  
      const sortBooks = (param) => {
        if (param === 'Date') {
          filtered.value.sort((a, b) => new Date(a.insertDate) - new Date(b.insertDate));
        } else if (param === 'Rate') {
          filtered.value.sort((a, b) => a.rate - b.rate);
        } else {
          filtered.value.sort((a, b) => a.title.localeCompare(b.title));
        }
        selectedOption.value = param;
        page.value = 0;
        initializeActivate();
      };
  
      const nextPage = () => {
        if (page.value + 1 < nrPages.value) {
          activate.value[page.value] = false;
          page.value += 1;
          activate.value[page.value] = true;
        }
      };
  
      const backPage = () => {
        if (page.value > 0) {
          activate.value[page.value] = false;
          page.value -= 1;
          activate.value[page.value] = true;
        }
      };
  
      const goToPage = (pageIndex) => {
        activate.value[page.value] = false;
        page.value = pageIndex;
        activate.value[page.value] = true;
      };
  
      const initializeActivate = () => {
        activate.value = new Array(nrPages.value).fill(false);
        if (activate.value.length > 0) activate.value[0] = true;
      };
  
      const nrPages = computed(() => Math.ceil(filtered.value.length / maxPerPage));
  
      const paginatedBooks = computed(() => {
        const start = page.value * maxPerPage;
        const end = start + maxPerPage;
        const booksForPage = filtered.value.slice(start, end);
        const rows = [];
        for (let i = 0; i < booksForPage.length; i += 5) {
          rows.push(booksForPage.slice(i, i + 5));
        }
        return rows;
      });
  
      const fetchBooksByCategory = async () => {
        try {
          const response = await axios.get(`http://localhost:8080/book/genre?genre=${encodeURIComponent(category.value.toLowerCase())}`);
          books.value = response.data;
          filtered.value = books.value;
          console.log('Books fetched successfully!', books.value);
          initializeActivate();
        } catch (error) {
          console.error('Error fetching books by category:', error);
        }
      };
  
      watch(searchInput, (newSearch) => {
        if (newSearch) {
          filtered.value = books.value.filter((book) =>
            book.title.toLowerCase().includes(newSearch.toLowerCase())
          );
        } else {
          filtered.value = books.value;
        }
        initializeActivate();
      });
  
      onMounted(() => {
        fetchBooksByCategory();
      });
  
      return {
        category,
        username,
        showDropdownMenu,
        selectedOption,
        showMenu,
        searchInput,
        page,
        nrPages,
        activate,
        nextPage,
        backPage,
        goToPage,
        paginatedBooks,
        sortBooks,
      };
    },
  };
  </script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400;700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Lato:wght@600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400&display=swap');
.bodyCategory {
margin: 0; line-height: normal;
}
.bodyCategory .overlap {
  position: relative;
  flex-direction: row;
  width: 1179px;
  height: 1163px;
  top: 190px;
  left: -1050px;
}
.mywrapper{
  display:flex;
}
.bodyCategory .divm:not(#searchbox) {
    width: 600px;
    height: 700px;
    position: absolute;
    flex-direction: row;
} 
.bodyCategory .book {
    position: relative;
    width: 40px;
    height: 100px;
    flex-direction: row;
} 
.bodyCategory .my-book-row {
    display: flex;
    width: 1060px;
    align-items: flex-end;
    flex-direction: row;
    gap: 45px;
    position: absolute;
    top: 29px;
    left: 80px;
}
.bodyCategory ~ .remove {
  display: none
}
.books-page .my-book-row {
  display: flex;
  width: 1060px;
  align-items: flex-end;
  gap: 45px;
  position: absolute;
  top: 160px;
  left: -180px;
}

.books-page .book {
    position: relative;
    width: 180px;
    height: 280px;
} 

.books-page {
	width: 2000px;
	display: flex;
	flex-direction: row;
}
.books-page .div:not(#searchbox) {
  width: 1300px;
  height: 800px;
  position: absolute;
  left: 500px;
}

.books-page .text-wrapper-4 {
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

.books-page .remove {
    position: absolute;
    width: 30px;
    height: 30px;
    top: 0;
    left: 165px;
}
.books-page .book-add {
    position: absolute;
    width: 179px;
    height: 280px;
    top: 29px;
    left: 68px;
    cursor: pointer;
}
.books-page .div-wrapper {
    position: relative;
    width: 180px;
    height: 280px;
    box-shadow: var(--book-shadow);
    background: linear-gradient(180deg, rgba(221, 221, 221, 0) 0%, rgb(221, 221, 221) 100%);
}
.books-page .text-wrapper-3 {
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

.books-page .pagination {
    position: absolute;
    width: 542px;
    height: 68px;
    top: 2400px;
    left: 800px;
    background-color: #f6f8ff;
    border-radius: 32.32px;
    box-shadow: 0px 4.97px 4.97px #00000033;
}

.books-page .overlap-2 {
    position: absolute;
    width: 445px;
    height: 42px;
    top: 13px;
    left: 80px;
}

.books-page .vector {
    top: 12px;
    left: 433px;
    position: absolute;
    width: 8px;
    height: 16px;
}

.books-page .element {
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

.books-page .overlap-group-2 {
    position: absolute;
    width: 42px;
    height: 42px;
    top: 0;
    left: 0;
    background-color: #8698d4;
    border-radius: 21px;
}
.books-page .text-wrapper-5 {
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
.books-page .frame {
    display: flex;
    width: 363px;
    height: 20px;
    align-items: flex-start;
    gap: 36px;
    position: absolute;
    top: 10px;
    left: 78px;
}
.books-page .text-wrapper-6 {
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
.books-page .vector-2 {
    top: 26px;
    left: 14px;
    position: absolute;
    width: 8px;
    height: 16px;
}
.books-page .search-bookshelf {
    position: absolute;
    width: 739px;
    height: 57px;
    top: 444px;
    left: 615px;
    background-color: #e6e6e66e;
    border-radius: 25px;
}

.books-page .search-2 {
    position: absolute;
    width: 33px;
    height: 34px;
    top: 11px;
    left: 16px;
}
.books-page .text-wrapper-7 {
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

.vector-icon {
	width: 9px;
  	position: absolute;
  	height: 16px;
}
.vector-icon1 {
  	width: 9px;
  	position: absolute;
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
.overlap-group {
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
.div {
  	position: absolute;
  	top: 13px;
  	left: 80px;
  	width: 441px;
  	height: 42px;
}
.pagination {
  	position: absolute;
  	top: 1467px;
  	left: 464px;
  	width: 642px;
  	height: 68.4px;
  	text-align: center;
  	font-size: 18px;
  	font-family: Lato;
}
.pagination-child {
  	position: absolute;
  	top: 0px;
  	left: 0px;
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
