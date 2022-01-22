import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

// axios.defaults.baseURL = '/api';
Vue.config.productionTip = false

Vue.prototype.$request = axios;

axios.interceptors.request.use(function(config) {
  if (Tool.getLoginUser() !== null || Tool.getLoginUser() !== '') {
    config.headers.Authorization = Tool.getLoginUser();
  } else {
    config.headers.Authorization = '';
  }
  return config;
})

// router.beforeEach((to, from, next) => {
//   console.log(to);
//   if (to.matched.some(function(item) {
//     console.log(item);
//     return item.meta.requireAuth
//   })) {
//     console.log(123123123123123123);
//     let loginUser = Tool.getLoginUser();
//     if (Tool.isEmpty(loginUser)) {
//       next('/login');
//     } else {
//       next();
//     }
//   } else {
//     console.log(1123123123213);
//     next();
//   }
// })



new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

// console.log("环境：", process.env.NODE_ENV);
