<template>
  <div class="home">
    <img class="form-logo" alt="Vue logo" src="../assets/book-flat.png">
    <div v-if="!korisnik || korisnik === null">
      <h2>Uloguj se</h2>
      <LoginForm></LoginForm>
    </div>
    <div v-else>
      <h1>Police korisnika {{ korisnik.korisnickoIme }} </h1><br>
    
       <div v-if="prikaziF">  
        <form>
          <input type="text">
          <button @click="dodaj">Dodaj</button> 
        </form>
      </div> 
      <div v-else>  
        <button @click="prikazi(prikaziFormu)">Dodaj novu policu</button>
       </div> 
      <div v-for="p in police" :key="p.id">
        <h3>{{ p.naziv }}</h3>
        <div v-if="knjige?.length > 0">
          <KnjigeComponent :knjige="p.stavke?.map(s => s.knjiga)" :sveKnjige="sveKnjige" :korisnik="korisnik"></KnjigeComponent>
      
        </div>
        <div v-else>Na polici nema knjiga.</div>
      </div>
    </div>
  </div>
</template>
<script>
import LoginForm from '@/components/LoginForm.vue'
import KnjigeComponent from '@/components/KnjigeComponent.vue'
export default {
  name: 'Home',
  data: function() {
        return {
          korisnik: {},
          police: [],
          knjige: [],
          sveKnjige: [],
          prikaziFormu:{
            prikaziF: false
          }
        }
    },
  components: {
    LoginForm,
    KnjigeComponent
  },
  mounted: async function() {
    this.korisnik = JSON.parse(window.sessionStorage.getItem('korisnik'));
    if (this.korisnik && this.korisnik !== null) {
      // get police korisnika
      await fetch('http://localhost:8081/api/police')
            .then(response => {
                return response.json();
              }).then(data => {
                console.log('police ' + JSON.stringify(this.police));
                this.police = data;
                console.log('br knjiga: ' + this.police.length);
                if(this.police.length > 0){
                  console.log('no 1 ' + JSON.stringify(this.police[0]));
                    for (let i = 0; i < this.police.length; i++){
                      console.log('naslov ' + i.naziv);
                    }
                } 
            })
            .catch((error) => {
                console.error("Error:", error);
            });

      // get sve knjige za dodavanje na policu
      await fetch('http://localhost:8081/api/knjige')
      .then(response => {
          return response.json();
      }).then(data => {
          console.log('sveKnjige ' + JSON.stringify(this.sveKnjige));
          this.sveKnjige = data;
          console.log('br sveKnjige: ' + this.sveKnjige.length);
          if(this.sveKnjige.length > 0){
              console.log('prva ' + JSON.stringify(this.sveKnjige[0]));
              for (let i = 0; i < this.sveKnjige.length; i++){
                  console.log('naslov ' + i.naslov);
              }
          } 
      })
      .catch((error) => {
          console.error("Error:", error);
      });
    }
  },
  methods: {
   prikazi(prikaziFormu){
      prikaziFormu = true;
    },
    // dodaj(policaId, knjigaId) {

    //   //
    //   this.privaziFormu = false;
    //   window.location.reload();
    // }
  }
}
</script>