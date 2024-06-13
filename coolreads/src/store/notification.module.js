import NotificationService from '../services/notification.service';

const state = {
  notifications: [],
};

const actions = {
  fetchNotifications({ commit }, { username, pageNumber, pageSize }) {
    return NotificationService.getNotifications(username, pageNumber, pageSize).then(
      notifications => {
        commit('setNotifications', notifications);
        return Promise.resolve(notifications);
      },
      error => {
        return Promise.reject(error);
      }
    );
  },
  deleteNotification({ commit }, notificationId) {
    return NotificationService.deleteNotification(notificationId).then(
      () => {
        commit('removeNotification', notificationId);
        return Promise.resolve();
      },
      error => {
        return Promise.reject(error);
      }
    );
  }
};

const mutations = {
  setNotifications(state, notifications) {
    state.notifications = notifications;
  },
  removeNotification(state, notificationId) {
    state.notifications = state.notifications.filter(
      notification => notification.id !== notificationId
    );
  }
};

export default {
  namespaced: true,
  state,
  actions,
  mutations
};
