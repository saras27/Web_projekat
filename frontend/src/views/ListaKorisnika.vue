<template>
    <h1>Korisnici</h1>
    <br>
        <div class = "korisnik1">
            <table id = "korisnici">
                <tr>
                    <th>Id</th>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Korisnicko ime</th>
                    <th>Mejl adresa</th>
                </tr>

                <ZanrComp v-for = "korisnik in korisnici"
                    :key = "korisnik.korisnikId"
                    :korisnikId = "korisnik.korisnikId"
                    :ime = korisnik.ime
                    :prezime = korisnik.prezime
                    :korisnickoIme = korisnik.korisnickoIme
                    :mejlAdresa = korisnik.mejlAdresa>
                </ZanrComp>
            </table>
        </div>
</template>

<script>
import KorisnikComp from '@/components/KorisnikComponent.vue'
export default {
    name: "ListaKorisnika",
    data: function(){
        return{
            korisnici: [],
        };
    },
    components:{
        KorisnikComp,
    },
    mounted(){
        fetch('http://localhost:9090/api/korisnici')
            .then(response => response.json())
            .then(data => {
                console.log("Success:", data);
                this.korisnici = data;
            })
            .catch(error => {
                console.error("Error:", error);
            });
    }
}
</script>
<style>
    h1{
        color:coral;
    }
    .zanr1{
        display: flex;
    }
</style>
