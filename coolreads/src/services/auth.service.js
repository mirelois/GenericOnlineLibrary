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
    return axios.put(API_URL + 'me/password', null, {
      params: {
        oldPassword: oldPassword,
        newPassword: newPassword
      },
      headers: authHeader()
    });
  }
}

export default new AuthService();
