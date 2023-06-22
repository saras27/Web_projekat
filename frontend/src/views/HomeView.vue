<template>
  <div class="home">
    <img class="form-logo" alt="Vue logo" src="../assets/book-flat.png">
    <h2>Uloguj se</h2>
    <LoginForm></LoginForm>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'

export default {
  name: 'Home',
  components: {
    LoginForm
  }
}
</script>