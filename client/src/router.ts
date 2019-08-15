import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import OktaVuePlugin from '@okta/okta-vue';
import CodeReview from '@/components/CodeReview.vue';

Vue.use(Router);
Vue.use(OktaVuePlugin, {
  issuer: 'https://dev-367104.okta.com/oauth2/default',
  client_id: '0oa13btk7dw1zh2KV357',
  redirect_uri: window.location.origin + '/implicit/callback',
  scope: 'openid profile email',
});

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/code-review',
      name: 'code-review',
      component: CodeReview,
      meta: {
        requiresAuth: true,
      },
    },
    { path: '/implicit/callback', component: OktaVuePlugin.handleCallback() },
  ],
});

router.beforeEach(Vue.prototype.$auth.authRedirectGuard());

export default router;

