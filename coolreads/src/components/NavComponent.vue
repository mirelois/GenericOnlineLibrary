<template>
    <main>
        <div class="navegador">
            <div class="logo"><div class="text-wrapper">C</div></div>
       		<div class="profile-section" @click="openProfile" id="profileSectionContainer"  v-bind:style="{ 'background-color': computedColor }">
                <img class="vuesaxoutlineframe-icon" alt="" src="/img/frame.svg">
                <img class="chevron-icon" alt="" src="/img/Chevron.svg">
                <div class="c">Carmen</div>
            </div>
            <div v-if="isProfileOpen==true" tabindex="0" class="dropdown-profile">
                <div class="viewprofileoption" id="viewProfileOptionContainer">
                    <a href="/profile"><<div class="view-profile"><a>View Profile</a></div></a>
                </div>
                <div class="signoutoption" id="signoutOptionContainer">
                <div class="view-profile"><a>Sign Out</a></div>
                </div>
            </div>  
            <a href="#settings"><div class="settings-section">
                <img class="settings" src="/img/settings.svg"/>Settings    
            </div></a>
            <a href="/"><div class="home-section">
                <img class="vuesax-outline-home" src="/img/home-2.svg"/>Home
            </div></a>
            <div>
                <input type="text" v-model="inputtxt" class="my-search-box" placeholder="Search for books, readers and writers . . ." name="search">
                <div v-if="inputtxt!=''" id="myDropdown" class="dropdown-content">
                    <div class="txtinserted">Results for: {{textInserted}}</div>
                    <div class="myresult" v-for="result in results">
                        <a :href="`/books/${result.isbn}`" :key="result.isbn">{{result.title}}<img :src="result.imageUrl" class="result-img" width="30px" height="50px" /></a>
                    </div>
                </div>
            </div>
            <a href="/bookmenu"><div class="books-section">
                <img class="book-light" src="/img/Book_light.svg"/>Books
            </div></a>
            <a href="/notifications"><div class="notifications">
                <img class="bell-light" src="/img/Bell_light.svg"/>Notifications
            </div></a>
            <a href="/bookshelves/all">
            	<div class="bookshelf-section">
                    <img class="icon" alt="" src="/img/bookshelf.svg">
                    <div class="c">Bookshelf</div>
                </div> 
            </a>
            <div class="registration-login">
                <a href="/signup" class="registration-button">Registo</a>
                <a href="/login" class="login-button">Login</a>
            </div>
        </div>
    </main>
</template>
<script>
import axios from 'axios';
export default {
    data(){
        return {
            isProfileOpen:false,
            backgroundColor: "#000000",
            inputtxt:'',
            results:[]
        }
    },
    computed: {
        computedColor: function () {
            return this.backgroundColor;
        }        
    },
    methods: {
        openProfile() {
            if(this.isProfileOpen==true) this.backgroundColor = "#000000"
            else this.backgroundColor = "#da9f46d9"
            
            this.isProfileOpen = !this.isProfileOpen
            console.log("here:"+isProfileOpen)
        },
        getResults(){
            // fazer get dos books com um titulo que inclui o inputxtGET http://localhost:8080/book/name?title={{$random.alphanumeric(8)}}
            axios.get('http://localhost:8080/book/name?title='+this.inputtxt).then((books)=>{
                this.results = books.data;
                let index = 0;
                this.results.forEach(b => {
                    if (b.title.length > 50) {
                    this.results[index].title = this.results[index].title.slice(0, 50) + '...';
                    index+=1;
                } 
                });
                console.log(this.results);
            }).catch(erro=>{
                console.log(erro);
            })
            
        }
    },
    computed:{
        textInserted(){
            return this.inputtxt;
        }
    },
    watch:{
        inputtxt() {
            this.getResults();
        }
    }
}
</script>


<style scoped>
.dropdown-content {
    display: block;
    position: absolute;
    background-color: #a8a8a8;
    width: 550px;
    overflow: auto;
    border: 1px solid #ddd;
    top: 89px;
    left: 465px;
    padding-left: 35px;
    min-width: 550px;
    z-index: 31;
}
.txtinserted{
    margin-top:10px;
}
.result-img{
    margin-left:10px;
}

.myresult{
    display:flexbox;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

a {
    color:white;
}

a:hover { text-decoration: underline; }
.navegador {
    position:fixed; 
    width: 100%;
    height: 115px;
    top:0px;
    left:0px;
    background-color: #000000;
    z-index:30;
}

.navegador .logo {
    display: inline-flex;
    flex-direction: column;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 10px;
    padding: 10px;
    position: absolute;
    top: 10px;
    left: 33px;
}

.navegador .text-wrapper {
    position: relative;
    width: fit-content;
    margin-top: -16px;
    margin-bottom: -14px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #c3882f;
    font-size: 96px;
    letter-spacing: 0;
    line-height: normal;
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
    height: 65px;
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
    width: 100%;
    position: relative;
    height: 124px;
    text-align: left;
    font-size: 24px;
    color: #fff;
    font-family: Inika;
    left: 1032px;
    top:115px;
}


.navegador .vuesax-outline-frame {
    position: relative;
    width: 31.73px;
    height: 31.73px;
}

.navegador .div {
    position: relative;
    width: fit-content;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #ffffff;
    font-size: 24px;
    letter-spacing: 0;
    line-height: normal;
}
.navegador .profile-section {
    position: absolute;
    color: #ffffff;
    top: 0px;
    left: 1032px;
    width: 184px;
    height: 115px;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 11px;
    font-size: 22px;
} 

.navegador .profile-section:hover {
    cursor: pointer;
}

.navegador .settings-section {
    display: flex;
    width: 159px;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 11px;
    position: absolute;
    top: 0;
    left: 1216px;
    font-size: 22px;
}

.navegador .settings-section:hover {
    background-color: #da9f46d9; 
}

.navegador .settings {
    position: relative;
    width: 31px;
    height: 31px;
}

.navegador .home-section {
    display: flex;
    width: 144px;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 17px;
    position: absolute;
    top: 0;
    left: 141px;
    font-size: 22px;
}

.navegador .bookshelf-section {
    position: absolute;
    top: -1px;
    left: 1610px;
    width: 209px;
    height: 115px;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    color:white;
    font-size: 22px;
} 

.navegador .bookshelf-section:hover {
    background-color: #da9f46d9;
}

.navegador .home-section:hover {
    background-color: #da9f46d9; 
}

.navegador .vuesax-outline-home {
    position: relative;
    width: 32.25px;
    height: 32.25px;
}

.my-search-box {
  position: absolute;
  width: 556px;
  height: 70px;
  top: 22px;
  left: 462px;
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

::placeholder {
  color: black;
  opacity: 0.5; 
}

.navegador .search {
    position: absolute;
    width: 30px;
    height: 30px;
    top: 20px;
    left: 20px;
}

.navegador .p {
    position: absolute;
    top: 21px;
    left: 62px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #2f3134;
    font-size: 20px;
    letter-spacing: 0;
    line-height: normal;
}

.navegador .books-section {
    display: flex;
    width: 142px;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 15px;
    padding: 41px 7px;
    position: absolute;
    top: -1px;
    left: 285px;
    font-size: 22px;
}

.navegador .books-section:hover {
    background-color: #da9f46d9; 
}

.navegador .book-light {
    position: relative;
    width: 34px;
    height: 34px;
    margin-top: -0.5px;
    margin-bottom: -0.5px;
}

.navegador .notifications {
    display: flex;
    width: 235px;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 8px;
    position: absolute;
    top: 0;
    left: 1375px;
    font-size: 22px;
}

.navegador .notifications:hover {
    background-color: #da9f46d9; 
}

.navegador .bell-light {
    position: relative;
    width: 33px;
    height: 33px;
}
.registration-login {
    display: flex;
    gap: 10px;
    position: absolute;
    top: 10px;
    right: 20px; 
}

.registration-button,
.login-button {
    padding: 10px 20px;
    background-color: #da9f46d9;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-decoration: none; 
}

.registration-button:hover,
.login-button:hover {
    background-color: #c3882f;
}
</style>