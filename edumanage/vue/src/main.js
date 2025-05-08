import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import './components/global/global.css'
import router from '../router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import {post,fetch,patch,put} from "./http.js"
import {getCookie} from './components/global/cookie'


Vue.use(ElementUI)
// require('./mock.js')
 
axios.defaults.withCredentials = true
Vue.prototype.$axios=axios;
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;
axios.defaults.baseURL='http://localhost:8080'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if(getCookie("token")!==''){ //判断本地是否存在token
      next();
    }else {
     if(to.path === '/'){
        next()
      }else {
        next({
          path:'/'
        })
      }
    }
  }
  else {
    next()
  }
})

new Vue({
  
  router,
  render: h => h(App),
}).$mount('#app')
