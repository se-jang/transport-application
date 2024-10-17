<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="order-container">
        <h2 class="order-title">Order</h2>
        <component
          v-for="order in orders"
          :key="order.id"
          :is="orderComponent"
          :status="order.status"
          :orderId="order.id"
          :dueDate="order.dueDate"
        />
      </div>
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
      role: "customer",
      orders: [
        { id: "1", dueDate: "2024-10-20", status: "checked" },
        { id: "2", dueDate: "2024-10-20", status: "ongoing" },
        { id: "3", dueDate: "2024-10-20", status: "delivered" },
        { id: "4", dueDate: "2024-10-20", status: "ongoing" },
        { id: "5", dueDate: "2024-10-20", status: "delivered" }
      ],
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
:root {
  --main-bg-color: #4a4a4a;
  --sub-bg-color: #eeeeee;
  --card-bg-color: #ffffff;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.page-container {
  background-color: var(--main-bg-color);
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-container {
  display: flex;
  justify-content: center;
  flex: 1;
}

.order-title {
  font-family: "Inter", sans-serif;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
  text-align: left;
}

.order-container {
  background-color: #e6e6e6;
  padding: 20px;
  width: 80%;
  max-width: 1200px;
  overflow-y: auto;
  height: 100%;
}
</style>
