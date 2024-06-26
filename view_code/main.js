import { createApp } from 'vue'
import router from './routers/router'
import App from './App.vue'
import pinia from './pinia.js'

const app=createApp(App)
app.use(router)
app.use(pinia)
app.mount('#app')
