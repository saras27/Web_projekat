<template>
    <div class="home">
      <img class="form-logo" alt="Vue logo" src="../assets/book-flat.png">
      <KnjigaPage></KnjigaPage>
    </div>
  </template>
  
  <script>
  import KnjigaPage from '@/components/KnjigaPage.vue'
  
  export default {
    name: 'Knjiga',
    components: {
        KnjigaPage
    }
  }
  </script>