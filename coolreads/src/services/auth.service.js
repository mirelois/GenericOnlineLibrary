import axios from 'axios';
import { jwtDecode } from "jwt-decode";
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/customer/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'login', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data) {
          const decoded = jwtDecode(response.data.token);
          console.log(decoded);
          response.data['info'] = decoded;
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'register', {
      username: user.username,
      password: user.password,
      email: user.email
    });
  }

  changePassword(oldPassword, newPassword) {
    const user = JSON.parse(localStorage.getItem('user'));
    return axios
        .put(API_URL + 'me/password', {
          oldPassword: oldPassword,
          newPassword: newPassword
    }, {
      headers: {
        Authorization: 'Bearer ' + user.accessToken
      }
    }).then(response => {
      if (response.data.success) {
        return Promise.resolve(response.data);
      } else {
        return Promise.reject(new Error('Password change failed'));
      }
    }).catch(error => {
      return Promise.reject(error);
    });
  }
}

export default new AuthService();
