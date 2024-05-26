<template>
    <main>
        <div class="navegador">
            <div class="logo"><div class="text-wrapper">C</div></div>
            <a href="#profile"><div class="profile-section">
                <img class="vuesax-outline-frame" src="/img/frame.svg"/>Profile
            </div></a>
            <a href="#settings"><div class="settings-section">
                <img class="settings" src="/img/settings.svg"/>Settings    
            </div></a>
            <a href="/"><div class="home-section">
                <img class="vuesax-outline-home" src="/img/home-2.svg"/>Home
            </div></a>
            <div class="search-box">
                <input type="text" placeholder="Search for books, readers and writers . . ." name="search">
            </div>
            <a href="/books"><div class="books-section">
                <img class="book-light" src="/img/Book_light.svg"/>Books
            </div></a>
            <a href="#notifications"><div class="notifications">
                <img class="bell-light" src="/img/Bell_light.svg"/>Notifications
            </div></a>
        </div>
    </main>
</template>
<style>

a {
    color:white;
}

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

.navegador .profile-section {
    display: flex;
    width: 157px;
    height: 115px;
    align-items: center;
    justify-content: center;
    gap: 11px;
    position: absolute;
    top: 0;
    left: 1059px;
    font-size: 22px;
}

.navegador .profile-section:hover {
    background-color: #da9f46d9; 
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

.navegador .home-section:hover {
    background-color: #da9f46d9; 
}

.navegador .vuesax-outline-home {
    position: relative;
    width: 32.25px;
    height: 32.25px;
}

input {
    position: absolute;
    width: 556px;
    height: 70px;
    top: 22px;
    left: 462px;
    font-size: 22px;
    background: url("/img/Search.svg") no-repeat left;
    padding-left: 35px;
    background-color: #6b6b6b;
    border-radius: 15px;
    border-width: 0px;
    overflow: hidden;
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
</style>