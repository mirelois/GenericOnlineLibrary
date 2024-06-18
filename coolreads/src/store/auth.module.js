import AuthService from '../services/auth.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
  ? { status: { loggedIn: true }, lastRoute: '/', user: user, storedReview: '' }
  : { status: { loggedIn: false }, lastRoute: '/', user: null, storedReview: '' };

const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    storeReview({commit}, textReview) {
      commit('storeReview', textReview);
    },
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
    logout({ commit }, lastRoute) {
      AuthService.logout();
      commit('logout', lastRoute);
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
    logout(state, lastRoute) {
      state.status.loggedIn = false;
      state.user = null;
      console.log("Storing Route: " + lastRoute);
      if (lastRoute) {
        state.lastRoute = lastRoute;
      } else {
        state.lastRoute = '/'
      }
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
    storeReview(state, textReview) {
      state.storedReview = textReview;
      console.log("Stored review " + state.storedReview);
    },
  },
};

export default auth;
