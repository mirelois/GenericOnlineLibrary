<script setup>
import NavComponent from '../components/NavComponent.vue';
import ShelfSideBarComponent from '../components/ShelfSideBarComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
</script>
<template>
  <main>
    <NavComponent :username="username"></NavComponent>
    <ShelfSideBarComponent :username="username" :profileImg="profileImg"></ShelfSideBarComponent>
  </main>
</template>
<script>
export default {
  data(){
    return{
      username:'',
			profileImg: 'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'
    }
  },
	created(){
		const token = localStorage.getItem('user');
		let username = JSON.parse(token).info.sub;
		this.setUsername(username);
	},
	methods:{
		setUsername(username){
			this.username=username;
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
    margin-top: 0px;
    margin-left: -1400px;
}
</style>