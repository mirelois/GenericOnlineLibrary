import { createStore } from 'vuex';
import auth from './auth.module';
import notifications from './notification.module';

const store = createStore({
  modules: {
    auth,
    notifications
  }
});

export default store;
