<template>
    <tr>
      <td>{{ korisnik.ime }}</td>
      <td>{{ korisnik.prezime }}</td>
      <td>{{ korisnik.mejl }}</td>
      <td>{{ korisnik.korisnickoIme }}</td>
      <td>{{ korisnik.lozinka }}</td>
      <td>{{ korisnik.potvrdaLozinke }}</td>
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
    name: "Korisnik",
    props: ["korisnik"],
    methods: {
      seeMore: function () {
        this.$router.push("/korisnik?id=" + this.korisnik.id);
      },
      deleteEmployee: function() {
        fetch("http://localhost:8082/api/employees/" + this.korisnik.id, {
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
  