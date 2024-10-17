<template>
    <div class="page-container">
      <header>
        <component :is="headerComponent"></component>
      </header>
  
      <div class="main-container">
        <div class="worker-container">
          <h2 class="worker-title">Workers</h2>
          <WorkerCard
            v-for="worker in workers"
            :key="worker.id"
            :status="worker.status"
            :workerId="worker.id"
            :name="worker.name"
          />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import HeaderAdmin from "../components/HeaderAdmin.vue";
  import WorkerCard from "../components/WorkerCard.vue";
  
  export default {
    data() {
      return {
        role: "admin", 
        workers: [
          { id: "7", name: "Cristiano Ronaldo", status: "available" },
          { id: "11", name: "Neymar", status: "ongoing" },
          { id: "10", name: "Lionel Messi", status: "unavailable" },
          { id: "24", name: "Andre Onana", status: "available" },
        ],
      };
    },
    computed: {
      headerComponent() {
        return HeaderAdmin;
      },
    },
    components: {
      WorkerCard,
    },
  };
  </script>
  
  <style scoped>
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
  }
  
  .worker-title {
    font-family: "Inter", sans-serif;
    font-size: 24px;
    font-weight: bold;
    color: #333;
    margin-bottom: 20px;
    text-align: left;
  }
  
  .worker-container {
    background-color: #e6e6e6;
    padding: 20px;
    width: 80%;
    max-width: 1200px;
    overflow-y: auto;
    height: 100%;
  }
  </style>
  