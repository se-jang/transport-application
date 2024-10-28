<template>
  <div class="company-header">
    <div class="header-container">
      <router-link to="/main" class="logo">Transport application</router-link>
      <nav class="company-nav">
        <router-link to="/create-order">Create Order</router-link>
        <router-link to="/orders">My Order</router-link>
      </nav>
    </div>

    <div class="company-profile">
      <a href="#" @click="contact">Contact</a>
      <a href="#" @click="logout">Logout</a>
      <img
        src="https://via.placeholder.com/40"
        alt="Profile"
        class="profile-image"
      />
    </div>
  </div>
</template>


<script>
export default {
  name: "HeaderCompany",
  methods: {
    logout(){
      this.$store.dispatch("clearUserData");
      this.$router.push("/login");
    },
    contact(){
      alert("Tel: 089-***-****")
    }
  }
};
</script>

<style scoped>
.company-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 10px 20px;
  border-bottom: 2px solid #e0e0e0;
}

.header-container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.logo {
  text-decoration: none;
  font-size: 16px;
  color: #000;
  font-weight: bold;
  margin-right: 20px;
}

.company-nav {
  margin-top: 10px;
  display: flex;
  gap: 10px;
}

.company-nav a {
  text-decoration: none;
  font-size: 12px;
  color: #000;
  padding: 3px 6px;
  border-bottom: 1px solid transparent;
}

.company-nav a:hover,
.company-nav a:focus {
  border-bottom: 1px solid black;
}

.company-profile {
  display: flex;
  align-items: center;
  gap: 10px;
}

.profile-image {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
</style>
