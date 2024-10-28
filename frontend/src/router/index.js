import { createRouter, createWebHistory } from 'vue-router';
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
  },
  {
    path: '/orders/order-detail/:orderId',
    name: 'order-detail',
    component: OrderDetailView,
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
    path: '/add-order',
    name: 'add-order',
    component: AddOrderView,
  },
  {
    path: '/user-list',
    name: 'user-list',
    component: UserListView,
  },
  {
    path: '/user-detail',
    name: 'user-detail',
    component: UserDetailView,
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

  if (!route) {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router;
