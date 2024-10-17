<template>
  <div class="worker-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="worker-id">Worker ID: {{ workerId }}</h2>
    <p class="worker-name">Name: {{ name }}</p>
    <button class="details-button">Details</button>
  </div>
</template>

<script>
export default {
  name: "WorkerCard",
  props: {
    status: {
      type: String,
      required: true,
    },
    workerId: {
      type: String,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
  },
  computed: {
    statusClass() {
      switch (this.status) {
        case "available":
          return "status-available";
        case "ongoing":
          return "status-ongoing";
        case "unavailable":
          return "status-unavailable";
        default:
          return "";
      }
    },
  },
};
</script>

<style scoped>
.worker-card {
  position: relative;
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 15px;
}

.worker-id,
.worker-name {
  font-family: "Inter", sans-serif;
  margin: 0;
  margin-bottom: 10px;
}

.details-button {
  padding: 5px 10px;
  font-family: "Inter", sans-serif;
  background-color: gray;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
}

.status-indicator {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 5px 10px;
  font-size: 14px;
  font-weight: bold;
  color: white;
  border-radius: 5px;
}

.status-available {
  font-family: "Inter", sans-serif;
  background-color: green;
}

.status-ongoing {
  font-family: "Inter", sans-serif;
  background-color: orange;
}

.status-unavailable {
  font-family: "Inter", sans-serif;
  background-color: red;
}
</style>
