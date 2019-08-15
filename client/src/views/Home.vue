<template>
  <div class="home">
    <h1>Code Review App</h1>
      <p>Welcome to the Code reviewer app. You can submit your java code and receive a generated report
          on identified vulnerabilities in your code.</p>
      <img alt="Code Reviewer logo" src="../assets/logo.png">

  </div>
</template>



<!--<template>-->
<!--  <div class="home">-->
<!--    <img alt="Vue logo" src="../assets/logo.png">-->
<!--    <BeerList/>-->
<!--  </div>-->
<!--</template>-->

<script lang="ts">
    import { Component, Vue } from 'vue-property-decorator';
    import axios from 'axios';

    export interface Beer {
        id: number;
        name: string;
        giphyUrl: string;
    }

    @Component
    export default class Home extends Vue {
        public beers: Beer[] = [];
        private async created() {
            const response = await axios.get('/good-beers');
            this.beers = await response.data;
        }
    }
</script>
