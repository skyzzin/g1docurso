import { createRouter, createWebHistory } from 'vue-router'
import AppView from "@/views/AppView"
import NewView from "@/views/NewViewMobile.vue"
import CommitUI from '@/views/CommitUI.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: AppView
  },
  {
    path: '/new/:uuid',
    name: 'new',
    component: NewView
  },
  {
    path: '/new/:uuid/commit/:uuid_sender',
    name: 'new/commit',
    component: CommitUI
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
