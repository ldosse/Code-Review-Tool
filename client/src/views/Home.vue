<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <h1>Beer List</h1>
    <div v-for="beer in beers">
      {{ beer.name }}
    </div>
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
