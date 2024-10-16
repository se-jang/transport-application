<template>
  <div class="order-card">
    <h2 class="order-id">Order ID</h2>
    <p class="due-date">Due Date: ...</p>
    <button class="details-button">Details</button>
  </div>
</template>

<script>
export default {
  name: "OrderAdminCard",
};
</script>

<style scoped>
.order-card {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.order-id,
.due-date {
  font-family: "Inter", sans-serif;
  margin: 0;
}

.details-button {
  padding: 5px 8px;
  font-family: "Inter", sans-serif;
  background-color: gray;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
  align-self: flex-start;
  width: auto;
}
</style>
