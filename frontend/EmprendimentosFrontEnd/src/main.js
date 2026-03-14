import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'unfonts.css'
import App from './App.vue'
import router from './router'
import '@mdi/font/css/materialdesignicons.css'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App)

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light', // <--- É AQUI QUE VOCÊ MUDA
  },
  icons: {
    defaultSet: 'mdi', // This is already the default value - only for display purposes
  },
})

app.use(createPinia())
app.use(vuetify)
app.use(router)

app.mount('#app')





