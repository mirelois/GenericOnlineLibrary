<script setup>
import { useStore } from 'vuex';
import { computed, ref, onMounted } from 'vue';
import NavComponent from '@/components/NavComponent.vue';
import { formatDistanceToNow, parseISO } from 'date-fns';
const store = useStore();

const notifications = computed(() => store.state.notifications.notifications);

const translations = computed(() => store.getters['language/currentTranslations']);
const username = ref('');

const maxPerPage = computed(() => store.state.notifications.maxPerPage);
const currentPage = computed(() => store.state.notifications.currentPage);
const fetchNotifications = () => {
  console.log('Fetching notifications for', username.value);
  store.dispatch('notifications/fetchNotifications', {
    username: username.value,
    pageNumber: currentPage.value,
    pageSize: maxPerPage.value,
  }).then(notifications=>{
    console.log("hmmmm")
    console.log(notifications)
  });
};
const nextPage = () => {
  console.log("next pageee")
  store.commit('notifications/incrementPage');
  fetchNotifications();
};

const backPage = () => {
  store.commit('notifications/decrementPage');
  fetchNotifications();
};

const goToPage = (pageIndex) => {
  store.commit('notifications/resetPage');
  store.commit('notifications/incrementPage', pageIndex);
  fetchNotifications();
};

const nrPages = computed(() => Math.ceil(notifications.value.length / maxPerPage.value));

const paginatedNotifications = computed(() => {
  const start = currentPage.value * maxPerPage.value;
  const end = start + maxPerPage.value;
  console.log("eiiii")
  console.log(notifications.value)
  return notifications.value.slice(start, end);
});

const timeMachine = (dateString) => {
  const date = parseISO(dateString);
  return formatDistanceToNow(date, { addSuffix: true });
};

const formatNotificationType = (type) => {
  switch (type) {
    case 'POST_NOTIFICATION':
      return 'New Post';
    case 'FRIEND_REQUEST_NOTIFICATION':
      return 'Received Friend Request';
    case 'FRIEND_REQUEST_ACCEPTED_NOTIFICATION':
      return 'Friend Request Accepted';
    case 'FRIEND_REVIEWED_BOOK_NOTIFICATION':
      return 'New Book Review';
    case 'FRIEND_STARRED_BOOK_NOTIFICATION':
      return 'New Book Star';
    default:
      return 'Notification';
  }
};
onMounted(() => {
  const token = localStorage.getItem('user');
  if (token) {
    try {
      const decodedToken = JSON.parse(token);
      username.value = decodedToken.info.sub;
      fetchNotifications();
    } catch (error) {
      console.error('Error parsing user token:', error);
    }
  } else {
    console.warn('User token not found in localStorage');
  }
});
</script>

<template>
  <div>
    <NavComponent></NavComponent>
    <div class="notificationspage-child"></div>
    <b class="all-notifications">{{ translations.allnotifications }}</b>
    <div class="notification2all">
      <div v-for="notification in paginatedNotifications" :key="notification.id" class="notification">
        <img class="clock-icon" alt="" src="/img/clock.svg">
        <b class="time">{{ timeMachine(notification.createdAt) }}</b>
        <b class="message">{{ formatNotificationType(notification.notificationType) }} from {{ notification.username }}</b>
      </div>
    </div>
    <div class="pagination">
      <div class="notif-pagination-child">
        <div class="parent">
          <img class="notif-vector-icon" @click="backPage" alt="" src="/img/back.svg">
          <div class="notif-div3" :class="{ 'child': true }" >{{ nrPages }}</div>
          <img class="notif-vector-icon1" @click="nextPage" alt="" src="/img/front.svg">
        </div>
      </div>
    </div>
    <NavComponent></NavComponent>
  </div>
</template>

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
.deletebutton {
  position: absolute;
  left: -150px;
  margin-left: 40px;
  width: 70px;
  height: 40px;
  overflow: hidden;
}
.clock-icon {
  position: absolute;
  left: -65px;
  margin-left: 40px;
  width: 40px;
  height: 40px;
  overflow: hidden;
}
.time {
  position: absolute;
  margin-left: 40px;
  left: -15px;
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
.pagination {
  position: absolute;
  top: 1467px;
  left: 464px;
  width: 642px;
  height: 68.4px;
  text-align: center;
  font-size: 18px;
  font-family: Lato;
}
.notif-pagination-child {
  position: absolute;
  top: 0px;
  left: 0px;
  box-shadow: 0px 4.972476959228516px 4.97px rgba(0, 0, 0, 0.2);
  border-radius: 32.32px;
  background-color: #f7f9ff;
  width: 762px;
  height: 68.4px;
}
.notif-vector-icon {
  width: 26px;
  position: absolute;
  height: 26px;
  left:-45px;
}
.notif-vector-icon1 {
  width: 26px;
  position: absolute;
  height: 26px;
  left:550px;
}
.notif-vector-icon:hover {
  cursor:pointer;
}
.notif-vector-icon1:hover {
  cursor:pointer;
}
.child {
  position: absolute;
  top: 0px;
  left: 0px;
  border-radius: 50%;
  background-color: #8798d4;
  width: 42px;
  height: 42px;
}
.div1 {
  position: absolute;
  top: 10.5px;
  left: 10.5px;
  font-weight: 600;
  display: flex;
  align-items: flex-end;
  justify-content: center;
  width: 21px;
  height: 19.5px;
}
.notif-div3 {
  width: 46px;
  position: relative;
  font-weight: 600;
  display: flex;
  align-items: flex-end;
  justify-content: center;
  left: 5px;
  top: -10px;
  height: 46px;
  flex-shrink: 0;
}
.parent {
  position: absolute;
  top: 25.5px;
  left: 78px;
  width: 363px;
  height: 19.5px;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 36px;
  color: #656565;
}
.div {
  position: absolute;
  top: 13px;
  left: 80px;
  width: 441px;
  height: 42px;
}
</style>