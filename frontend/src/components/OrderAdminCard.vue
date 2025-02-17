<template>
  <div class="order-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="order-id">Order ID: {{ orderId }}</h2>
    <p class="customer-name">Customer Name: {{ customerName }}</p>
    <p class="due-date">Date: {{ formattedDate(date) }}</p>

    <button class="details-button" @click="viewDetails">Details</button>
  </div>
</template>

<script>
import dayjs from "dayjs";

export default {
  name: "OrderAdminCard",
  props: {
    status: {
      type: String,
      required: true,
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
  computed: {
    statusClass() {
      switch (this.status) {
        case "UNCHECK":
          return "status-uncheck";
        case "CHECKED":
          return "status-checked";
        case "ONGOING":
          return "status-ongoing";
        case "DELIVERED":
          return "status-delivered";
        case "UPLOADED":
          return "status-uploaded";
        case "COMPLETED":
          return "status-completed";
        default:
          return "";
      }
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
.customer-address{
  font-family: "Inter", sans-serif;
  margin: 0;
  margin-bottom: 10px;
}

.details-button {
  padding: 5px 10px;
  font-family: "Inter", sans-serif;
  background-color: gray;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
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

.status-uncheck {
  font-family: "Inter", sans-serif;
  background-color: #D3D3D3;
}

.status-checked {
  font-family: "Inter", sans-serif;
  background-color: #1E90FF;
}

.status-ongoing {
  font-family: "Inter", sans-serif;
  background-color: #FFA500;
}

.status-delivered {
  font-family: "Inter", sans-serif;
  background-color: #32CD32;
}

.status-uploaded {
  font-family: "Inter", sans-serif;
  background-color: #8A2BE2;
}

.status-completed {
  font-family: "Inter", sans-serif;
  background-color: #00289e;
}
</style>
