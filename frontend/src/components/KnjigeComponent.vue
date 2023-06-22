<template>
    <br>
    <p>Pretraga po nazivu:</p>
    <input v-model="keyWords" />
    <br><br>
    <table class="items-table">
        <tr>
            <th>Naslov</th>
            <th>Autor</th>
            <th>Žanr</th>
            <th>Broj strana</th>
            <th>Ocena</th>
        </tr>
        <tr v-for="k in knjige">
            <td><router-link :to="{ name: 'knjiga', params: { id: k.id }}">{{ k.naslov }}</router-link></td>
            <td>{{ k.autor?.ime }} {{ k.autor?.prezime }}</td>
            <td>{{ k.zanr?.naziv }}</td>
            <td>{{ k.brojStrana }}</td>
            <td>{{ k.ocena }}</td>
        </tr>
    </table>
  </template>
  
  <script>
    export default {
    name: 'KnjigeComponent',
    props: ["knjige"],
}
  </script>
  
  <style scoped>
  p {
      margin-bottom: 5px;
  };
  </style>