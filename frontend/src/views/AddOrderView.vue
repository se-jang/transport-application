<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="order-container">
        <h2 class="order-title">Add Order</h2>
        <button class="back-button" @click="goBack">Back</button>
        <button class="add-button" @click="addSelectedOrders">Add</button>

        <div class="order-list">
          <AddOrderWorkerCard
              v-for="order in orders"
              :key="order.orderId"
              :status="order.status"
              :orderId="order.orderId"
              :date="order.date"
              :isChecked="selectedOrderId === order.orderId"
              @checkOrder="selectOrder(order.orderId)"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import HeaderAdmin from "../components/HeaderAdmin.vue";
import AddOrderWorkerCard from "../components/AddOrderWorkerCard.vue";

export default {
  components: {
    AddOrderWorkerCard,
  },
  data() {
    return {
      role: "admin",
      selectedOrderId: null,
      orders: [],
    };
  },
  computed: {
    headerComponent() {
      return HeaderAdmin;
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    addSelectedOrders() {
      console.log(`Selected Order ID: ${this.selectedOrderId}`);
    },
    selectOrder(orderId) {
      this.selectedOrderId = orderId;
    },
    async fetchOrders() {
      try {
        const response = await axios.get("http://localhost:8080/orders/check-orders");
        this.orders = response.data;
      } catch (error) {
        console.error("Failed to fetch orders:", error);
      }
    },
  },
  created() {
    this.fetchOrders();
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

.back-button {
  background-color: black;
  color: white;
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  font-size: 14px;
  margin-top: 60px;
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

.order-list {
  margin-top: 20px;
}
</style>
