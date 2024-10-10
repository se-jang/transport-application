import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/views/LoginView.vue";

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView,
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