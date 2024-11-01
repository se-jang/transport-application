<template>
  <div class="order-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="order-id">Order ID: {{ orderId }}</h2>
    <p class="customer-name">Customer Name: {{ customerName }}</p>
    <p class="due-date">Date: {{ formattedDate(date) }}</p>
    <button class="details-button" @click="viewDetails">Details</button>
    <button v-if="status === 'delivered'" class="bill-button">Bill</button>
  </div>
</template>

<script>
import dayjs from 'dayjs';

export default {
  name: "OrderCompanyCard",
  props: {
    status: {
      type: String,
      default: "checked",
    },
    orderId: {
      type: String,
      required: true,
    },
    date: {
      type: String,
      required: true,
    },
    customerName: {
      type: String,
      required: true,
    },
  },
  methods: {
    viewDetails() {
      console.log(`Viewing details for order ID: ${this.orderId}`);
      this.$router.push({ name: 'order-detail', params: { orderId: this.orderId } });
    },
    formattedDate() {
      return dayjs(this.date).format('DD/MM/YYYY HH:mm:ss');
    },
  },
  computed: {
    statusClass() {
      switch (this.status) {
        case "checked":
          return "status-checked";
        case "ongoing":
          return "status-ongoing";
        case "delivered":
          return "status-delivered";
        default:
          return "";
      }
    },
  },
};
</script>

<style scoped>
.order-card {
  position: relative;
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 15px;
}

.order-id,
.due-date,
.customer-name,
.customer-address {
  font-family: "Inter", sans-serif;
  margin: 0;
  margin-bottom :10px;
}

.details-button,
.bill-button {
  padding: 5px 10px;
  font-family: "Inter", sans-serif;
  background-color: gray;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
  margin-right: 10px;
}

.status-indicator {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 5px 10px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  border-radius: 5px;
}

.status-checked {
  font-family: "Inter", sans-serif;
  background-color: red;
}

.status-ongoing {
  font-family: "Inter", sans-serif;
  background-color: orange;
}

.status-delivered {
  font-family: "Inter", sans-serif;
  background-color: green;
}
</style>
