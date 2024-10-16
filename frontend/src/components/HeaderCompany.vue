<template>
  <div class="company-header">
    <div class="header-container">
      <div class="logo">Transport application</div>
      <nav class="company-nav">
        <a href="#">Create Order</a>
        <a href="#">My Order</a>
      </nav>
    </div>
    <div class="company-profile">
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
  name: "HeaderCompany",
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

.company-nav {
  margin-top: 10px;
  margin-left: 100px;
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
