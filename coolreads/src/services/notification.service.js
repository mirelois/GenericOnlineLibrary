import axios from 'axios';

const API_URL = 'http://localhost:8080/notifications/';

class NotificationService {
  getNotifications(username, pageNumber, pageSize) {
    return axios.get(`${API_URL}?username=${username}&page=${pageNumber}&size=${pageSize}`).then(response => response.data);
  }

  deleteNotification(notificationId) {
    return axios.delete(`${API_URL}${notificationId}`);
  }
}

export default new NotificationService();
