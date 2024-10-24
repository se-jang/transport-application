<template>
  <div class="page-container">
    <header>
      <component :is="headerComponent"></component>
    </header>

    <div class="main-container">
      <div class="order-detail-container">
        <button class="back-button">Back</button>
        <p class="order-id-text">Order ID: 12345</p>
        <div class="order-info-wrapper">
          <div class="order-info-box">
            <div class="order-info-left">
              <p><strong>Due date:</strong></p>
              <p><strong>Company Name:</strong></p>
            </div>
            <div class="order-info-right">
              <p><strong>Status:</strong></p>
              <p><strong>Customer Name:</strong></p>
              <p><strong>Address:</strong></p>
            </div>
          </div>
        </div>

        <div class="product-list-box">
          <p class="product-list-title">Product List</p>
          <table class="product-list-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Amount</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Product A</td>
                <td>Type 1</td>
                <td>10</td>
              </tr>
              <tr>
                <td>2</td>
                <td>Product B</td>
                <td>Type 2</td>
                <td>5</td>
              </tr>
              <tr>
                <td>3</td>
                <td>Product C</td>
                <td>Type 3</td>
                <td>20</td>
              </tr>
            </tbody>
          </table>
        </div>

        <button v-if="role === 'admin'" class="checked-button">Checked</button>
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

.order-detail-container {
  background-color: #e0e0e0;
  padding: 20px;
  width: 80%;
  max-width: 1000px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.order-id-text {
  font-size: 20px;
  font-weight: bold;
  position: absolute;
  top: 20px;
  left: 20px;
}

.order-info-wrapper {
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

.checked-button {
  position: absolute;
  bottom: 20px;
  right: 20px;
  background-color: var(--button-bg-color);
  color: var(--button-text-color);
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
}

.order-info-box {
  display: flex;
  justify-content: space-between;
  background-color: #ffffff;
  border-radius: 1px;
}

.order-info-right {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
  margin-right: 100px;
}

.order-info-left {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
  margin-left: 5px;
}

.order-info-left p,
.order-info-right p {
  margin-bottom: 10px;
  font-size: 16px;
}

.product-list-box {
  margin-bottom: 30px;
}

.product-list-title {
  font-size: 18px;
  margin-bottom: 15px;
}

.product-list-table {
  width: 100%;
  border-collapse: collapse;
}

.product-list-table th,
.product-list-table td {
  border: 1px solid var(--border-color);
  padding: 10px;
  text-align: left;
}
</style>
