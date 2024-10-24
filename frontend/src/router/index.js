import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";
import MainViews from "@/views/MainView.vue";
import OrderView from "@/views/OrderView.vue";
import OrderDetailView from "@/views/OrderDetailView.vue";
import CreateUserView from "@/views/CreateUserView.vue";
import WorkerListView from "@/views/WorkerListView.vue"
import WorkerDetailView from "@/views/WorkerDetailView.vue";
import AddOrderView from "@/views/AddOrderView.vue";
import UserListView from "@/views/UserListView.vue";

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
    path: '/orders',
    name: 'orders',
    component: OrderView,
  },
  {
    path :'/order-detail',
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
    path: '/worker-detail',
    name: 'worker-detail',
    component: WorkerDetailView
  },
  {
    path: '/add-order',
    name: 'add-order',
    component: AddOrderView
  },
  {
    path: '/user-list',
    name: 'user-list',
    component: UserListView
  }
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
