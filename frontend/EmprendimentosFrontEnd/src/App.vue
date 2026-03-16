<script setup>
import { RouterLink, RouterView } from 'vue-router'
import {useNotification} from'@/composables/useNotification';

const notification = useNotification() 

</script>

<template> 
  <v-app>
    <RouterView />
    <v-snackbar v-model=" notification.show.value"
      :color="notification.color.value"
      :timeout = "notification.timeout.value">
      {{ notification.message.value }}
    </v-snackbar>

  </v-app> 

  
</template>




