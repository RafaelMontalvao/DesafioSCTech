import EmpreendimentoFormPage from '@/pages/EmpreendimentoFormPage.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      component: () => import('@/layouts/DefaultLayout.vue'),
      children:[
        {
          path:'',
          name: 'home',
          component: ()=> import('@/pages/HomePage.vue')
        },

       {
        path:'empreendimento/:id',
        name:'empreendimento',
        component:EmpreendimentoFormPage
       }
      ]
    }
  ],
})

export default router
