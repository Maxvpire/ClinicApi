import { createRouter, createWebHistory } from 'vue-router'

import HomeViews from "../views/HomeViews.vue";

const routes = [
    { path: '/', name: 'Home', component: HomeViews },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
