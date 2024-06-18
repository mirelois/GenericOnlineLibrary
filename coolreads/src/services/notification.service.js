import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/customer/me/notifications';

class NotificationService {
  getNotifications(username, pageNumber, pageSize) {
    return axios.get(`${API_URL}?page=${pageNumber}&size=${pageSize}`, { headers: authHeader() })
      .then(response => response.data);
  }

  deleteNotification(notificationId) {
    return axios.delete(`${API_URL}/${notificationId}`, { headers: authHeader() });
  }
}

export default new NotificationService();
