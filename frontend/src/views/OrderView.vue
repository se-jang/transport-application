<template>
  <div>
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="order-container">
      <component :is="orderComponent"></component>
    </div>
  </div>
</template>

<script>
import HeaderAdmin from "../components/HeaderAdmin.vue";
import HeaderWorker from "../components/HeaderWorker.vue";
import HeaderCompany from "../components/HeaderCompany.vue";
import HeaderCustomer from "../components/HeaderCustomer.vue";
import OrderAdminCard from "../components/OrderAdminCard.vue";
import OrderWorkerCard from "../components/OrderWorkerCard.vue";
import OrderCompanyCard from "../components/OrderCompanyCard.vue";
import OrderCustomerCard from "../components/OrderCustomerCard.vue";

export default {
  data() {
    return {
      role: "admin",
    };
  },
  computed: {
    headerComponent() {
      switch (this.role) {
        case "admin":
          return HeaderAdmin;
        case "worker":
          return HeaderWorker;
        case "company":
          return HeaderCompany;
        case "customer":
          return HeaderCustomer;
        default:
          return null;
      }
    },
    orderComponent() {
      switch (this.role) {
        case "admin":
          return OrderAdminCard;
        case "worker":
          return OrderWorkerCard;
        case "company":
          return OrderCompanyCard;
        case "customer":
          return OrderCustomerCard;
        default:
          return null;
      }
    },
  },
};
</script>

<style scoped>
.order-container {
  background-color: #f0f0f0;
  padding: 20px;
  min-height: 100vh;
}
</style>
