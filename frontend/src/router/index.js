import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";
import MainViews from "@/views/MainView.vue";
import OrderView from "@/views/OrderView.vue";
import CreateUserView from "@/views/CreateUserView.vue";

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
    path: '/create-user',
    name: 'createUser',
    component: CreateUserView,
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
