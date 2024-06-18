<script setup>
import NavComponent from '../components/NavComponent.vue'
import BookComponent from '../components/BookCoverComponent.vue'
import FooterComponent from '../components/FooterComponent.vue';
import ShelfSideBarComponent from '../components/ShelfSideBarComponent.vue';

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
    <main>
        <div class="books-page">
            <ShelfSideBarComponent v-if="username!==''" :username="username"></ShelfSideBarComponent>
            <div class="text-wrapper-4">{{ translations.bookshelf }}: {{ bookshelfname }}</div> 
        <input v-model="search_input" class="search-bookshelf" type="text" placeholder="Filter by book name"/> 
        <div class="my-sort-component">
            <div class="listbox-title">
                <img v-if="showDropdownMenu==true" class="my-chevron-icon-d" @click='showMenu' alt="" src="/img/downdroplist.svg">
                <img v-if="showDropdownMenu==false" class="my-chevron-icon-d" @click='showMenu' alt="" src="/img/updroplist.svg">
            <div class="order-by-title">{{ translations.orderBy }}</div>
        </div>
        <div class="listbox-main">
            <div class="listboxbg">
            </div>
            <div class="placeholder-text">
                <div class="order-by-placeholder">{{selectedOption}}</div>
            </div>
        </div>
        <div v-show="showDropdownMenu==true" class="clip-list">
            <div class="dropdown-list">
                <div class="item-option-d" @click="sortBooks(`Date`)">
                    <div class="item-content">{{ translations.date }}</div>
                </div>
                <div class="item-option-d" @click="sortBooks(`Title`)">
            <div class="item-content">{{ translations.title }}</div>
            </div>
            <div class="item-option-d" @click="sortBooks(`Rate`)">
            <div class="item-content">{{ translations.rate }}</div>
        </div>
    </div>
    <div class="clip-list-child">
    </div>
        </div>     
    </div>
        <div class="div">
            <div class="ibooks-template">
                <div class="overlap">
                    <img class="img" src="/img/bookshelf.png" />
                    <div class="book-row">
                        <div class="book">
                            <div class="overlap-group">
                                <div v-for="book in displayBooksPerPage" v-if="!book">
                                    <BookComponent :categories_page="false" @removeBook="showConfirmDeletion" :cover="book.coverImage" :bookISBN="book.bookISBN" ></BookComponent>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-if="showConfirmDel==true">
            <ConfirmComponent @confirmation_response="confirmdeleteBook" :header_msg="confirm_msg"></ConfirmComponent>
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
    <NavComponent :username="username"></NavComponent>
</main>
</template>
<script>
import axios from "axios";
import ConfirmComponent from '@/components/ConfirmComponent.vue';
import router from '@/router';
import authHeader from '@/services/auth.header';
export default {
    components: {
        NavComponent,
        BookComponent,
        ConfirmComponent
    },
    data(){
        return {
            bookshelf:[],  //{ titulo:"biografia", rate:2.3,launchDate:"2011-10-11", cover: "/img/biografia.png", id: 1 },
            activate : [],
            showDropdownMenu:false,
            page: 0,
            maxPerPage : 30,
            nrpages : 0,
            search_input: '',
            filtered: [],
            selectedOption: 'Select Sorting Operation',
            bookshelfname:'',
            showConfirmDel:false,
            username:'',
            confirm_msg:'Are you sure you want to remove the book?',
            removeBook:''
        }
    },
    methods: {
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
            this.selectedOption = param
            this.page = 0; 
            this.nrpages = Math.ceil(this.filtered.length / this.maxPerPage);
            this.initializeActivate();
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
        },
        initializeActivate(){
            this.activate = new Array(this.nrpages).fill(false)
            this.activate[0] = true;
        },
        getBooks(){
            let header = authHeader();
            let config = {headers:header}
            header['Content-Type']='application/json';
            this.bookshelfname = this.$route.params.bookshelfname;
            axios.get('http://localhost:8080/customer/'+this.username+'/bookshelf/'+this.bookshelfname,config).then(books=>{
                this.bookshelf = books.data;
                console.log("allbooks");
                console.log(books.data);
            }).catch(err=>{
                console.log(err)
            })
        },
        confirmdeleteBook(resp){
            if(resp=="no") this.showConfirmDel= false;
            else{
                let header = authHeader();
                let config = {headers:header}
                header['Content-Type']='application/json';
                axios.delete('http://localhost:8080/customer/'+this.username+'/bookshelf/personalBook?isbn='+this.removeBook,config).then(resp=>{
                    this.bookshelf = this.bookshelf.filter(b=> b.bookISBN!=this.removeBook)
                    this.showConfirmDel = false;
                }).catch(error=>{
                    console.log(error)
                })
            }
            console.log(resp)
        },
        showConfirmDeletion(book){
            console.log("showing")
            this.removeBook = book;
            this.showConfirmDel=true;
        },
        setUsername(username){
			this.username=username;
		}
    },
    created(){
        const token = localStorage.getItem('user');
        if (!token || this.$store.state.auth.status.loggedIn===false) {
            router.push({path:'/login'})
            return;
        }

        try {
            const decodedToken = JSON.parse(token);
            if(decodedToken.info.exp<Date.now()/1000) {
                router.push({path:'/login'})
            }
            this.setUsername(decodedToken.info.sub);
        } catch (error) {
            console.error('Error parsing user token:', error);
        }

        this.getBooks();
        this.nrpages = Math.ceil(this.filtered.length / this.maxPerPage);
        this.initializeActivate();
    },
    computed: {
        displayBooksPerPage(){
            if (this.search_input !== '') {
                this.filtered = this.bookshelf.filter(b => b.title.toLowerCase().includes(this.search_input.toLowerCase()))
            }else{
                this.filtered = this.bookshelf
            }
            console.log("olee")
            const startIndex = this.maxPerPage * this.page;
            const endIndex = startIndex + this.maxPerPage;
            this.nrpages = Math.ceil(this.filtered.length / this.maxPerPage);
            if(startIndex>=0 && startIndex<this.filtered.length && endIndex>0 && endIndex<this.filtered.length) return this.filtered.slice(startIndex, endIndex);
            else if(endIndex>=this.filtered.length) {
                const endIndex=this.filtered.length;
                return this.filtered.slice(startIndex, endIndex);
            }else return this.filtered;
        }
    }
}
</script>
<style>
@media (min-width: 1024px) {
    #app {
            padding: 0;
    }
}
#app {
    max-width: 3000px;
    font-weight: normal;
}
.newfooter {
    margin-top: 500px;
    margin-left: -800px;
}
::placeholder {
  color: black;
}

.books-page .overlap {
  position: relative;
  width: 1000px;
  height: 1763px;
  top: 550px;
  left: 100px;
}

.line-div {
  width: 100%;
  position: relative;
  border-right: 1px solid #dccfcf;
  box-sizing: border-box;
  height: 3176.1px;
  left:-2240px;
} 

.books-page .book-row {
    display: flex;
    width: 1060px;
    align-items: flex-end;
    gap: 45px;
    position: absolute;
    top: 29px;
    left: 80px;
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
    width: 30px;
    left: -18%;
    overflow: hidden;
    object-fit: contain;
    height: 30px;
}
.vector-icon:hover {
    cursor:pointer;
}
.vector-icon1 {
    width: 30px;
    position: relative;
    height: 30px;
}
.vector-icon1:hover {
    cursor:pointer;
}

.child {
    position: absolute;
    top: 0px;
    left: 0px;
    border-radius: 50%;
    background-color: #8798d4;
    width: 62px;
    height: 62px;
}
.div3 {
    width: 32px;
    position: relative;
    font-weight: 600;
    display: flex;
    align-items: flex-end;
    justify-content: center;
    height: auto;
    flex-shrink: 0;
}
.parent {
    position: absolute;
    top: 20px;
    left: 90px;
    width: 363px;
    height: 19.5px;
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: flex-start;
    gap: 26px;
    color: #656565;
    font-size: 20px;
}
.pagination {
    width: 100%;
    position: relative;
    height: 68.4px;
    text-align: center;
    font-size: 18px;
    color: #fff;
    font-family: Lato;
} 
.overlap-group {
    display: grid;
    grid-template-columns: auto auto auto auto auto auto;
    grid-template-rows : auto auto auto auto auto auto;
    grid-gap: 10px;
    grid-row-gap:70px;
} 
.order-by-title {
  position: absolute;
  top: 22px;
  left: 0%;
  font-weight: 500;
}

.listbox-title {
  position: absolute;
  font-family: "Inika-Regular", Helvetica;
  height: 6.92%;
  width: 90.91%;
  top: -20px;
  right: 4.55%;
  bottom: 93.08%;
  left: 4.55%;
  font-size: 25px;
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
    background-color: #c2c2c2;
}
.chevron-icon-d {
  position: absolute;
  height: 30px;
  width: 30px;
  z-index: 11;
  margin-top: 88px;
  margin-left: 320px;
}
.chevron-icon-d:hover {
    cursor:pointer;
}
.my-chevron-icon-d {
  position: absolute;
  height: 30px;
  width: 30px;
  z-index: 11;
  margin-top: 77px;
  margin-left: 320px;
}
.my-chevron-icon-d:hover {
    cursor:pointer;
}

.order-by-placeholder {
  	position: absolute;
  	top: 0%;
  	left: 0%;
    font-size: 18px;
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
.item-content {
  	position: absolute;
  	top: 12px;
  	left: 16px;
}
.item-dropdown {
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
  top: 60px;
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
  height: 130px;
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
.sort-component {
  width: 400px;
  top: 370px;
  left: 1400px;
  margin: 20px auto;
  text-align: left;
  font-size: 16px;
  color: #fff;
  font-family: Montserrat;
  position: absolute;
  height: 450px;
}

.my-sort-component {
  width: 400px;
  top: 380px;
  left: 1400px;
  margin: 20px auto;
  text-align: left;
  font-size: 16px;
  color: #fff;
  font-family: Montserrat;
  position: absolute;
  height:400px;
}
body {
	margin-left: -80px !important;
    background-color: #222831;
}

</style>
