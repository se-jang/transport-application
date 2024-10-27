<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="order-container">
        <h2 class="order-title">Order</h2>
        <div>
          <label v-if="userRole === 'ADMIN'" for="status">Filter by Status:</label>
          <select v-if="userRole === 'ADMIN'" v-model="selectedStatus" @change="fetchOrders">
            <option value="all">All</option>
            <option value="uncheck">Unchecked</option>
            <option value="not-uncheck">Not Unchecked</option>
            <option value="delivered">Delivered</option>
            <option value="on-going">On-going</option>
          </select>
          <button v-if="userRole !== 'ADMIN'" @click="fetchOwnOrders">My Orders</button>
        </div>
        <component
            v-for="order in orders"
            :key="order.id"
            :is="orderComponent"
            :status="order.status"
            :orderId="order.id"
            :date="order.date || 'N/A'"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import HeaderAdmin from "../components/HeaderAdmin.vue";
import HeaderWorker from "../components/HeaderWorker.vue";
import HeaderCompany from "../components/HeaderCompany.vue";
import OrderAdminCard from "../components/OrderAdminCard.vue";
import OrderWorkerCard from "../components/OrderWorkerCard.vue";
import OrderCompanyCard from "../components/OrderCompanyCard.vue";
import axios from "axios";

export default {
  components: {
    HeaderAdmin,
    HeaderWorker,
    HeaderCompany,
    OrderAdminCard,
    OrderWorkerCard,
    OrderCompanyCard,
  },
  data() {
    return {
      orders: [],
      selectedStatus: "all",
    };
  },
  computed: {
    ...mapGetters(["userRole"]),
    headerComponent() {
      switch (this.userRole) {
        case "ADMIN":
          return HeaderAdmin;
        case "WORKER":
          return HeaderWorker;
        case "USER":
          return HeaderCompany;
        default:
          return null;
      }
    },
    orderComponent() {
      switch (this.userRole) {
        case "ADMIN":
          return OrderAdminCard;
        case "WORKER":
          return OrderWorkerCard;
        case "USER":
          return OrderCompanyCard;
        default:
          return null;
      }
    },
  },
  methods: {
    async fetchOrders() {
      let endpoint = 'http://localhost:8080/orders';
      switch (this.selectedStatus) {
        case 'uncheck':
          endpoint = 'http://localhost:8080/orders/uncheck-order';
          break;
        case 'not-uncheck':
          endpoint = 'http://localhost:8080/orders/not-uncheck-orders';
          break;
        case 'delivered':
          endpoint = 'http://localhost:8080/orders/delivered-orders';
          break;
        case 'on-going':
          endpoint = 'http://localhost:8080/orders/on-going-orders';
          break;
      }

      try {
        const response = await axios.get(endpoint);
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date,
          status: order.status.toLowerCase(),
        }));
        console.log("Fetched orders:", this.orders);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    },
    async fetchOwnOrders() {
      try {
        const userId = this.userRole;
        if (userId === "USER") {
          const response = await axios.get(`http://localhost:8080/orders/${userId}`);
          this.orders = response.data;
        } else if (userId === "WORKER") {
          const response = await axios.get(`http://localhost:8080/orders/${userId}`);
          this.orders = response.data;
        }
      } catch (error) {
        console.error('Error fetching own orders:', error);
      }
    },
  },
  mounted() {
    if (!this.orderId || !this.status) {
      console.warn('Invalid orderId or status:', this.orderId, this.status);
    } else if (this.userRole === 'ADMIN') {
      this.fetchOrders();
    } else {
      this.fetchOwnOrders();
    }
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
