import './assets/main.css'
import 'primevue/resources/themes/saga-blue/theme.css'; // or another theme
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';

import { createApp } from 'vue'
import PrimeVue from 'primevue/config'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(router)
app.use(PrimeVue)
app.mount('#app')
