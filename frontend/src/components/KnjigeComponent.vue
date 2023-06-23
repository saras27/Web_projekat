<template>
    <div v-if="knjige && knjige?.length > 0">
        <table class="items-table">
            <tr>
                <th>Naslov</th>
                <th>Autor</th>
                <th>Žanr</th>
                <th>Broj strana</th>
                <th>Ocena</th>
                <th v-if="korisnik || korisnik !== null"></th>
            </tr>
            <tr v-for="k in knjige" :key="k.id">
                <td><router-link :to="{ name: 'knjiga', params: { id: k.id }}">{{ k.naslov }}</router-link></td>
                <td>{{ k.autor?.ime }} {{ k.autor?.prezime }}</td>
                <td>{{ k.zanr?.naziv }}</td>
                <td>{{ k.brojStrana }}</td>
                <td>{{ k.ocena }}</td>
                <td v-if="korisnik || korisnik !== null">
                    <button @click="brisanje(policaId, k?.id)">Ukloni</button>
                </td>
            </tr>
        </table>
    </div>
    <div v-else>
        <p>Nema knjiga za prikaz</p>
    </div>
    <div class="btn-row" v-if="korisnik && sveKnjige && sveKnjige.length">
        <span colspan="5"><button class="dodaj-knjigu">+ Dodaj knjigu</button></span>
    </div>
  </template>
  
  <script>
    export default {
    name: 'KnjigeComponent',
    props: ["knjige", "sveKnjige", "korisnik", "policaId"],
    methods: {
        async brisanje(pId, kId){
            if(pId && kId) {
                await fetch(`http://localhost:8081/api/polica/${pId}/${kId}`)
            .then(response => {
                window.location.reload();
            });
            }
            
        }
    }
}
  </script>
  
  <style scoped>
  .btn-row {
    margin-top: 10px;
    margin-bottom: 60px;
  }
  </style>