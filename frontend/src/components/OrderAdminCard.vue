<template>
  <div class="order-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="order-id">Order ID: {{ orderId }}</h2>
    <p class="due-date">Date: {{ date }}</p>
    <button class="details-button" @click="viewDetails">Details</button>
  </div>
</template>

<script>
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
  },
  computed: {
    statusClass() {
      return {
        "status-red": this.status === "UNCHECKED",
        "status-orange": this.status === "NOT_UNCHECKED",
        "status-green": this.status === "DELIVERED",
        "status-yellow": this.status === "ON_GOING",
      };
    },
  },
  methods: {
    viewDetails() {
      console.log(`Viewing details for order ID: ${this.orderId}`);
      this.$router.push({ name: 'order-detail', params: { orderId: this.orderId } });
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
.due-date {
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
</style>
