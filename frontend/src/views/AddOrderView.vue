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
              type="radio"
          :value="order.id"
          v-model="selectedOrder"
          class="order-checkbox"
          />
          <component
              :is="orderComponent"
              :status="order.status"
              :orderId="order.id"
              :dueDate="order.date"
          />
        </div>
        <button class="back-button" @click="goBack">Back</button>
        <button class="add-button" @click="addSelectedOrder">Add</button>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderAdmin from "../components/HeaderAdmin.vue";
import OrderAdminCard from "../components/OrderAdminCard.vue";
import axios from "axios";

export default {
  data() {
    return {
      role: "admin",
      selectedOrder: null,
      orders: [],
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
      return Array.isArray(this.orders) ? this.orders.filter((order) => order.status === "checked") : [];
    },
  },
  methods: {
    goBack() {
      this.$router.push("/worker-list");
    },
    addSelectedOrder() {
      console.log("Selected Order: ", this.selectedOrder);
    },
    fetchCheckedOrders() {
      axios.get("http://localhost:8080/orders/check-order")
          .then(response => {
            console.log(response.data); // ตรวจสอบข้อมูลที่ได้รับ

            // ตรวจสอบว่า response.data เป็น array หรือไม่
            if (Array.isArray(response.data)) {
              this.orders = response.data.map(order => ({
                id: order.id,
                status: order.status,
                date: order.date,
              }));
            } else {
              console.error("Received data is not an array:", response.data);
              this.orders = []; // ตั้งค่าเป็น array ว่างถ้าไม่เป็น array
            }
          })
          .catch(error => {
            console.error("There was an error fetching checked orders!", error);
            this.orders = [];
          });
    }
  },
  mounted() {
    this.fetchCheckedOrders();
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
