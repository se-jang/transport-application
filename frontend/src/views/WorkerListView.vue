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
import axios from "axios";
import { mapGetters } from "vuex";
import HeaderAdmin from "../components/HeaderAdmin.vue";
import HeaderWorker from "../components/HeaderWorker.vue";
import HeaderCompany from "../components/HeaderCompany.vue";
import WorkerCard from "../components/WorkerCard.vue";

export default {
  data() {
    return {
      workers: [],
    };
  },
  created() {
    this.fetchWorkers();
  },
  computed: {
    ...mapGetters(["userRole", "username"]),
    headerComponent() {
      switch (this.userRole) {
        case "ADMIN":
          return HeaderAdmin;
        case "WORKER":
          return HeaderWorker;
        case "USER":
          return HeaderCompany;
        default:
          return null;
      }
    },
  },
  methods: {
    async fetchWorkers() {
      try {
        const response = await axios.get("http://localhost:8080/transportation-workers");
        console.log("Response data:", response.data);

        // ตรวจสอบว่า response.data เป็นอาเรย์หรือไม่
        if (Array.isArray(response.data)) {
          this.workers = response.data.map(worker => ({
            id: worker.id,
            name: worker.name,
            status: worker.status.toLowerCase(),
          }));
        } else {
          console.warn("Expected an array but got:", response.data);
          this.workers = [];
        }
      } catch (error) {
        console.error("Error fetching workers:", error);
      }
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
  padding-top: 0%;
  padding-bottom: 0%;
  flex: 1;
}

.worker-title {
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
  min-height: 100%;
}
</style>
  