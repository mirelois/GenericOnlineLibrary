<template>
  <div>
    <div class="notificationspage-child"></div>
    <b class="all-notifications">All Notifications</b>
    <div class="notification2all">
      <div v-for="notification in notifications" :key="notification.id" class="notification">
        <img class="clock-icon" alt="" src="/img/clock.svg">
        <b class="time">{{ notification.time }}</b>
        <b class="message">{{ notification.message }}</b>
        <button @click="handleDelete(notification.id)">Delete</button>
      </div>
    </div>
    <NavComponent></NavComponent>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import NavComponent from '../components/NavComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';

export default {
  components: {
    NavComponent,
    FooterComponent
  },
  data() {
    return {
      username: '',
    };
  },
  computed: {
    ...mapState({
      notifications: state => state.notifications.notifications,
    }),
  },
  created() {
    const token = localStorage.getItem('user');
    if (!token) {
      console.warn('User token not found in localStorage');
      return;
    }

    try {
      const decodedToken = JSON.parse(token);
      this.username = decodedToken.info.sub;
      this.fetchNotifications();
    } catch (error) {
      console.error('Error parsing user token:', error);
    }
  },
  methods: {
    fetchNotifications() {
      this.$store.dispatch('notifications/fetchNotifications', {
        username: this.username,
        pageNumber: 0,
        pageSize: 10,
      });
    },
    handleDelete(notificationId) {
      this.$store.dispatch('notifications/deleteNotification', notificationId).then(() => {
        console.log('Notification deleted successfully');
      }).catch(error => {
        console.error('Error deleting notification:', error);
      });
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Inika:wght@400&display=swap');

.all-notifications {
  color: #fff;
  position: absolute;
  top: 284px;
  left: 326px;
  font-size: 60px;
}

.notificationspage-child {
  position: absolute;
  top: 459.5px;
  left: 536.5px;
  border-right: 1px solid #fff;
  box-sizing: border-box;
  width: 1px;
  height: 857px;
}

.notification2all {
  color: white;
  position: absolute;
  top: 470px;
  left: 326px;
  width: 946px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.notification {
  position: relative;
  display: flex;
  align-items: center;
  height: 60px;
}

.clock-icon {
  position: absolute;
  left: 0;
  margin-left: 40px;
  width: 40px;
  height: 40px;
  overflow: hidden;
}

.time {
  position: absolute;
  margin-left: 40px;
  left: 50px;
  font-size: 20px;
  color: #fff;
}

.message {
  position: absolute;
  margin-left: 150px;
  left: 120px;
  font-size: 20px;
  color: #fff;
}

.notificationspage {
  width: 100%;
  position: relative;
  background-color: #222831;
  height: 3383px;
  overflow: hidden;
  text-align: left;
  font-size: 30px;
  color: #fff;
  font-family: Inika;
}
</style>