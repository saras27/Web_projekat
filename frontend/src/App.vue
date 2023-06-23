<template>
   <nav>
    <router-link to="/">Početna</router-link> |
    <router-link to="/knjige">Knjige</router-link> | 
    <router-link to="/zanrovi">Žanrovi</router-link>
    <div  v-if="korisnik" class="logout">
      <button @click="izlogujSe">Izloguj se</button>
    </div>
  </nav>
  <router-view/>
</template>

<script>
  export default {
    name: 'Navbar',
    data: function(){
      return {
        korisnik: JSON.parse(window.sessionStorage.getItem('korisnik')),
      }
    },
    mounted: function() {
      this.korisnik = JSON.parse(window.sessionStorage.getItem('korisnik'));
    },
    methods: {
      izlogujSe: function() {
        window.sessionStorage.removeItem('korisnik');
        window.location.reload();
      }
    }
  }
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
nav {
  padding: 30px;
}
nav a {
  font-weight: bold;
  color: #2c3e50;
}
nav a.router-link-exact-active {
  color: #42b983;
}
table {
  margin-left: auto;
  margin-right: auto;
}
table td {
    padding: 10px;
    border-bottom: 1px solid;
}
table th {
    padding: 10px;
    color: #42b983;
    border-bottom: 1px solid;
}
table a {
  color: #2c3e50;
}
button {
  color: white;
  font-size: 15px;
  border: none;
  border-radius: 4px;
  padding: 5px;
  background-color: #42b983;
}
div.logout {
 position: fixed;
 top: 35px;
 right: 35px;
}
</style>
