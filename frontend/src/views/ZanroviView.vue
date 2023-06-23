<template>
    <div class="home">
      <button @click="novo(z?.id)" class="zanr-btn" v-for="z in zanrovi" :key="z?.id">{{ z?.naziv }}</button>
       
    </div>
  </template>

  <script>
  
  export default {
    name: 'Zanrovi',
    data: function() {
        return {
            zanrovi: [],
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
            
    },
    methods:{
        async novo(zId){
            console.log('zanrId ' + zId)
            
                this.$router.push({ name: 'ZanroviView', params: { id: zId }});
            
            
        }
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