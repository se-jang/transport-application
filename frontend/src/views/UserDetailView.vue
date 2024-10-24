<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="user-detail-container">
        <button class="back-button">Back</button>
        <p class="user-id-text">User ID: 12345</p>
        <div class="user-info-wrapper">
          <div class="user-info-box">
            <div class="user-info-left">
              <p><strong>User Name:</strong></p>
              <p><strong>Password:</strong></p>
            </div>
            <div class="user-info-right">
              <p><strong>Tel:</strong></p>
              <p><strong>Address:</strong></p>
            </div>
          </div>
        </div>

        <div class="order-history-box">
          <p class="order-history-title">Order History</p>
          <table class="order-history-table">
            <thead>
            <tr>
              <th>ID</th>
              <th>Due Date</th>
              <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>1</td>
              <td>2024-01-01</td>
              <td>Delivered</td>
            </tr>
            <tr>
              <td>2</td>
              <td>2024-02-15</td>
              <td>Pending</td>
            </tr>
            <tr>
              <td>3</td>
              <td>2024-03-30</td>
              <td>Cancelled</td>
            </tr>
            </tbody>
          </table>
        </div>
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
:root {
  --main-bg-color: #f5f5f5;
  --sub-bg-color: #ffffff;
  --button-bg-color: #000;
  --button-text-color: #ffffff;
  --border-color: #ddd;
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
  padding: 20px;
}

.user-detail-container {
  background-color: #e0e0e0;
  padding: 20px;
  width: 80%;
  max-width: 1000px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.user-id-text {
  font-size: 20px;
  font-weight: bold;
  position: absolute;
  top: 20px;
  left: 20px;
}

.user-info-wrapper {
  background-color: var(--sub-bg-color);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
  margin-top: 60px;
}

.back-button {
  position: absolute;
  top: 20px;
  right: 20px;
  background-color: var(--button-bg-color);
  color: var(--button-text-color);
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
  margin-bottom: 20px;
}

.user-info-box {
  display: flex;
  justify-content: space-between;
  background-color: #ffffff;
  border-radius: 1px;
}

.user-info-right {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
  margin-right: 100px;
}

.user-info-left {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
  margin-left: 5px;
}

.user-info-left p,
.user-info-right p {
  margin-bottom: 10px;
  font-size: 16px;
}

.order-history-box {
  margin-bottom: 30px;
}

.order-history-title {
  font-size: 18px;
  margin-bottom: 15px;
}

.order-history-table {
  width: 100%;
  border-collapse: collapse;
}

.order-history-table th,
.order-history-table td {
  border: 1px solid var(--border-color);
  padding: 10px;
  text-align: left;
}
</style>
