<template>
  <div class="container">
      <h1>Code Analysis for Java code</h1>
      <div class="large-12 medium-12 small-12 cell">
          <form>
              <label><h2>Code snippet</h2></label>
              <textarea placeholder="Paste your code here"/>
              <br/>
              <button type="submit">Submit</button>
          </form>
      </div>
    <div class="large-12 medium-12 small-12 cell">
        <label><h2>File Upload</h2>
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
        <br/>
        <br/>
      <button v-on:click="submitFile()">Submit</button>
    </div>
    <div class="large-12 medium-12 small-12 cell">
      <h3 v-if="this.responseBack">Generated report</h3>
      <div v-html="this.response"></div>
    </div>
  </div>
</template>

<script lang="ts">
    import { Component, Vue } from 'vue-property-decorator';
    import axios from 'axios';

    export default class CodeReview extends Vue {
      public response: string = '' ;
      public responseBack: boolean = false ;
      public file!: string | Blob;
      $refs!: {
        file: HTMLFormElement;
      };

      private async submitFile() {
        const formData = new FormData();
        formData.append('file', this.file);
        const resp = await axios.post( 'http://localhost:8080/code-review',
          formData,
            {
                headers: {
                    'Authorization': `Bearer ${Vue.prototype.$token}`,
                    'Content-Type': 'multipart/form-data',
                },
            },
        );
        this.response = await resp.data;
        this.responseBack = true;
        this.$forceUpdate();
      }

      private handleFileUpload() {
        this.file = this.$refs.file.files[0];
      }

        private async submitSnippet() {
            const formData = new FormData();
            formData.append('file', this.file);
            const resp = await axios.post( 'http://localhost:8080/code-review',
                formData,
                {
                    headers: {
                        'Authorization': `Bearer ${Vue.prototype.$token}`,
                        'Content-Type': 'multipart/form-data',
                    },
                },
            );
            this.response = await resp.data;
            this.responseBack = true;
            this.$forceUpdate();
        }
    }
</script>

<style scoped>
  /*.grid {*/
  /*  display: grid;*/
  /*  grid-template-columns: repeat(3, 1fr);*/
  /*  grid-gap: 10px;*/
  /*  grid-auto-rows: minmax(100px, auto);*/
  /*}*/
    .container{
        padding-top: 50px;
    }
</style>
