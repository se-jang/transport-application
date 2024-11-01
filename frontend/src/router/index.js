import { createRouter, createWebHistory } from 'vue-router';
import store from '@/stores/store.js';
import LoginView from '@/views/LoginView.vue';
import MainViews from '@/views/MainView.vue';
import OrderView from '@/views/OrderView.vue';
import OrderDetailView from '@/views/OrderDetailView.vue';
import CreateUserView from '@/views/CreateUserView.vue';
import WorkerListView from '@/views/WorkerListView.vue';
import WorkerDetailView from '@/views/WorkerDetailView.vue';
import AddOrderView from '@/views/AddOrderView.vue';
import UserListView from '@/views/UserListView.vue';
import UserDetailView from '@/views/UserDetailView.vue';
import CreateOrderView from '@/views/CreateOrderView.vue';

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  {
    path: '/main',
    name: 'main',
    component: MainViews,
  },
  {
    path: '/orders/:id?',
    name: 'orders',
    component: OrderView,
    props: true,
  },
  {
    path: '/orders/order-detail/:orderId',
    name: 'order-detail',
    component: OrderDetailView,
    props: true,
  },
  {
    path: '/create-user',
    name: 'createUser',
    component: CreateUserView,
  },
  {
    path: '/worker-list',
    name: 'worker-list',
    component: WorkerListView,
  },
  {
    path: '/worker-detail/:workerId',
    name: 'worker-detail',
    component: WorkerDetailView,
    props: true,
  },
  {
    path: '/worker/worker-detail/:workerId/add-order',
    name: 'add-order',
    component: AddOrderView,
  },
  {
    path: '/user-list',
    name: 'user-list',
    component: UserListView,
  },
  {
    path: '/user-detail/:userId',
    name: 'user-detail',
    component: UserDetailView,
    props: true
  },
  {
    path: '/create-order',
    name: 'create-order',
    component: CreateOrderView,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const routeExists = router.getRoutes().some(route => route.path === to.path); 
  const userRole = store.getters.userRole;
  const adminRoutes = ['/create-user', '/user-list', '/user-detail/:userId', '/worker-list', '/worker-detail/:workerId', '/worker/worker-detail/:workerId/add-order'];
  const userRoutes = ['/create-order'];

  if (!routeExists) {
    next({ name: 'login' });
  } else if (adminRoutes.includes(to.path) && userRole !== 'ADMIN') {
    next({ name: 'login' });
  } else if (userRoutes.includes(to.path) && userRole !== 'USER') {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router;
