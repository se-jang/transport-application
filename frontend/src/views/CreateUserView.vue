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
        <label for="password">Password</label>
        <input type="text" id="password" required placeholder="Enter password" />
      </div>
      <div class="form-group">
        <label for="phoneNumber">Phone Number</label>
        <input type="text" id="phoneNumber" required placeholder="Enter phone number" />
      </div>
      <div class="form-group">
        <label for="email">E-mail</label>
        <input type="text" id="email" required placeholder="Enter E-mail" />
      </div>
      <div class="form-group">
        <label for="role">Role</label>
        <select id="role" required>
        <option value="" disabled selected>Select Role</option>
        <option value="USER">User</option>
        <option value="WORKER">Worker</option>
    </select>
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
  max-width: 70%;
  height: 94vh;
  margin: 0 auto;
  background-color: #f9f9f9; 
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.create-user-container {
  max-width: 80%;
  height: 88%;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9; 
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
}

.create-user-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.form-group {
  width: 25%;
  display: flex; 
  flex-direction: column;
  align-items: flex-start; 
  margin-bottom: 0px; 
}

input{
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px; 
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1); 
}

select {
  border-radius: 5px;
}

.accept-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 90px;
  height: 40px;
  background-color: #189e1f;
  color: rgb(255, 255, 255);
  border: none;
  border-radius: 5px;
  cursor: pointer;       
}


h1 {
  display: inline-block;
  text-align: left;
  margin-top: 5%;
  margin-bottom: 0px; 
  margin-left: 10%; 
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

.accept-button {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 90px;
  height: 40px;
  background-color: #189e1f;
  color: rgb(255, 255, 255);
  border: none;
  border-radius: 5px;
  cursor: pointer;       
}

.accept-button:hover {
  background-color: #24be2c;
}


.back-button {
  display: inline-block;
  margin-top: 5%;
  margin-bottom: auto;
  margin-left: auto;
  margin-right: 21%;
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
