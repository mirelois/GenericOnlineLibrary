<script setup>
import NavComponent from '../components/NavComponent.vue'
import BookComponent from '../components/BookCoverComponent.vue'
import AddBookSearchComponent from '../components/AddBookSearchComponent.vue';
</script>
<template>
    <NavComponent></NavComponent>
    <div class="books-page">
        <div id="searchbox" v-if="showSearchBox==true"><AddBookSearchComponent @close="showSearchBox=false"/></div>
        <div class="text-wrapper-4">My Bookshelf</div> 
        <input class="search-bookshelf" type="text" placeholder="Filter by book name"/> 
        <div class="div">
            <div class="ibooks-template">
                <div class="overlap">
                    <img class="img" src="/img/bookshelf.png" />
                    <div class="book-row">
                        <div class="book">
                            <div class="overlap-group">
                                <div v-for="book in bookshelf" v-if="!book">
                                    <BookComponent :cover="book.cover" ></BookComponent>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    components: {
        NavComponent,
        BookComponent
    },
    data(){
        return {
                bookshelf:[
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 },
                { cover: "/img/book.png", id: 1 }
            ],
            showSearchBox:false
        }
    },
    methods: {
        changeSearchBoxVisibility(){
            this.showSearchBox = !this.showSearchBox
            console.log("estado:"+this.showSearchBox)

        }
    }
}
</script>
<style>

.books-page .overlap {
    position: relative;
    width: 1379px;
    height: 1763px;
    top: -12px;
    left: -277px;
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
.books-page .book {
    position: relative;
    width: 180px;
    height: 280px;
} 

.books-page {
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
}
.books-page .div:not(#searchbox) {
    width: 1300px;
    height: 800px;
    position: absolute;
} 

.books-page .text-wrapper-4 {
    position: absolute;
    top: 201px;
    left: 100px;
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
.books-page .text-wrapper-4 {
    position: absolute;
    top: 201px;
    left: 100px;
    font-family: "Inika-Regular", Helvetica;
    font-weight: 400;
    color: #e9e9e9;
    font-size: 80px;
    letter-spacing: 0;
    line-height: normal;
}

.books-page .pagination {
    position: absolute;
    width: 542px;
    height: 68px;
    top: 2272px;
    left: 534px;
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
    background-color: #e6e6e66e;
    border-radius: 25px;
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
    top: 334px;
    left: 115px;
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

.overlap-group {
    display: grid;
    grid-template-columns: auto auto auto auto auto auto;
    grid-template-rows : auto auto auto auto auto auto;
    grid-gap: 10px;
    grid-row-gap:70px;
} 

body {
  margin: 0;
  background-color: #222831;
}


</style>
