import NotificationService from '../services/notification.service';

const state = {
  notifications: [],
  currentPage: 0,
  maxPerPage: 10,
};

const actions = {
  async fetchNotifications({ commit, state }, { username, pageNumber, pageSize }) {
    try {
      const notifications = await NotificationService.getNotifications(username, pageNumber, pageSize);
      console.log("the notifications:")
      console.log(notifications)
      commit('addNotifications', notifications);
    } catch (error) {
      console.error('Error fetching notifications:', error);
    }
  },
  async deleteNotification({ commit }, notificationId) {
    try {
      await NotificationService.deleteNotification(notificationId);
      commit('removeNotification', notificationId);
    } catch (error) {
      console.error('Error deleting notification:', error);
    }
  }
};

const mutations = {
  addNotifications(state, notifications) {
    if (state.currentPage === 0) {
      state.notifications = notifications;
    } else {
      state.notifications.push(...notifications);
    }
  },
  removeNotification(state, notificationId) {
    state.notifications = state.notifications.filter(
      notification => notification.id !== notificationId
    );
  },
  incrementPage(state) {
    state.currentPage += 1;
  },
  decrementPage(state) {
    state.currentPage = Math.max(state.currentPage - 1, 0);
  },
  resetPage(state) {
    state.currentPage = 0;
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
};