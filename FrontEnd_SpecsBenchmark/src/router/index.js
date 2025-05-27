import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from "../components/HomePage.vue";
import Imprint from '../components/imprint.vue';
import Admin from '../components/AdminPage.vue';
import AdminLogInPage from "../components/AdminLogInPage.vue";

/**
 * all routes in front-end
 * @type {[{path: string, component: {new(): {$host: Element | null, $props: {}, $watch<T extends string | ((...args: any) => any)>(source: T, cb: T extends ((...args: any) => infer R) ? ((...args: [R, R, OnCleanup]) => any) : ((...args: [any, any, OnCleanup]) => any), options?: WatchOptions): WatchStopHandle, $: ComponentInternalInstance, $data: {isCreateNewsVisible}, $slots: (UnwrapSlotsType<any>&{}), $attrs: Data, $parent: ComponentPublicInstance | null, $options: any, $forceUpdate: () => void, $el: any, $nextTick: typeof nextTick, $refs: {[p: string]: unknown}, $root: ComponentPublicInstance | null, $emit: (event: string, ...args: any[]) => void, isCreateNewsVisible}}, name: string},{path: string, component: {new(): {$host: Element | null, $props: {}, $watch<T extends string | ((...args: any) => any)>(source: T, cb: T extends ((...args: any) => infer R) ? ((...args: [R, R, OnCleanup]) => any) : ((...args: [any, any, OnCleanup]) => any), options?: WatchOptions): WatchStopHandle, $: ComponentInternalInstance, $data: {}, $slots: (UnwrapSlotsType<any>&{}), $attrs: Data, $parent: ComponentPublicInstance | null, $options: any, $forceUpdate: () => void, $el: any, $nextTick: typeof nextTick, $refs: {[p: string]: unknown}, $root: ComponentPublicInstance | null, $emit: (event: string, ...args: any[]) => void}}, name: string},{path: string, component: {new(): {$host: Element | null, $props: {}, $watch<T extends string | ((...args: any) => any)>(source: T, cb: T extends ((...args: any) => infer R) ? ((...args: [R, R, OnCleanup]) => any) : ((...args: [any, any, OnCleanup]) => any), options?: WatchOptions): WatchStopHandle, $: ComponentInternalInstance, $data: {}, $slots: (UnwrapSlotsType<any>&{}), $attrs: Data, $parent: ComponentPublicInstance | null, $options: any, $forceUpdate: () => void, $el: any, $nextTick: typeof nextTick, $refs: {[p: string]: unknown}, $root: ComponentPublicInstance | null, $emit: (event: string, ...args: any[]) => void}}, meta: {requiresAuth: boolean}, name: string},{path: string, component: {new(): {$host: Element | null, $props: {}, $watch<T extends string | ((...args: any) => any)>(source: T, cb: T extends ((...args: any) => infer R) ? ((...args: [R, R, OnCleanup]) => any) : ((...args: [any, any, OnCleanup]) => any), options?: WatchOptions): WatchStopHandle, $: ComponentInternalInstance, $data: {password, username}, $slots: (UnwrapSlotsType<any>&{}), $attrs: Data, $parent: ComponentPublicInstance | null, $options: any, $forceUpdate: () => void, $el: any, $nextTick: typeof nextTick, $refs: {[p: string]: unknown}, $root: ComponentPublicInstance | null, $emit: (event: string, ...args: any[]) => void, password, username, login}}, name: string}]}
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
    if (to.meta.requiresAuth && !token) {
        next('/admin_log_in');
    } else {
        next();
    }
});

export default router
