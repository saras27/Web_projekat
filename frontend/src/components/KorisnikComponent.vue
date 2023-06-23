<template>
    <tr>
        <td>{{ korisnikId }}</td>
        <td>{{ ime }}</td>
        <td>{{ prezime }}</td>
        <td>{{ korisnickoIme }}</td>
        <td>{{ mejlAdresa }}</td>
    </tr>
</template>

<script>

    export default {
        name: "KorisnikComp",
        props: ["korisnikId", "ime", "prezime", "korisnickoIme", "mejlAdresa"],
    };
</script>
