<template>
  <router-view />
</template>

<script setup lang="ts">
import { onMounted } from 'vue';

onMounted(() => {
  const basePath = process.env.BACKEND_BASE_PATH
    ? process.env.BACKEND_BASE_PATH
    : '/';

  const link = document.createElement('link');
  link.rel = 'stylesheet';
  link.type = 'text/css';
  link.href = basePath + 'map/webjars/leaflet/1.8.0/leaflet.css';
  document.head.appendChild(link);

  const script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = basePath + 'map/webjars/leaflet/1.8.0/leaflet.js';
  document.head.appendChild(script);
});
</script>
