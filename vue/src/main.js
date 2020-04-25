import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import './components/global/global.css'
import router from '../router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

require('./mock.js')

Vue.use(ElementUI)
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  
  router,
  render: h => h(App),
}).$mount('#app')
