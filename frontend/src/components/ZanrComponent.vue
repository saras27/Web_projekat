<template>
    <tr>
        <td>{{ zanrId }}</td>
        <td>{{ Naziv }}</td>
    </tr>
</template>

<script>

    export default {
        name: "ZanrComp",
        props: ["zanrId", "naziv"],   
    };
</script>
