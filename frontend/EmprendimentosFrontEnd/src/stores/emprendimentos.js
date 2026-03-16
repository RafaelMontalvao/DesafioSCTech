import { defineStore } from "pinia"
import {ref} from 'vue'
import { useApi } from '@/composables/useApi'



export const useEmprendimentosStore = defineStore('empreendimentos', () => {

const api = useApi()
const empreedimentos = ref([])
const isLoading = ref(false)
const error = ref(null)


const createEmpreendimento = async(empreedimento)=>{
    isLoading.value=true
    try{
        const {data}= await api.post('/empreendimento',empreedimento)
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

const editEmpreendimento = async (id, e) => {
      
      try {
        const { data } = await api.put(`/empreendimento/${id}`, e)
        return data
      } catch (err) {
        error.value = err
        throw err
      } 
    };

const empreendimentoExiste = (nome, id = null) => {
  return empreedimentos.value.some(e => 
    e.nomeEmpreendimento?.toLowerCase() === nome.toLowerCase() &&
    e.id !== id
  )
}


  const fetchEmpreendimentos = async () => {
    isLoading.value = true
    try {
      const { data } = await api.get('/empreendimento')
      empreedimentos.value = data
    } catch(err) {
        error.value = err
      }
      finally {
       
            isLoading.value = false
            
        
      }
    };

  const getEmpreendimentoById = async (id) => {
     isLoading.value = true
      const { data } = await api.get(`/empreendimento/${id}`)
      
             isLoading.value = false
        
          return data
     
    };

    
   const deleteEmpreendimento = async (id) => {
    isLoading.value = true

    try {
      await api.delete(`/empreendimento/${id}`)
    } catch (err) {
      error.value = err
      throw err
    } finally {
      isLoading.value = false
    }
  }


  return {
    empreedimentos,
    isLoading,
    error,
    empreendimentoExiste,
    fetchEmpreendimentos,
    deleteEmpreendimento,
    createEmpreendimento,
    editEmpreendimento,
    getEmpreendimentoById
  }

})