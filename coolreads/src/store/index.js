import { createStore } from 'vuex';
import auth from './auth.module';
import notifications from './notification.module';
import language from './language.module';

const store = createStore({
  modules: {
    auth,
    notifications,
    language,
  },
});

export default store;
