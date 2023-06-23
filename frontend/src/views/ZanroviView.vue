<template>
    <div class="home">
      <button class="zanr-btn" v-for="z in zanrovi" :key="z.id">{{ z.naziv }}</button>
    </div>
  </template>

  <script>
  import KnjigeComponent from '@/components/KnjigeComponent.vue'
  
  export default {
    name: 'Zanrovi',
    components: {
      KnjigeComponent
    },
    data: function() {
        return {
            zanrovi: [],
            knjige: [],
        }
    },
    mounted: async function() {
        await fetch('http://localhost:8081/api/zanrovi')
            .then(response => {
                return response.json();
            }).then(data => {
                console.log('zanrovi ' + JSON.stringify(this.zanrovi));
                this.zanrovi = data;
                console.log('br zanr: ' + this.zanrovi.length);
                if(this.zanrovi.length > 0){
                    console.log('prvi ' + JSON.stringify(this.zanrovi[0]));
                    for (let i = 0; i < this.zanrovi.length; i++){
                        console.log('naslov ' + i.naziv);
                    }
                } 
            })
            .catch((error) => {
                console.error("Error:", error);
            });
            await fetch('http://localhost:8081/api/knjige-zanr' + this.$route.params.id)
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
  <style>
    button.zanr-btn {
        color: white;
        margin: 0 5px;
        font-size: 16px;
        border: none;
        border-radius: 16px;
        padding: 5px 15px;
        background-color: #42b983;
    }
</style>