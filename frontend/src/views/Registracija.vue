<template>
    <label for="ime">Ime:</label>
    <input v-model="korisnik.ime" /><br />
    <label for="prezime">Prezime:</label>
    <input v-model="korisnik.prezime" /><br />
    <label for="mejl">Mejl:</label>
    <input v-model="korisnik.mejl" /><br />
    <label for="korisnickoIme">Korisnicko ime:</label>
    <input v-model="korisnik.korisnickoIme" /><br />
    <label for="lozinka">Lozinka:</label>
    <input v-model="korisnik.lozinka" /><br />
    <label for="potvrdaLozinke">Potvrdite lozinku:</label>
    <input v-model="korisnik.lozinka" /><br />
    <button v-on:click="submit()">submit</button>
  </template>
  
  <script>
  import Korisnik from "c:/Users/HP/Desktop/demo/Web_projekat/frontend/src/components/Korisnik.vue"
  export default {
    name: "Registracija",
    components: {Korisnik},
    data: function () {
      return {
        korisnik: {
          ime: "",
          prezime: "",
          mejl: "",
          korisnickoIme: "",
          lozinka: "",
          potvrdaLozinke: "",
        },
      };
    },
    methods: {
      submit: function () {
  
        axios
          .post("http://localhost:8082/api/registracija", this.korisnik)
          .then((res) => {
            console.log(res);
            this.$router.push("/knjige");
          })
          .catch((err) => {
            console.log(err);
            alert("Something went wrong!");
          });
  
        /*fetch("http://localhost:8081/api/employees", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-type": "application/json",
          },
          body: JSON.stringify(this.employee),
        })
          .then((response) => response.json)
          .then((data) => {
            console.log("Success : " + data);
            this.$router.push("/employees");
          })
          .catch((err) => {
            console.log("Error : " + err);
            alert(err);
          });*/
      },
    },
  };
  </script>
  