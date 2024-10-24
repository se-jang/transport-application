<template>
  <div class="customer-header">
    <div class="header-container">
      <div class="logo">Transport application</div>
      <nav class="customer-nav">
        <a href="#">My Order</a>
      </nav>
    </div>
    <div class="customer-profile">
      <a href="#">Contact</a>
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
  name: "HeaderCustomer",
};
</script>

<style scoped>
.customer-header {
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

.customer-nav {
  margin-top: 10px;
  margin-left: 100px;
  display: flex;
  gap: 10px;
}

.customer-nav a {
  text-decoration: none;
  font-size: 12px;
  color: #000;
  padding: 3px 6px;
  border-bottom: 1px solid transparent;
}

.customer-nav a:hover,
.customer-nav a:focus {
  border-bottom: 1px solid black;
}

.customer-profile {
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
