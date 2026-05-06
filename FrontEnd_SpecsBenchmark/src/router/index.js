import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from "../components/HomePage.vue";
import Imprint from '../components/imprint.vue';
import Admin from '../components/AdminPage.vue';
import AdminLogInPage from "../components/AdminLogInPage.vue";
import MoreNewsPage from "../components/MoreNewsPage.vue";
import Article from "../components/Article.vue";
import NotFound from '../components/NotFound.vue';
import Gpu from '../components/Gpu.vue';
import AllGpus from '../components/AllGpus.vue';

/**
 * all routes in front-end
 */

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
    },
    {
        path: "/admin",
        name: "Administrator Seite",
        component: Admin,
        meta: {requiresAuth: true}
    },
    {
        path: "/admin_log_in",
        name: "Administrator Log In",
        component: AdminLogInPage
    },
    {
        path: "/news",
        name: "News Page",
        component: MoreNewsPage
    },
    {
        path: '/article/:id',
        name: "article",
        component: Article,
        props: true
    },
    {
        path: "/notFound",
        name: "notFound",
        component: NotFound
    },
    {
        path: "/gpu/:gpuName",
        name: "gpu",
        component: Gpu,
        props: true
    },
    {
        path: "/allGpus",
        name: "allGpus",
        component: AllGpus
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFoundCatchAll',
        component: NotFound
    }
    
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

/**
 * checks if you can access page if it needs and you have authorization
 * to -> Route Location
 * from -> Route at the start
 * next -> navigation access or deny
 */

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('jwt');

    if (to.meta.requiresAuth) {
        if (!token || isTokenExpired(token)) {
            localStorage.removeItem('jwt');
            next('/admin_log_in');
        } else {
            next();
        }
    } else {
        next();
    }
});

/**
 * checks if token is expired
 * @param token
 * @returns {boolean}
 */

function isTokenExpired(token) {
    if (!token) return true;

    const payloadBase64 = token.split('.')[1];
    const payloadJson = atob(payloadBase64);
    const payload = JSON.parse(payloadJson);

    const now = Math.floor(Date.now() / 1000);
    return payload.exp < now;
}

export default router
