<script setup>
import NavComponent from '../components/NavComponent.vue';
</script>
<template>
    <main>
        <b class="order-by">Genres</b>
        <b class="for-you">For You</b>
        <div class="allcategories">
            <div class="image-4-parent" @click="navigateToCategory('Adventure')">
            <img class="image-4-icon" alt="" src="/img/aventura.png">
            <b class="adventure">Adventure</b>
            </div>
            <div class="image-6-parent" @click="navigateToCategory('Mystery')">
            <img class="image-4-icon" alt="" src="/img/misterio.png">
            <b class="mystery">Mystery</b>
            </div>
            <div class="image-5-parent" @click="navigateToCategory('Biography')">
            <img class="image-4-icon" alt="" src="/img/biografia.png">
            <b class="biography">Biography</b>
            </div>
            <div class="image-2-parent" @click="navigateToCategory('Sci-Fi')">
            <img class="image-4-icon" alt="" src="/img/ficcao.png">
            <b class="sci-fi">Sci-Fi</b>
            </div>
            <div class="image-3-parent" @click="navigateToCategory('Fantasy')">
            <img class="image-4-icon" alt="" src="/img/fantasia.png">
            <b class="fantasy">Fantasy</b>
            </div>
            <div class="image-9-parent" @click="navigateToCategory('Music')">
            <img class="image-4-icon" alt="" src="/img/musica.png">
            <b class="music">Music</b>
            </div>
            <div class="rectangle-parent" @click="navigateToCategory('Sports')">
            <div class="group-child">
            </div>
            <img class="image-4-icon" alt="" src="/img/desporto.png">
            <b class="sports">Sports</b>
            </div>
            <div class="rectangle-group" @click="navigateToCategory('Romance')">
            <div class="group-item">
            </div>
            <img class="image-4-icon" alt="" src="/img/romance.png">
            <b class="music">Romance</b>
            </div>
            <div class="rectangle-container" @click="navigateToCategory('Horror')">
            <div class="group-item">
            </div>
            <img class="image-12-icon" alt="" src="/img/terror.png">
            <b class="horror">Horror</b>
            </div>
            <div class="image-7-parent" @click="navigateToCategory('Manga')">
            <img class="image-7-icon" alt="" src="/img/manga.png">
            <b class="manga">Manga</b>
            </div>
        </div>
        <NavComponent :username="username"></NavComponent>
    </main> 
</template>

<script>
import router from '../router/index'

export default {
    data(){
        return{
            username:''
        }
    },
    methods: {
        navigateToCategory(category) {
        router.push({ name: 'bookCategoria', params: { category } });
        }
    ,
    setUsername(username){
		this.username=username;
    }
  },
  created(){
        const token = localStorage.getItem('user');
		let username = JSON.parse(token).info.sub;
		this.setUsername(username);
  }
}

</script>


<style scoped>

.order-by {
    position: absolute;
    top: 60%;
    color: white;
    font-size: 33px;
    font-family: Inika;
    left: 300px;
}
.for-you {
    top: 20%;
    position: absolute;
    left: 300px;
    font-size: 33px;
    font-family: Inika;
    color: #fafafa;
}
.booksmenupage-child {
    position: absolute;
    top: 424px;
    left: 365px;
    border-radius: 2px;
    width: 150px;
    height: 250px;
    object-fit: cover;
}
.booksmenupage-item {
    position: absolute;
    top: 424px;
    left: 777px;
    border-radius: 2px;
    width: 150px;
    height: 250px;
    object-fit: cover;
}
.booksmenupage-inner {
    position: absolute;
    top: 424px;
    left: 570px;
    border-radius: 2px;
    width: 150px;
    height: 250px;
    object-fit: cover;
}
.rectangle-icon {
    position: absolute;
    top: 424px;
    left: 982px;
    border-radius: 2px;
    width: 150px;
    height: 250px;
    object-fit: cover;
}
.image-4-icon {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 200px;
    height: 200px;
    object-fit: cover;
}
.adventure {
    position: absolute;
    top: 153.33px;
    left: 8px;
    display: inline-block;
    width: 162.7px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.allcategories{
    width:auto;
    height:1500px;
}
.image-4-parent {
    position: absolute;
    top: 913px;
    left: 756px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}
.mystery {
    position: absolute;
    top: 153px;
    left: 13.33px;
    display: inline-block;
    width: 145.3px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.image-6-parent {
    position: absolute;
    top: 913px;
    left: 1010px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}
.biography {
    position: absolute;
    top: 153px;
    left: 13.33px;
    display: inline-block;
    width: 153.3px;
    height: 47.4px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.image-5-parent {
    position: absolute;
    top: 913px;
    left: 1263px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}
.sci-fi {
    position: absolute;
    top: 153px;
    left: 8px;
    display: inline-block;
    width: 162.7px;
    height: 40px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.image-2-parent {
    position: absolute;
    top: 913px;
    left: 248px;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    width: 200px;
    height: 200px;
    cursor: pointer;
}

.fantasy {
    position: absolute;
    top: 153px;
    left: 9.33px;
    display: inline-block;
    width: 109.3px;
    height: 34.7px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}

.image-3-parent {
    position: absolute;
    top: 913px;
    left: 502px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}

.music {
    position: absolute;
    top: 153px;
    left: 13px;
    display: inline-block;
    width: 101.3px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}

.image-9-parent {
    position: absolute;
    top: 1166px;
    left: 502px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}
.group-child {
    position: absolute;
    top: 0px;
    left: 2.96px;
    background-color: #000;
    width: 197px;
    height: 200px;
}

.sports {
    position: absolute;
    top: 153px;
    left: 15.76px;
    display: inline-block;
    width: 128.7px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}

.rectangle-parent {
    position: absolute;
    top: 1166px;
    left: 756px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}
.group-item {
    position: absolute;
    top: 0px;
    left: 0px;
    background-color: #000;
    width: 200px;
    height: 200px;
}
.rectangle-group {
    position: absolute;
    top: 1166px;
    left: 1010px;
    width: 200px;
    height: 200px;
    cursor: pointer;
}

.image-12-icon {
    position: absolute;
    top: 0px;
    left: 1px;
    width: 200px;
    height: 200px;
    object-fit: cover;
}
.horror {
    position: absolute;
    top: 153px;
    left: 13px;
    display: inline-block;
    width: 101.3px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.rectangle-container {
    position: absolute;
    top: 1166px;
    left: 1263px;
    width: 201px;
    height: 200px;
    cursor: pointer;
}
.image-7-icon {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 199px;
    height: 200px;
    object-fit: cover;
}
.manga {
    position: absolute;
    top: 153px;
    left: 12.33px;
    display: inline-block;
    width: 74.7px;
    height: 32px;
    font-size: 22px;
    font-weight: bold;
    color:white;
}
.image-7-parent {
    position: absolute;
    top: 1166px;
    left: 250px;
    width: 199px;
    height: 200px;
    cursor: pointer;
}
.line-div {
    position: absolute;
    top: 739.5px;
    left: 329.5px;
    border-top: 1px solid #fff;
    box-sizing: border-box;
    width: 1051px;
    height: 1px;
    mix-blend-mode: normal;
}
.booksmenupage-child1 {
    position: absolute;
    top: 424px;
    left: 1189px;
    border-radius: 2px;
    width: 150px;
    height: 250px;
    object-fit: cover;
} 
</style>