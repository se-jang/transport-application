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
          <select v-if="userRole === 'ADMIN'" v-model="selectedStatus" @change="fetchOrdersFilter">
            <option value="all">All</option>
            <option value="uncheck">Unchecked</option>
            <option value="not-uncheck">Not Unchecked</option>
            <option value="on-going">On-going</option>
            <option value="delivered">Delivered</option>
            <option value="uploaded">Uploaded</option>
            <option value="complete">Complete</option>
          </select>
          <button v-if="userRole !== 'ADMIN'" @click="fetchOwnOrders">My Orders</button>
        </div>
        <component
          v-for="order in orders"
          :key="order.id"
          :is="orderComponent"
          :orderId="order.id"
          :date="order.date || 'N/A'"
          :status="order.status"
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
  created() {
    this.fetchOrders(); // Load orders when component is created
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
  try {
    const response = await axios.get("http://localhost:8080/orders/all-orders?fields=id,date,status,customerName");

    const data = Array.isArray(response.data) ? response.data : [response.data];
    console.log(response.data)
    this.orders = data.map(order => ({
      id: order.id,
      date: order.date || 'N/A',
      status: order.status,
    }));
  } catch (error) {
    console.error("Error fetching orders:", error);
  }
},

async fetchOrdersFilter() {
  let endpoint;
  switch (this.selectedStatus) {
    case 'uncheck':
      endpoint = 'http://localhost:8080/orders/uncheck-orders';
      break;
    case 'not-uncheck':
      endpoint = 'http://localhost:8080/orders/not-uncheck-orders';
      break;
    case 'on-going':
      endpoint = 'http://localhost:8080/orders/on-going-orders';
      break;
    case 'delivered':
      endpoint = 'http://localhost:8080/orders/delivered-orders';
      break;
    case 'uploaded':
      endpoint = 'http://localhost:8080/orders/uploaded-orders';
      break;
    case 'complete':
      endpoint = 'http://localhost:8080/orders/complete-orders';
      break;
    case 'all':
    default:
      endpoint = 'http://localhost:8080/orders/all-orders';
      break;
  }

  try {
    const response = await axios.get(endpoint);
    
    // ถ้า response.data เป็น object ให้แปลงเป็น array
    const data = Array.isArray(response.data) ? response.data : [response.data];

    this.orders = data.map(order => ({
      id: order.id,
      date: order.date || 'N/A',
      status: order.status.toLowerCase(),
      customerName: order.customerName,
      customerAddress: order.customerAddress,
      orderLines: order.orderLines.map(line => ({
        productId: line.id.productId,
        quantity: line.quantity,
      })),
    }));
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
},

    async fetchOwnOrders() {
      try {
        const userId = this.userRole; // Change this to fetch by actual user ID if needed
        const response = await axios.get(`http://localhost:8080/orders/${userId}`);
        this.orders = response.data.map(order => ({
          id: order.id,
          date: order.date || 'N/A',
          status: order.status.toLowerCase(),
          customerName: order.customerName,
          customerAddress: order.customerAddress,
          orderLines: order.orderLines.map(line => ({
            productId: line.id.productId,
            quantity: line.quantity,
          })),
        }));
      } catch (error) {
        console.error('Error fetching own orders:', error);
      }
    },
  },
  mounted() {
    if (this.userRole === 'ADMIN') {
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
