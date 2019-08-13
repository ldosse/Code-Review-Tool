import Vue from 'vue';
import App from './App.vue';
import router from './router';
import './registerServiceWorker';

Vue.config.productionTip = false;

// const token = localStorage.getItem('token')
Vue.prototype.$token = '';

// if (token) {
//   Vue.prototype.$http.defaults.headers.common['Authorization'] = token
// }

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');
