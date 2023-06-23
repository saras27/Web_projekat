<template>

    <h1>Knjige zanra {{ zanr }}</h1>
    <KnjigeComponent :knjige="knjige"></KnjigeComponent>
</template>

<script>

import KnjigeComponent from '@/components/KnjigeComponent.vue'

export default {
    name: "ZanrComp",
    components: {
        KnjigeComponent
  },
    data: function(){
          return{
            knjige:[],
            zanr: String
      }
      },

    mounted: async function(){
        await fetch('http://localhost:8081/api/knjige-zanr/' + this.$route.params.id)
            .then(response => {
                return response.json();
            }).then(data => {
                console.log('knjige ' + JSON.stringify(this.knjige));
                this.knjige = data;
                this.zanr = this.knjige[0]?.zanr?.naziv;
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
    }
};
</script>
