<template>
    <main>
        <div class="navegador">
            <div class="logo"><div class="text-wrapper">C</div></div>
            <div  v-if="username!==''" class="profile-section" @click="openProfile" id="profileSectionContainer"  v-bind:style="{ 'background-color': computedColor }">
                <img class="vuesaxoutlineframe-icon" alt="" src="/img/frame.svg">
                <img class="chevron-icon" alt="" src="/img/Chevron.svg">
                <div v-if="username!==''"class="c">{{username}}</div>
                <div v-if="username===''"class="c">Profile</div>
            </div>
            <div v-if="isProfileOpen===true && username!=''" tabindex="0" class="dropdown-profile">
                <div class="viewprofileoption" id="viewProfileOptionContainer">
                    <a href="/profile">View Profile<div class="view-profile"><a></a></div></a>
                </div>
                <div class="signoutoption" id="signoutOptionContainer">
                <div class="view-profile"><a>Sign Out</a></div>
                </div>
            </div>  
            <a href="/settings"><div div v-if="username!==''" class="settings-section">
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
            <div v-if="username!==''" class="notifications-section" @click="openNotifications" id="notificationsSectionContainer">
                <img class="bell-light" alt="" src="/img/Bell_light.svg">
                <div class="notifications-text">Notifications</div>
            </div>
            <div v-if="isNotificationsOpen==true" tabindex="0" class="dropdown-notifications">
                <div class="notification1">
                    <b class="marlena">
                        <span>Marlena</span>
                        <span class="span">  </span>
                    </b>
                    <div class="liked-your-review">Liked your review of Soul by Olivia Wilson.</div>
                    <div class="min">15min</div>
                </div>
                <div class="notification2">
                    <b class="johnny">Johnny</b>
                    <div class="liked-your-review1">Liked your review of Good Services by Lou Downe.</div>
                    <div class="min">1h</div>
                </div>
                <div class="notification11" id="notification1Container1">
                    <div class="view-all"><a href="/notifications">View All</a></div>
                </div>
            </div>
            <a href="/bookshelves/all">
                <div v-if="username!==''" class="bookshelf-section">
                    <img class="icon" alt="" src="/img/bookshelf.svg">
                    <div class="c">Bookshelf</div>
                </div> 
            </a>
            <button v-if="username!==''" @click="handle_logout()" class="authbtn">Logout</button>
            <div>
                <div v-if="username===''"><button @click="handle_login()" class="authbtn">Login</button></div>
                <div v-if="username===''"><button @click="handle_signup()" class="authbtn">Sign Up</button></div>
            </div>
        </div>
    </main>
</template>

<script>
import axios from 'axios';
import router from "../router/index";
export default {
    props:{username:''},
    data(){
        return {
            isProfileOpen:false,
            isNotificationsOpen:false,
            backgroundColor: "#000000",
            inputtxt:'',
            results:[],
            message:'',
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
        },
        openNotifications() {
            this.isNotificationsOpen = !this.isNotificationsOpen
        },
        getResults(){
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
            
        },
        handle_logout(){
            this.$store.dispatch('auth/logout').then(
            () => {
                router.push({ path: '/' })
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        },
        handle_login(){
            router.push({path:'/login'})
        },
        handle_signup(){
            router.push({path:'/signup'})
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
.authbtn {
    position: relative;
    bottom: 20px;
    right: -2000px; 
    padding: 10px 20px;
    background-color: #da9f46d9;
    color: white;
    border: none;
    border-radius: 5px;
    cursor:pointer;
    width: auto;
    float:left;
    margin:12px;
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

.span {
font-size: 12px;
white-space: pre-wrap;
}
.marlena {
width: 88px;
position: relative;
text-decoration: underline;
display: inline-block;
height: 44px;
flex-shrink: 0;
}
.liked-your-review {
width: 298px;
position: relative;
font-size: 12px;
display: inline-block;
height: 29px;
flex-shrink: 0;
}
.min {
position: relative;
font-size: 15px;
color: #8d502e;
}
.notification1 {
position: absolute;
top: 0px;
left: 0px;
background-color: #dba046;
border: 1px solid #fff;
box-sizing: border-box;
width: 464px;
height: 83px;
display: flex;
flex-direction: row;
align-items: center;
justify-content: center;
gap: 5px;
}
.johnny {
width: 92px;
position: relative;
text-decoration: underline;
display: inline-block;
height: 53px;
flex-shrink: 0;
}
.liked-your-review1 {
width: 299px;
position: relative;
font-size: 20px;
display: inline-block;
height: 46px;
flex-shrink: 0;
margin-bottom: 5px;
}
.notification2 {
position: absolute;
top: 83px;
left: 0px;
background-color: #dba046;
border: 1px solid #fff;
box-sizing: border-box;
width: 464px;
height: 71px;
display: flex;
flex-direction: row;
align-items: center;
justify-content: center;
}
.view-all {
width: 95px;
position: relative;
text-decoration: underline;
display: inline-block;
height: 15px;
flex-shrink: 0;
}
.notification11 {
position: absolute;
top: 154px;
left: 0px;
background-color: #ac7c34;
border: 1px solid #fff;
box-sizing: border-box;
width: 464px;
height: 43px;
display: flex;
flex-direction: row;
align-items: center;
justify-content: center;
cursor: pointer;
font-size: 18px;
}
.notifications-dropdown {
width: 100%;
position: relative;
height: 204px;
text-align: left;
font-size: 22px;
color: #fff;
font-family: Inika;
}
.notifications-section {
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

.notifications-section:hover {
    background-color: #da9f46d9; 
}

.bell-light {
    position: relative;
    width: 33px;
    height: 33px;
}

.dropdown-notifications {
    width: 100%;
    position: absolute;
    height: 209px;
    text-align: left;
    font-size: 22px;
    color: #fff;
    font-family: Inika;
    top: 115px;
    left: 1375px;
}

.notification1, .notification2, .notification11 {
    background-color: #dba046;
    border: 1px solid #fff;
    box-sizing: border-box;
    width: 464px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}

.notification1 {
    height: 83px;
    top: 0;
}

.notification2 {
    height: 71px;
    top: 83px;
}

.notification11 {
    height: 45px;
    top: 154px;
    background-color: #ac7c34;
    cursor: pointer;
    font-size: 18px;
    justify-content: center;
}

.marlena, .johnny {
    width: 92px;
    text-decoration: underline;
    display: inline-block;
    height: 44px;
    flex-shrink: 0;
}

.liked-your-review, .liked-your-review1 {
    width: 298px;
    font-size: 20px;
    display: inline-block;
    height: 39px;
    flex-shrink: 0;
}

.min {
    font-size: 15px;
    color: #8d502e;
}

.notifications-text{
    color:white;
}
</style>