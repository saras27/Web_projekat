<template>
    <tr>
        <td>{{ zanrId }}</td>
        <td>{{ naziv }}</td>
    </tr>
</template>

<script>

    export default {
        name: "ZanrComp",
        props: ["zanrId", "naziv"],   
    };
</script>
