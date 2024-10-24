<template>
  <div class="worker-details-page">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="content-container">
        <h1>Worker ID: 00001</h1>

        <div class="order-list-header">
          <h2>Order list</h2>
          <button class="add-button">Add</button>
        </div>

        <div class="order-list-container">
          <table class="order-list-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Due date</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in orders" :key="order.id">
                <td>{{ order.id }}</td>
                <td>{{ order.dueDate }}</td>
                <td>{{ order.status }}</td>
              </tr>
            </tbody>
          </table>
        </div>

        <button class="back-button">Back</button>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderAdmin from "../components/HeaderAdmin.vue";
import HeaderWorker from "../components/HeaderWorker.vue";
import HeaderCompany from "../components/HeaderCompany.vue";
import HeaderCustomer from "../components/HeaderCustomer.vue";

export default {
  data() {
    return {
      role: "admin",
      orders: [
        { id: "12345", dueDate: "2024-10-21", status: "Ongoing" },
        { id: "12346", dueDate: "2024-10-22", status: "Ongoing" },
        { id: "12347", dueDate: "2024-10-23", status: "Delivered" },
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
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap");

.worker-details-page {
  background-color: #f5f5f5;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  font-family: "Inter", sans-serif;
}

.main-container {
  display: flex;
  justify-content: center;
  flex: 1;
  padding: 20px;
}

.content-container {
  background-color: #ffffff;
  padding: 20px;
  width: 80%;
  max-width: 1000px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

h1 {
  display: inline-block;
  font-weight: 600;
}

.order-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.add-button {
  width: auto;
  font-weight: 500;
  background-color: #007bff;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 550px;
}

.order-list-container {
  margin-top: 20px;
}

.order-list-table {
  width: 100%;
  border-collapse: collapse;
}

.order-list-table th,
.order-list-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.back-button {
  margin-top: 20px;
  background-color: #000;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-weight: 500;
}
</style>
