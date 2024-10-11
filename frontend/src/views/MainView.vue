<template>
  <div>
    <header>
      <div v-if="role === 'admin'" class="admin-header">
        <div class="logo">Transport application</div>
        <nav class="admin-nav">
          <a href="#">Order</a>
          <a href="#">Transport worker</a>
          <a href="#">User</a>
          <a href="#">Product</a>
          <a href="#">Create User</a>
          <a href="#">Create Bill</a>
          <a href="#">User Report</a>
        </nav>
        <div class="admin-profile">
          <a href="#">Contact</a>
          <img src="https://via.placeholder.com/40" alt="Profile" class="profile-image" />
        </div>
      </div>

      <div v-else-if="role === 'customer'" class="customer-header">
        <h1>Customer</h1>
      </div>

      <div v-else-if="role === 'company'" class="company-header">
        <h1>Company</h1>
      </div>

      <div v-else-if="role === 'worker'" class="worker-header">
        <h1>Worker</h1>
      </div>
    </header>

    <div class="main-container">
      <section class="content-section">
        <h1 class="title">Get Start!</h1>
        <p class="description">
          Start your journey with us today and experience stress-free
          transportation.
        </p>
        <button class="get-start-button">Get Start</button>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      role: "admin",
    };
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap');

body {
  font-family: 'Inter', sans-serif;
}

.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 10px 20px;
  border-bottom: 2px solid #e0e0e0;
}

.customer-header, .company-header, .worker-header {
  background-color: #f8f8f8;
  padding: 20px;
  text-align: center;
  border-bottom: 2px solid #e0e0e0;
}

.logo {
  font-size: 20px;
  font-weight: bold;
}

.admin-nav {
  display: flex;
  gap: 15px;
}

.admin-nav a {
  text-decoration: none;
  font-size: 14px;
  color: #000;
  padding: 5px;
  border-bottom: 2px solid transparent;
}

.admin-nav a:hover,
.admin-nav a:focus {
  border-bottom: 2px solid black;
}

.admin-profile {
  display: flex;
  align-items: center;
  gap: 10px;
}

.profile-image {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.main-container {
  background-color: rgb(217, 217, 217);
  height: calc(100vh - 100px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding-left: 50px;
}

.content-section {
  text-align: left;
  margin-bottom: 40px;
}

.title {
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 10px;
}

.description {
  font-size: 18px;
  color: #333;
  margin-bottom: 20px;
}

.get-start-button {
  padding: 10px 20px;
  background-color: rgb(230, 230, 230);
  border: 2px solid black;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  color: #333;
}

.get-start-button:hover {
  background-color: #f0f0f0;
}

.grid-section {
  display: flex;
  gap: 20px;
}

.grid-item {
  width: 200px;
  height: 100px;
  background-color: #b0b0b0;
  border-radius: 5px;
}
</style>
