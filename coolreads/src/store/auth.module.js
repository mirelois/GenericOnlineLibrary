import AuthService from '../services/auth.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
  ? { status: { loggedIn: true }, user: user }
  : { status: { loggedIn: false }, user: null };

const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, user) {
      return AuthService.login(user).then(
        user => {
          console.log("Login action - user received from AuthService:", user);
          commit('loginSuccess', user);
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    },
    register({ commit }, user) {
      return AuthService.register(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    },
    changePassword({ commit }, { oldPassword, newPassword }) {
      return AuthService.changePassword(oldPassword, newPassword).then(
        response => {
          commit('changePasswordSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('changePasswordFailure');
          return Promise.reject(error);
        }
      );
    }
  },
  mutations: {
    loginSuccess(state, user) {
      state.status.loggedIn = true;
      console.log("loginSuccess mutation called with user:", user);
      state.user = user;
    },
    loginFailure(state) {
      state.status.loggedIn = false;
      state.user = null;
    },
    logout(state) {
      state.status.loggedIn = false;
      state.user = null;
    },
    registerSuccess(state) {
      state.status.loggedIn = false;
    },
    registerFailure(state) {
      state.status.loggedIn = false;
    },
    changePasswordSuccess(state) {
      state.passwordChangeMessage = "Success";
      console.log("Success: Password changed");
    },
    changePasswordFailure(state, error) {
      state.passwordChangeMessage = "Pass not correct";
      console.error("Error: Password change failed", error);
    },
  },
};

export default auth;
