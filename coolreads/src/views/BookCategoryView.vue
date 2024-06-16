<template>
  <div class="body">
      <NavComponent></NavComponent>
      <b class="category-fantasy">{{ translations.category }} {{ translatedCategory }}</b>
      <input v-model="searchInput" type="text" class="my-search-box" :placeholder="translations.searchPlaceholderBooks" />
      <div class="sort-component">
          <div class="listbox-title" @click="showMenu">
              <img v-if="showDropdownMenu" class="chevron-icon-d" alt="" src="/img/updroplist.svg">
              <img v-else class="chevron-icon-d" alt="" src="/img/downdroplist.svg">
              <div class="order-by-title">{{ translations.orderBy }}</div>
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
                      <div class="item-content">{{ translations.date }}</div>
                  </div>
                  <div class="item-option-d" @click="sortBooks('Title')">
                      <div class="item-content">{{ translations.title }}</div>
                  </div>
                  <div class="item-option-d" @click="sortBooks('Rate')">
                      <div class="item-content">{{ translations.rate }}</div>
                  </div>
              </div>
          </div>
      </div>
      <div class="separator"></div>
      <div class="books-search-result">
          <div v-for="(bookRow, rowIndex) in paginatedBooks" :key="rowIndex" class="book-row">
              <img v-for="book in bookRow" :key="book.id" class="book-icon" :src="book.cover" alt="" />
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
import { ref, computed, watch} from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import NavComponent from '../components/NavComponent.vue';

export default {
  components: {
      NavComponent,
  },
  setup() {
      const route = useRoute();
      const store = useStore();

      const translations = computed(() => store.getters['language/currentTranslations']);
      const category = computed(() => route.params.category);
      const translatedCategory = computed(() => {
          const categoryTranslations = {
              'Aventura': translations.value.adventure,
              'Mistério': translations.value.mystery,
              'Biografia': translations.value.biography,
              'Ficção Científica': translations.value.sciFi,
              'Fantasia': translations.value.fantasy,
              'Música': translations.value.music,
              'Desporto': translations.value.sports,
              'Romance': translations.value.romance,
              'Terror': translations.value.horror,
              'Manga': translations.value.manga,
          };
          return category.value ? categoryTranslations[category.value] || category.value : '';
      });

      const showDropdownMenu = ref(false);
      const selectedOption = ref('Select an option');
      const searchInput = ref('');
      const page = ref(0);
      const maxPerPage = 5;
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

      return {
          translations,
          translatedCategory,
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
