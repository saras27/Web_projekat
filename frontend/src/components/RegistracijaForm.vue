<template>
    <br>
    <p>Ime:</p>
    <input v-model="input.ime" />
    <p>Prezime:</p>
    <input v-model="input.prezime" />
    <p>Korisničko ime:</p>
    <input v-model="input.username" />
    <p>Email:</p>
    <input v-model="input.email" />
    <p>Lozinka:</p>
    <input v-model="input.password" type="password" />
    <p>Lozinka (ponovo unesite):</p>
    <input v-model="input.passwordProvera" type="password" />
    <br><br>
    <button @click="registrujSe">Registruj se</button>
    <br><br>
  </template>

  <script>
    
  export default{
    name: 'RegistracijaForm',
        data: function(){
            return{
                input:{
                    ime: "",
                    prezime:"",
                    username:"",
                    email:"",
                    password:"",
                    passwordProvera:""
                }
            }
        },
    methods: {
        registrujSe: function(){
            console.log(this.input.ime);
            console.log(this.input.prezime);
            console.log(this.input.username);
            console.log(this.input.email);
            console.log(this.input.password);
            console.log(this.input.passwordProvera);
            const bd = JSON.stringify({ime: this.input.ime, prezime: this.input.prezime, korisnickoIme: this.input.username, mejl: this.input.email, lozinka: this.input.password, potvrda_lozinke: this.input.passwordProvera});
            console.log('body' + bd);
            const requestOptions = {
                method: 'POST',
                headers : {'Content-Type': 'application/json' },
                body: bd
            }
            fetch('http://localhost:8081/api/registracija', requestOptions)
            .then(response => {
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