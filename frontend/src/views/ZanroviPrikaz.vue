<template>
    <h1>Zanrovi</h1>
    <br>
        <div class = "zanr1">
            <table id = "zanrovi">
                <tr>
                    <th>Id</th>
                    <th>Naziv</th>
                </tr>

                <ZanrComp v-for = "zanr in zanrovi"
                    :key = "zanr.zanrId"
                    :zanrId = "zanr.zanrId"
                    :naziv = zanr.naziv>
                </ZanrComp>
            </table>
        </div>
</template>

<script>
import ZanrComp from '@/components/ZanrComponent.vue'
export default {
    name: "ZanrPrikaz",
    data: function(){
        return{
            zanrovi: [],
        };
    },
    components:{
        ZanrComp,
    },
    mounted(){
        fetch('http://localhost:8080/api/zanrovi')
            .then(response => response.json())
            .then(data => {
                console.log("Success:", data);
                this.zanrovi = data;
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
