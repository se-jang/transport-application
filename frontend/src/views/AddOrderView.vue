<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="order-container">
        <h2 class="order-title">Add Order</h2>
        <div v-for="order in filteredOrders" :key="order.id" class="order-card">
          <input
            type="checkbox"
            :value="order.id"
            v-model="selectedOrders"
            class="order-checkbox"
          />
          <component
            :is="orderComponent"
            :status="order.status"
            :orderId="order.id"
            :dueDate="order.dueDate"
          />
        </div>
        <button class="back-button" @click="goBack">Back</button>
        <button class="add-button" @click="addSelectedOrders">Add</button>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderAdmin from "../components/HeaderAdmin.vue";
import OrderAdminCard from "../components/OrderAdminCard.vue";

export default {
  data() {
    return {
      role: "admin",
      selectedOrders: [],
      orders: [
        { id: "1", dueDate: "2024-10-20", status: "checked" },
        { id: "2", dueDate: "2024-10-20", status: "ongoing" },
        { id: "3", dueDate: "2024-10-20", status: "delivered" },
        { id: "4", dueDate: "2024-10-20", status: "checked" },
        { id: "5", dueDate: "2024-10-20", status: "delivered" },
      ],
    };
  },
  computed: {
    headerComponent() {
      return HeaderAdmin;
    },
    orderComponent() {
      return OrderAdminCard;
    },
    filteredOrders() {
      return this.orders.filter((order) => order.status === "checked");
    },
  },
  methods: {
    goBack() {},
    addSelectedOrders() {
      console.log("Selected Orders: ", this.selectedOrders);
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

.order-card {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  flex: 1;
  min-height: 60px;
  background-color: white;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.order-checkbox {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.back-button {
  background-color: black;
  color: white;
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 70px;
  margin-right: 120px;
}

.add-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 20px;
}
</style>
