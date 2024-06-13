<script setup>
import NavComponent from '../components/NavComponent.vue';
import ShelfSideBarComponent from '../components/ShelfSideBarComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
</script>
<template>
  <main>
    <NavComponent :username="username"></NavComponent>
    <ShelfSideBarComponent v-if="username!==''" :username="username" :profileImg="profileImg"></ShelfSideBarComponent>
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
	created() {
    const token = localStorage.getItem('user');
    if (!token || this.$store.state.auth.status.loggedIn===false) {
      return;
    }

    try {
      const decodedToken = JSON.parse(token);
      if(decodedToken.info.exp<Date.now()) {
        handle_logout();
      }
      this.setUsername(decodedToken.info.sub);
    } catch (error) {
      console.error('Error parsing user token:', error);
    }
  },
	methods:{
		setUsername(username){
			this.username=username;
		},
    handle_logout(){
            this.$store.dispatch('auth/logout').then(
            () => {
                router.go()
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
      );
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