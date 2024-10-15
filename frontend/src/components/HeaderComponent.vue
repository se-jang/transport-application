<template>
  <div>
    <HeaderAdmin v-if="role === 'admin'" />
    <HeaderCustomer v-else-if="role === 'customer'" />
    <HeaderCompany v-else-if="role === 'company'" />
    <HeaderWorker v-else-if="role === 'worker'" />
  </div>
</template>

<script>
import HeaderAdmin from '..HeaderAdmin.vue';
import HeaderCustomer from "..HeaderCustomer.vue";
import HeaderCompany from "..HeaderCompany.vue";
import HeaderWorker from "..HeaderWorker.vue";

export default {
  props: ["role"],
  components: {
    HeaderAdmin,
    HeaderCustomer,
    HeaderCompany,
    HeaderWorker,
  },
};
</script>
