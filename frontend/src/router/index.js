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
  const route = router.resolve(to);
  const userRole = store.getters.userRole;

  if (!route) {
    next({ name: 'login' });
  } else if (to.path === '/create-user' || to.path === '/user-list' || to.path === '/user-detail/:userId' || to.path === '/worker-list' || to.path === '/worker-detail/:workerId'  || to.path === '/worker/worker-detail/:workerId/add-order' && userRole !== 'ADMIN'){
     
  }else {
    next();
  }
});

export default router;
