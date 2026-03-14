import { defineStore } from "pinia"
import {ref} from 'vue'
import { useApi } from '@/composables/useApi'



export const useEmprendimentosStore = defineStore('empreendimentos', () => {

const api = useApi()
const empreedimentos = ref([])
const isLoading = ref(false)
const error = ref(null)


const createEmpreendimento = async(empredimento)=>{
    isLoading.value=true
    try{
        const {data}= await api.post('/emprendimntos',empredimento)
        return data
    }
    catch (err){
        error.value = err
        throw err
    }
    finally{
        isLoading.value=false
    }
    
}


  const fetchEmpreendimentos = async () => {
    isLoading.value = true
    try {
      const { data } = await api.get('/empreendimento')
      empreedimentos.value = data
    } finally {
       setTimeout(() => {
          isLoading.value = false
          }, 500)
      
    }
  };

  const getEmpreendimentoById = async (id) => {
     isLoading.value = true
      const { data } = await api.get(`/empreendimento/${id}`)
      setTimeout(() => {
             isLoading.value = false
          }, 500)
        
          return data
     
    };


  return {
    empreedimentos,
    isLoading,
    error,
    fetchEmpreendimentos,
    createEmpreendimento,
    getEmpreendimentoById
  }

})