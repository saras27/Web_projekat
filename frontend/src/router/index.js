import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
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
    path: '/knjige-zanr/:id',
    name: 'ZanroviView',
    component: () => import(/* webpackChunkName: "ZanrComp" */ '../components/ZanrComponent.vue')
  }, 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router