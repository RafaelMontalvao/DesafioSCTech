import { defineStore } from "pinia"
import {ref} from 'vue'
import { useApi } from '@/composables/useApi'



export const useMunicipioStore = defineStore('muncipios', () => {

const api = useApi()
const municipios = ref([])
const isLoading = ref(false)
const error = ref(null)




  const fetchmunicipios = async () => {
    isLoading.value = true
    try {
      const { data } = await api.get('/municipios/municipiossc')
      municipios.value = data
    } finally {
       setTimeout(() => {
          
          }, 500)
      
    }
  };

  


  return {
    municipios,
    fetchmunicipios,
    isLoading,
    error,
    
  }

})