<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
    <div class="large-12 medium-12 small-12 cell">
<!--      {{response}}{{receivedReport}}-->
      <h3>Generated report:</h3>
      <div v-html="this.response"></div>
<!--      <p>{{this.response}}</p>-->
    </div>
  </div>
</template>

<script lang="ts">
    import { Component, Vue } from 'vue-property-decorator';
    import axios from 'axios';

    export default class CodeReview extends Vue {
      public response:string = "" ;
      public r = "";
      public receivedReport:boolean=false;
      public file!: string | Blob;
        $refs!: {
            file: HTMLFormElement;
        };
        private async submitFile(){
            let formData = new FormData();
            formData.append('file', this.file);
            const resp = await axios.post( 'http://localhost:8080/code-review',
                formData,
                {
                    headers: {
                        Authorization: `Bearer ${Vue.prototype.$token}`,
                        'Content-Type': 'multipart/form-data',
                    },
                }
            );
            this.response=await resp.data;
                    // .then((respo)=>{
              // console.log("YYYYYYYYYY"+respo.data);
              // this.receivedReport=true;
              // this.response=respo.data;
              // this.r=respo.data;
              // Vue.set(this.response, 0,respo.data);
            // })
            //         .catch((err)=>{
            //           this.response=err;
            //         })
            // this.response=resp;
          console.log("LLLLLLLLLLL"+this.response );
          this.$forceUpdate();
            // this.response=await resp;

        };
        handleFileUpload(){
            // try{
            //   this.file = this.fileElement.files[0];
              this.file = this.$refs.file.files[0];
              console.log(this.$refs.file.files[0])
            // }
        };


    }
</script>

<style scoped>
  .grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap: 10px;
    grid-auto-rows: minmax(100px, auto);
  }
</style>
