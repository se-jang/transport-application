<template>
  <div class="order-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="order-id">Order ID: {{ orderId }}</h2>
    <p class="due-date">Due Date: {{ date }}</p>
    <div class="button-group">
      <button class="details-button">Details</button>
      <button class="almost-there-button">Almost There</button>
      <button class="success-button">Success</button>
      <input type="file" id="fileInput" style="display:none" />
      <button type="button" onclick="document.getElementById('fileInput').click()">Upload</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderWorkerCard",
  props: {
    status: {
      type: String,
      default: "ongoing",
    },
    orderId: {
      type: String,
      required: true,
    },
    date: {
      type: String,
      required: true,
    },
  },
  computed: {
    statusClass() {
      switch (this.status) {
        case "checked":
          return "status-checked";
        case "ongoing":
          return "status-ongoing";
        case "delivered":
          return "status-delivered";
        default:
          return "";
      }
    },
  },
  methods: {
    uploadFile() {
      const formData = new FormData();
      const fileInput = document.getElementById('fileInput');
      const file = fileInput.files[0];

      if (file) {
        formData.append("file", file);

        fetch('/upload', {
          method: 'POST',
          body: formData
        })
            .then(response => response.json())
            .then(data => {
              console.log('File uploaded successfully:', data);
            })
            .catch(error => {
              console.error('Error uploading file:', error);
            });
      } else {
        alert("Please select a file to upload.");
      }
    },
  }
};
</script>

<style scoped>
.order-card {
  position: relative;
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-top: 15px;
}

.order-id,
.due-date {
  font-family: "Inter", sans-serif;
  margin: 0;
  margin-bottom: 10px;
}

.button-group {
  display: flex;
  gap: 10px;
}

.details-button,
.almost-there-button,
.success-button {
  padding: 5px 10px;
  font-family: "Inter", sans-serif;
  background-color: gray;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: auto;
}

.details-button:hover,
.almost-there-button:hover,
.success-button:hover {
  background-color: #4b4b4b;
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

.status-checked {
  font-family: "Inter", sans-serif;
  background-color: red;
}

.status-ongoing {
  font-family: "Inter", sans-serif;
  background-color: orange;
}

.status-delivered {
  font-family: "Inter", sans-serif;
  background-color: green;
}
</style>
