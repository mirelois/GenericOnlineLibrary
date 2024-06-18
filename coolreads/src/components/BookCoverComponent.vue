<template>
     <div class="box">
        <div class="book">
            <div class="property-minorbook">
                <div class="overlap-group">
                    <a :href="`/book/${bookISBN}`"><img class="book-2" :src="cover"/></a>
                    <div v-if="categories_page==false" ><img class="remove" @click="showConfirmDeletion" src="/img/minus-circle.svg"/></div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
export default{
    props: {
        cover: String,
        bookISBN: String,
        categories_page: Boolean
    },
    methods:{
        showConfirmDeletion(){
            console.log("transmitting")
            this.$emit('removeBook',this.bookISBN);
        }
    }
}
</script>
<style>

.box {
    position: relative;
    width: 200px;
    height: auto;
}

.box .book {
    position: fixed;
    top: 0;
    left: 0;
    border-radius: 5px;
    overflow: visible;
    border: 0px;
}

.box .property-minorbook {
    width: 180px;
    height: 280px;
}

.box .overlap-group {
    overflow: visible;
    position: relative;
    width: 195px;
    height: 291px;
    top: -11px;
}

.box .img {
    position: absolute;
    width: 100%;
    height: 280px;
    top: 11px;
    left: 0;
}

.box .remove {
    position: absolute;
    width: 100%;
    height: 30px;
    top: 0;
    left: 165px;
    z-index: 1;
    cursor:pointer;
}

.box .property-majorbook {
    width: 378px;
    height: 580px;
    top: 320px;
    position: absolute;
    left: 20px;
}

.box .book-2 {
    position: absolute;
    width: 179px;
    height: 300px;
    top: 0;
    left: 0;
    object-fit: cover;
}

.book-2:hover{
    position: absolute;
    width: 392px;
    height: 599px; 
    display: block;
    z-index: 10; 
}

.book-2:hover ~ .remove {
  display: none
}
</style>