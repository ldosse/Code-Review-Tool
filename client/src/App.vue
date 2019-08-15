<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>&nbsp
      <template v-if="authenticated">|
        <router-link to="/code-review">Code Review</router-link>&nbsp
      </template>
      <button v-if="authenticated" v-on:click="logout">Logout</button>
      <button v-else v-on:click="$auth.loginRedirect()">Login</button>
    </div>

    <router-view/>
  </div>
</template>

<script lang="ts">
    import { Component, Vue, Watch } from 'vue-property-decorator';

    @Component
    export default class App extends Vue {
        public authenticated: boolean = false;
        // public token: string='';
        private created() {
            this.isAuthenticated();
        }

        @Watch('$route')
        private async isAuthenticated() {
            this.authenticated = await this.$auth.isAuthenticated();
            Vue.prototype.$token = await this.$auth.getAccessToken();
            // this.token = await this.$auth.getAccessToken();
        }

        private async logout() {
            await this.$auth.logout();
            await this.isAuthenticated();
            Vue.prototype.$token = '';
            // Navigate back to home
            this.$router.push({path: '/'});
        }
    }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  #nav {
    padding: 30px;
    color: whitesmoke;
    background-color: #097496;
    /*lightseagreen;*/
  }

  #nav a {
    font-weight: bold;
    color: whitesmoke;
    /*color: #2c3e50;*/
  }

  #nav a.router-link-exact-active {
    color: paleturquoise;
    /*color: #42b983;*/
  }
  button{
    text-align: right;
  }
</style>
