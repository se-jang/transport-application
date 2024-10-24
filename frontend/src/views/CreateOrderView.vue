<template>
    <div class="create-order-page">
      <header>
        <component :is="headerComponent"></component>
      </header>
  
      <div class="main-container">
        <div class="content-container">
          <h1>Create Order</h1>
  
          <div class="order-form">
            <div class="form-group">
              <label for="customer-name">Customer Name:</label>
              <input type="text" id="customer-name" v-model="customerName" />
            </div>
  
            <div class="form-group">
              <label for="address">Address:</label>
              <input type="text" id="address" v-model="address" />
            </div>
  
            <div class="form-group">
              <label for="product-name">Product Name:</label>
              <input type="text" id="product-name" v-model="productName" />
            </div>
  
            <div class="form-group">
              <label for="product-type">Product Type:</label>
              <input type="text" id="product-type" v-model="productType" />
            </div>
  
            <div class="form-group">
              <label for="product-amount">Amount:</label>
              <input type="number" id="product-amount" v-model="productAmount" />
            </div>
  
            <button class="accept-button" @click="submitOrder">Accept</button>
          </div>
  
          <button class="back-button">Back</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import HeaderCompany from "../components/HeaderCompany.vue";
  
  export default {
    data() {
      return {
        role: "company",
        customerName: "",
        address: "",
        productName: "",
        productType: "",
        productAmount: 1,
      };
    },
    computed: {
      headerComponent() {
        return HeaderCompany;
      },
    },
    methods: {
      submitOrder() {
        console.log("Order Submitted", {
          customerName: this.customerName,
          address: this.address,
          productName: this.productName,
          productType: this.productType,
          productAmount: this.productAmount,
        });
      },
    },
  };
  </script>
  
  <style>
  @import url("https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap");
  
  .create-order-page {
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
    font-weight: 600;
  }
  
  .order-form {
    margin-top: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  .form-group label {
    display: block;
    font-weight: 500;
    margin-bottom: 5px;
  }
  
  .form-group input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  .accept-button {
    margin-top: 20px;
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    width: auto;
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
  