<template>
  <div class="order-card">
    <div class="status-indicator" :class="statusClass">{{ status }}</div>
    <h2 class="order-id">Order ID: {{ orderId }}</h2>
    <p class="customer-name">Customer Name: {{ customerName }}</p>
    <p class="due-date">Date: {{ formattedDate(date) }}</p>
    <div class="button-group">
      <button class="details-button" @click="viewDetails">Details</button>
      <button class="on-going-button" @click="OnGoing">On Going</button>
      <button class="success-button" @click="Success">Success</button>
      <input type="file" id="fileInput" style="display:none" @change="handleFileSelect" />
      <button type="button" @click="triggerFileInput">Select File</button>
      <span v-if="selectedFile">Selected File: {{ selectedFile.name }}</span>
      <button type="button" @click="uploadFile" :disabled="!selectedFile">Upload</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import dayjs from "dayjs";
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
    customerName: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      selectedFile: null,
    };
  },
  computed: {
    ...mapGetters(['id']),
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
    triggerFileInput() {
      document.getElementById('fileInput').click();
    },
    handleFileSelect(event) {
      this.selectedFile = event.target.files[0];
    },
    uploadFile() {
      if (!this.selectedFile) {
        alert("Please select a file to upload.");
        return;
      }
      
      const formData = new FormData();
      formData.append("orderId", 
        new Blob([JSON.stringify(this.orderId)], {
                type: 'application/json',
            })
        );
      formData.append("file", this.selectedFile);

      fetch('http://localhost:8080/upload', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        console.log('File uploaded successfully:', data);
        alert(data.message || 'File uploaded successfully');
        this.selectedFile = null;
      })
      .catch(error => {
        console.error('Error uploading file:', error);
        alert('Error uploading file');
      });

      const status = "UPLOADED";
      fetch(`http://localhost:8080/orders/order-detail/${this.orderId}/change-status`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
          orderId: this.orderId,
          status: status
        })
      })
      .then(response => response.json())
      .then(data => {
        console.log(data.message);
        alert(data.message || 'Order status updated successfully');
      })
      .catch(error => {
        console.error('Error updating order status:', error);
        alert('Error updating order status');
      });
    },
    formattedDate() {
      return dayjs(this.date).format('DD/MM/YYYY HH:mm:ss');
    },
    viewDetails() {
      console.log(`Viewing details for order ID: ${this.orderId}`);
      this.$router.push({ name: 'order-detail', params: { orderId: this.orderId } });
    },
    OnGoing() {
    const orderId = this.orderId;
    console.log("order: ", orderId);
    const workerId = this.id;
    console.log("worker: ", workerId);
    const status = "ONGOING";
    const workerStatus = "ONGOING";

    fetch('http://localhost:8080/change-order-worker-status', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            orderId: orderId,
            workerId: workerId,
            status: status,
            workerStatus: workerStatus
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data.message);
        alert(data.message || 'Order status updated successfully');
    })
    .catch(error => {
        console.error('Error updating order status:', error);
        alert('Error updating order status');
    });
},
  Success() {
    const orderId = this.orderId;
    console.log("order: ", orderId);
    const status = "DELIVERED";

    fetch(`http://localhost:8080/orders/order-detail/${orderId}/change-status`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            orderId: orderId,
            status: status
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data.message);
        alert(data.message || 'Order status updated successfully');
    })
    .catch(error => {
        console.error('Error updating order status:', error);
        alert('Error updating order status');
    });
  }

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
.due-date,
.customer-name,
.customer-address {
  font-family: "Inter", sans-serif;
  margin: 0;
  margin-bottom :10px;
}

.button-group {
  display: flex;
  gap: 10px;
}

.details-button,
.on-going-button,
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
.on-going-button:hover,
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
