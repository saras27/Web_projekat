import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Prijava from '../views/Prijava.vue'
import Registracija from '../views/Registracija.vue'
import PrikaziKnjie from '../views/PrikaziKnjige.vue'
import ListaKorisnika from '../views/ListaKorisnika.vue'
import ZanroviPrikaz from '../views/ZanroviPrikaz.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/knjige',
    name: 'knjige',
    component: () => import(/* webpackChunkName: "knjige" */ '../views/KnjigeView.vue')
  },
  {
    path: '/knjiga/:id',
    name: 'knjiga',
    component: () => import(/* webpackChunkName: "knjiga" */ '../views/KnjigeView.vue')
  },
  {
    path: '/zanrovi',
    name: 'zanrovi',
    component: () => import(/* webpackChunkName: "zanrovi" */ '../views/ZanroviView.vue')
  },
  {
    path: '/registracija',
    name: 'registracija',
    component: () => import(/* webpackChunkName: "registracija" */ '../views/RegistracijaView.vue')
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router