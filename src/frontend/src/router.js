import { createRouter, createWebHashHistory } from 'vue-router';
// import Dashboard from './components/Dashboard.vue';
// import Admin from './components/Admin.vue';
import Dash from './components/Dash.vue';
import Crud from './components/Crud.vue';

const routes = [
    {
        path: '/',
        alias: '/admin/all',
        name: 'dash',
        component: Dash
    },
    {
        path: '/crud',
        alias: '/admin/all',
        name: 'crud',
        component: Crud
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

export default router;
