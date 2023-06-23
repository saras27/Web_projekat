<template>
    <br>
    <h2>{{knjiga?.naslov}}</h2>
    <h3>{{knjiga?.autor?.ime}} {{knjiga?.autor?.prezime}}</h3>
    <p>{{knjiga?.opis}}</p>
    <br><br>
    <div class="row">
        <div class="column-left">
            <img v-if="knjiga !==null" class="book-img" alt="Vue logo" :src="`${publicPath}${knjiga?.naslovnaFotografija}`">
        </div>
        <div class="column-right">
            <div>
                <h3>Podaci</h3>
                <ul>
                    <li>ISBN: {{ knjiga?.isbn }}</li>
                    <li>Datum obljaviljvanja: {{ knjiga?.datumObjavljivanja }}</li>
                    <li>Broj strana: {{ knjiga?.brojStrana }}</li>
                    <li>Žanr: {{ knjiga?.zanr?.naziv }}</li>
                    <li>Ocena: {{ knjiga?.ocena }}</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="recenzije">
        <br>
        <h3>Recenzije</h3>
        <div v-if="recenzije && recenzije.length > 0">
            <div v-for="r in recenzije" :key="r.id">
                <span class="recenzija" id="korisnik">{{ r?.korisnik.korisnickoIme }}</span>
                <span class="ocena">
                    <span class="recenzija">Ocena: </span><span class="recenzija" id="ocena"> {{ r?.ocena }}</span>
                </span>
                <p>{{r?.tekst}}</p>
                <hr>
            </div>
        </div>
        <div v-else><p>Trenutno nema recenzija</p></div>
    </div>
  </template>

<script>
export default {
    name: 'KnjigaPage',
    data: function() {
        return {
            knjiga: {},
            recenzije: [],
            publicPath: process.env.BASE_URL
        }
    },
mounted: async function() {
    await fetch('http://localhost:9090/api/knjige/' + this.$route.params.id)
        .then(response => {
            return response.json();
        }).then(data => {
            this.knjiga = data;
            this.knjiga = data.knjiga;
            console.log('knjiga ' + JSON.stringify(this.knjiga));
            this.recenzije = data.recenzije;
            console.log('recenzije ' + JSON.stringify(this.recenzije));
        })
        .catch((error) => {
            console.error("Error:", error);
        });
    },
}
  </script>

<style scoped>


ul {
list-style-type: none !important;
}

#korisnik {
margin-right: 500px;
}

.book-img {
max-height: 280px;
}
.column-left {
  margin-left: 20px;
  text-align: right;
}
.column-right {
  text-align: left;
  line-height: 1.5;
}
.column-right h3 {
    padding-left: 40px;
}
.row {
    display: flex;
    flex-direction: row;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
}
.recenzije {
    text-align: left;
    margin-left: auto;
    margin-right: auto;
width: 50%;
}

span.recenzija {
display: inline;
}

span.ocena {
float: right;
}

.recenzije table {
width: 100%;
}
.recenzije h3 {
text-align: center;
}
</style>