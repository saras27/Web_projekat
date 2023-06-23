<template>
    <br>
    <p>Korisničko ime:</p>
    <input v-model="input.username" />
    <p>Lozinka:</p>
    <input v-model="input.password" type="password" />
    <br><br>
    <button @click="ulogujSe">Uloguj se</button>
    <br><br>
    <span>Nemas nalog? </span><router-link to="/registracija" class="redirect-link">Registruj se</router-link>
  </template>
  <script>
  import { loginState } from '@/router/listener'
    export default {
      name: 'LoginForm',
      data: function(){
          return{
          input:{
              username: "",
              password: ""
          }
      }
      },
      methods: {
        ulogujSe: function(){
          console.log('UN' + this.input.username);
          const bd = JSON.stringify({ korisnickoIme: this.input.username, lozinka: this.input.password });
          console.log('body' + bd);
          const requestOptions = {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({ korisnickoIme: this.input.username, lozinka: this.input.password })
          };
          fetch('http://localhost:8081/api/login', requestOptions)
              .then(response => {
                  window.sessionStorage.setItem('korisnik', response.body);
                  return response.json();
              }).then(data => {
                  window.sessionStorage.setItem('korisnik', JSON.stringify(data));
                  this.$router.push({ name: 'home' });
                  loginState.ulogovan = true;
                  window.location.reload();
              })
              .catch((error) => {
                  console.error("Error:", error);
              });
        }
      }
    }
</script>
<style scoped>
p {
    margin-bottom: 5px;
};
</style>