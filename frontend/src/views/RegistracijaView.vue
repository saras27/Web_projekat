
<template>
<img class="form-logo" alt="Vue logo" src="../assets/book-flat.png">
<div>
  <h2>registruj se</h2>
  <RegistracijaForm></RegistracijaForm>
</div>
</template>

<script>
import RegistracijaForm from '@/components/RegistracijaForm.vue';
export default {
    components:{
        RegistracijaForm
    }
}
</script>
