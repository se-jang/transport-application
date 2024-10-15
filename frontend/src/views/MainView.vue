<template>
  <div>
    <header>
      <component :is="headerComponent"></component>
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
import HeaderAdmin from '../components/HeaderAdmin.vue';
import HeaderWorker from '../components/HeaderWorker.vue';
import HeaderCompany from '../components/HeaderCompany.vue';
import HeaderCustomer from '../components/HeaderCustomer.vue';

export default {
  data() {
    return {
      role: 'admin',
    };
  },
  computed: {
    headerComponent() {
      switch (this.role) {
        case 'admin':
          return HeaderAdmin;
        case 'worker':
          return HeaderWorker;
        case 'company':
          return HeaderCompany;
        case 'customer':
          return HeaderCustomer;
        default:
          return null;
      }
    },
  },
};
</script>

<style scoped>
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
</style>
