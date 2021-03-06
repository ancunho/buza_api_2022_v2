import Vue from 'vue'
import App from './App.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import './assets/element-customer.css';
import router from './router'
import axios from 'axios'
import echarts from 'echarts'

Vue.use(ElementUI);
// axios.defaults.baseURL = '/api';
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts;

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
axios.defaults.withCredentials = true;
axios.interceptors.request.use(function(config) {
  if (Tool.getLoginUser() !== null || Tool.getLoginUser() !== '') {
    config.headers.Authorization = Tool.getLoginUser();
  } else {
    config.headers.Authorization = '';
  }
  return config;
});
Vue.prototype.$request = axios;

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

// new Vue({
//   el: '#app',
//   router,
//   render: h => h(App)
// });

// console.log("环境：", process.env.NODE_ENV);
