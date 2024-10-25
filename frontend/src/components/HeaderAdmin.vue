<template>
  <div class="admin-header">
    <div class="header-container">
      <div class="logo">Transport application</div>
      <nav class="admin-nav">
        <router-link to="/orders">Order</router-link>
        <router-link to="/worker-list">Transport worker</router-link>
        <router-link to="/user-list">User</router-link>
        <router-link to="/create-user">Create User</router-link>
      </nav>
    </div>
    <div class="admin-profile">
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
  name: "HeaderAdmin",
};
</script>

<style scoped>
.admin-header {
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

.admin-nav {
  margin-top: 10px;
  margin-left: 100px;
  display: flex;
  gap: 10px;
}

.admin-nav a {
  text-decoration: none;
  font-size: 12px;
  color: #000;
  padding: 3px 6px;
  border-bottom: 1px solid transparent;
}

.admin-nav a:hover,
.admin-nav a:focus {
  border-bottom: 1px solid black;
}

.admin-profile {
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
