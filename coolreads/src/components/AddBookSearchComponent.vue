<template>
    <main>
        <div class="add-book-shelf-card">
            <div class="div">
                <div class="books-search-result">
                <div class="add-book-row">
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                </div>
                <div class="add-book-row">
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                </div>
                <div class="add-book-row">
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                    <img class="img" src="/img/book.png" />
                </div>
                </div>
                <input class="overlap-group" placeholder="Search for books">
                <img @click="closeSearchBox" class="menus-close-line" src="/img/close_line_round.svg" />
                </div>
            </div>
          </input>
    </main>
</template>
<script>
  export default {
    methods: {
      closeSearchBox(){
        this.$emit('close')
        console.log("pressionado")
      }
    }
  }
</script>
<style>
.add-book-shelf-card {
  background-color: #d9d9d9;
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}

.add-book-shelf-card .div {
  background-color: #d9d9d9;
  border-radius: 20px;
  overflow-x: hidden;
  width: 1284px;
  height: 800px;
  position: relative;
  z-index: 20;
}

.add-book-shelf-card .books-search-result {
  display: flex;
  flex-direction: column;
  width: 914px;
  height: 672px;
  align-items: flex-start;
  gap: 80px;
  position: absolute;
  top: 226px;
  left: 150px;
}

.add-book-shelf-card .add-book-row {
  display: inline-flex;
  align-items: flex-end;
  gap: 56.65px;
  position: relative;
  flex: 0 0 auto;
  row-gap: 20px;
}

.add-book-shelf-card .book {
  position: relative;
  width: 150px;
  height: 249.27px;
  margin-bottom: -32.73px;
  margin-left: -30.21px;
  object-fit: cover;
}

.add-book-shelf-card .img {
  position: relative;
  width: 150px;
  height: 249.27px;
  margin-top: -27.7px;
  margin-bottom: -32.73px;
  object-fit: cover;
}

.add-book-shelf-card .book-2 {
  position: relative;
  width: 186.33px;
  height: 249.27px;
  margin-top: -27.7px;
  margin-bottom: -32.73px;
  margin-right: -30.22px;
  object-fit: cover;
}

.add-book-shelf-card .overlap-group {
  position: absolute;
  width: 1012px;
  height: 100px;
  top: 46px;
  left: 115px;
  background-color: #ffffff;
  border-radius: 25px;
}

.add-book-shelf-card .text-wrapper {
  position: absolute;
  top: 45px;
  left: 119px;
  font-family: "Inika-Regular", Helvetica;
  font-weight: 400;
  color: #959595;
  font-size: 35px;
  letter-spacing: 0;
  line-height: normal;
}

.add-book-shelf-card .search {
  position: absolute;
  width: 56px;
  height: 56px;
  top: 38px;
  left: 42px;
}

.add-book-shelf-card .menus-close-line {
  position: absolute;
  width: 40px;
  height: 40px;
  top: 44px;
  left: 1193px;
  cursor: pointer;
}
</style>