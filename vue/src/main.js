import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import './components/global/global.css'
import router from '../router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import {post,fetch,patch,put} from "./http.js";
Vue.prototype.$axios=axios;
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';

require('./mock.js')

Vue.use(ElementUI)
Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  
  router,
  render: h => h(App),
}).$mount('#app')
