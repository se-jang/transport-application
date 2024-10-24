import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";
import MainViews from "@/views/MainView.vue";
import OrderView from "@/views/OrderView.vue";
import OrderDetailView from "@/views/OrderDetailView.vue";
import CreateUserView from "@/views/CreateUserView.vue";
import WorkerListView from "@/views/WorkerListView.vue";
import WorkerDetailView from "@/views/WorkerDetailView.vue";
import AddOrderView from "@/views/AddOrderView.vue";
import UserListView from "@/views/UserListView.vue";
import UserDetailView from "@/views/UserDetailView.vue";
import CreateOrderView from "@/views/CreateOrderView.vue";

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  {
    path: '/main/:role',
    name: 'main',
    component: MainViews,
    props: true,
  },
  {
    path: '/orders/:role',
    name: 'orders',
    component: OrderView,
    props: true,
  },
  {
    path :'/order-detail/:role',
    name: 'order-detail',
    component: OrderDetailView,
    props: true,
  },
  {
    path: '/create-user/:role',
    name: 'createUser',
    component: CreateUserView,
    props: true,
  },
  {
    path: '/worker-list/:role',
    name: 'worker-list',
    component: WorkerListView,
    props: true,
  },
  {
    path: '/worker-detail/:role',
    name: 'worker-detail',
    component: WorkerDetailView,
    props: true,
  },
  {
    path: '/add-order/:role',
    name: 'add-order',
    component: AddOrderView,
    props: true,
  },
  {
    path: '/user-list/:role',
    name: 'user-list',
    component: UserListView,
    props: true,
  },
  {
    path: '/user-detail/:role',
    name: 'user-detail',
    component: UserDetailView,
    props: true,
  },
  {
    path: '/create-order/:role',
    name: 'create-order',
    component: CreateOrderView,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const routeExists = routes.some(route => route.name === to.name);

  if (!routeExists) {
    next({ name: "login" });
  } else {
    next();
  }
});

export default router;
