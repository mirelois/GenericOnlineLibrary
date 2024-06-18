import './assets/main.css'
import 'primevue/resources/themes/saga-blue/theme.css'; // or another theme
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';

import { createApp } from 'vue';
import PrimeVue from 'primevue/config';
import store from './store';
import App from './App.vue';
import router from './router';
import ToastPlugin from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-bootstrap.css';

const app = createApp(App);

app.use(ToastPlugin);
app.use(router);
app.use(PrimeVue);
app.use(store);
app.mount('#app');