<template>
    <br>
    <h2>{{knjiga.naslov}}</h2>
    <h3>{{knjiga.autor?.korisnik?.ime}} {{knjiga.autor?.korisnik?.prezime}}</h3>
    <p>{{knjiga.opis}}</p>
    <br><br>
    <div class="row">
        <div class="column-left">
            <img class="book-img" alt="Vue logo" src="../assets/ana.jpg">
        </div>
        <div class="column-right">
            <div>
                <h3>Podaci</h3>
                <ul>
                    <li>ISBN: {{ knjiga.ISBN }}</li>
                    <li>Datum obljaviljvanja: {{ knjiga.datumObjavljivanja }}</li>
                    <li>Broj strana: {{ knjiga.brojStrana }}</li>
                    <li>Žanr: {{ knjiga.zanr?.naziv }}</li>
                    <li>Ocena: {{ knjiga.ocena }}</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="recenzije">
        <br>
        <h3>Recenzije</h3>
        <table class="items-table">
        <tr>
            <th>Autor recenzije</th>
        </tr>
        <tr>
            <td><p>Bas je jako dobra knjiga, nemam reci</p></td>
        </tr>
    </table>
    </div>
  </template>
  
  <script>
    export default {
    name: 'KnjigaPage',
    data: function() {
        return {
            knjiga: {},
            recenzije: [],
        }
    },
    mounted: async function() {
        await fetch('http://localhost:9090/api/knjige/' + this.$route.params.id)
            .then(response => {
                return response.json();
            }).then(data => {
                this.knjiga = data;
                console.log('knjiga ' + JSON.stringify(this.knjiga));
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

.recenzije table {
    width: 100%;
}

.recenzije h3 {
    text-align: center;
}
</style>