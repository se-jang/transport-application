<template>
  <header>
    <component :is="headerComponent"></component>
  </header>
  <div class="container">
    <h1>Create User</h1>
    <button class="back-button">Back</button>
  <div class="create-user-container">
    <form @submit.prevent="handleSubmit" class="create-user-form">
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" required placeholder="Enter username" />
      </div>
      <div class="form-group">
        <label for="customerName">Customer Name</label>
        <input type="text" id="customerName" required placeholder="Enter customer name" />
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" required placeholder="Enter address" />
      </div>
      <button type="submit" class="accept-button">Accept</button>
    </form>
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
  methods: {
    handleSubmit() {
      console.log("Form submitted");
    },
  },
};
</script>

<style scoped>
.container{
  max-width: 80%;
  height: 94vh;
  margin: 0 auto;
  background-color: #f9f9f9; 
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}
.create-user-container {
  max-width: 90%;
  height: 75%;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9; 
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  display: inline-block;
  text-align: left;
  margin-top: 5%;
  margin-bottom: 0px; 
  margin-left: 4%; 
}

.create-user-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

label {
  margin-left: 3%;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  margin-left: 3%;
  width: 30%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px; 
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); 
}

.accept-button {
  margin-left: 3%;
  width: 5%;
  background-color: #189e1f;
  color: rgb(0, 0, 0);
  border: none; 
  border-radius: 5px;
  cursor: pointer;
}

.accept-button:hover {
  background-color: #24be2c;
}

.back-button {
  display: inline-block;
  margin-bottom: 20px;
  margin-left: 75%;
  width: 5%;
  background-color: #363636;
  color: rgb(255, 255, 255);
  border: none; 
  border-radius: 5px;
  cursor: pointer;
}
.back-button:hover {
  background-color: #808080;
}
</style>
