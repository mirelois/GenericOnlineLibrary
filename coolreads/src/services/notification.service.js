import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/customer/me/notifications';

class NotificationService {
  getNotifications(username, pageNumber, pageSize) {
    return axios.get(`${API_URL}?username=${username}&page=${pageNumber}&size=${pageSize}`, {
      headers: authHeader(),
    })
    .then(response => {
      console.log('Response data:', response.data);
      return response.data;
       
    })
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