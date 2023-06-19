<template>
    <tr>
      <td>{{ knjiga.naslov }}</td>
      <td>{{ korisnik.naslovnaFotografija }}</td>
      <td>{{ korisnik.ISBN }}</td>
      <td>{{ korisnik.datumObjavljivanja }}</td>
      <td>{{ korisnik.brStr }}</td>
      <td>{{ korisnik.opis }}</td>
      <td>{{ korisnik.zanr }}</td>
      <td>{{ korisnik.autor }}</td>
      <td>{{ korisnik.ocena }}</td>
      <td>
        <button class="btnSeeMore" v-on:click="seeMore">See more</button>
      </td>
      <td>
        <button class="delete" v-on:click="deleteEmployee">
          Delete
        </button>
      </td>
    </tr>
  </template>
  
  <script>
  export default {
    name: "Knjiga",
    props: ["knjiga"],
    methods: {
      seeMore: function () {
        this.$router.push("/knjiga?id=" + this.knjiga.id);
      },
      deleteEmployee: function() {
        fetch("http://localhost:8082/api/knjige/" + this.knjiga.id, {
          method: "DELETE",
        }).then((res) => {
          if (res.ok) {
            window.location.reload();
          }
        });
      },
    },
  };
  </script>
  
  
  <style>
  </style>
  