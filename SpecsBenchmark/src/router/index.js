import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from "../components/HomePage.vue";
import Imprint from '../components/imprint.vue';

const routes = [
    {
     path: "/",
        name: "Home",
        component: HomePage
    },
    {
        path: "/impressum",
        name: "Impressum",
        component: Imprint
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
