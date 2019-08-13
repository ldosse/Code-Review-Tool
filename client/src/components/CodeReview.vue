<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script lang="ts">
    import { Component, Vue } from 'vue-property-decorator';
    import axios from 'axios';


    export default class BeerList extends Vue {
        public file!: string | Blob;
        // public fileElement = this.$refs.file as HTMLInputElement;
        // public fileElement = this.$refs.file as HTMLInputElement;
        $refs!: {
            file: HTMLFormElement;
        };
        public submitFile(){
            let formData = new FormData();
            formData.append('file', this.file);
            axios.post( 'http://localhost:8080/code-review',
                formData,
                {
                    headers: {
                        Authorization: `Bearer ${Vue.prototype.$token}`,
                        'Content-Type': 'multipart/form-data',
                    },
                }
            ).then(function(){
                console.log('SUCCESS!!');
            })
                .catch((error)=>{
                    console.log('FAILURE!!');
                    console.log(error);
                    // console.log(Vue.prototype.$token);
                });
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
