<template>
  <div class="home">
    <img class="form-logo" alt="Vue logo" src="../assets/book-flat.png">
    <br>
    <p>Pretraga po nazivu:</p>
    <input v-model="keyWords" />
    <br><br>
    <KnjigeComponent :knjige="knjige"></KnjigeComponent>
  </div>
</template>

<script>
import KnjigeComponent from '@/components/KnjigeComponent.vue'

export default {
  name: 'Knjige',
  components: {
    KnjigeComponent
  },
  data: function() {
      return {
          knjige: [],
      }
  },
  mounted: async function() {
      await fetch('http://localhost:8081/api/knjige')
          .then(response => {
              return response.json();
          }).then(data => {
              console.log('knjige ' + JSON.stringify(this.knjige));
              this.knjige = data;
                console.log('br knjiga: ' + this.knjige.length);
                if(this.knjige.length > 0){
                    console.log('prva ' + JSON.stringify(this.knjige[0]));
                    for (let i = 0; i < this.knjige.length; i++){
                        console.log('naslov ' + i.naslov);
                    }
                } 
            })
            .catch((error) => {
                console.error("Error:", error);
            });
    },
  }
  </script>