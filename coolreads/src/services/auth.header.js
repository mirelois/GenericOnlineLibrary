export default function authHeader() {
    let user = localStorage.getItem('user');
    
    if (user!=null && JSON.parse(user).token) {
      return { Authorization: 'Bearer ' + JSON.parse(user).token };

    } else {
      return {};
    }
  }