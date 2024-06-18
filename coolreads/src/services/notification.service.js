import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/customer/me/notifications';

class NotificationService {
  getNotifications(username, page, size) {
    return axios.get(`${API_URL}`, {
      headers: authHeader(),
      params: { page, size }
    })
    .then(response => response.data)
    .catch(error => {
      console.error('Error fetching notifications:', error);
      throw error;
    });
  }

  deleteNotification(notificationId) {
    return axios.delete(`${API_URL}/${notificationId}`, {
      headers: authHeader()
    })
    .catch(error => {
      console.error('Error deleting notification:', error);
      throw error;
    });
  }
}

export default new NotificationService();