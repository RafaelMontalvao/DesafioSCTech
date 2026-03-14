<template>
  <ContainerDefault >
    
    <v-progress-linear color="secondary" v-if="empreendimentoStore.isLoading" indeterminate />
     
    <v-row v-if="!empreendimentoStore.isLoading" >
      
      <v-col cols="12">
        <v-btn @click="clickForm()"
          prepend-icon="mdi-plus" 
          class="float-right" 
          variant="outlined" 
          color="secondary"> Novo
        </v-btn>
        </v-col>

      <v-col>
        <v-data-table
            :headers="headers"
            elevation="3"
            hover
            :items="empreendimentoStore.empreedimentos"
            item-key="nomeEmpreendimento"
            :disable-sort="true"
            hide-no-data
            hide-default-footer>

            <template v-slot:item.edit="{ item }">
              <v-icon icon="mdi-pencil-outline"size="small"  color="secondary" @click="console.log('cliquei')"></v-icon>
            </template>

             

            <template v-slot:item.materials="{ item }">
              <span v-for="material in item.materials?.slice(0,3)" :key="material.id">
                <v-chip label size="x-small" density="comfortable" variant="outlined" color="secondary" class="ml-1">{{material.rawMaterialName}} - {{ material.quantityNeeded }} unid.</v-chip>
              </span>  
            </template> 

        </v-data-table>
      </v-col>
    </v-row>

    
   
    
  </ContainerDefault> 
</template>

<script setup>

import ContainerDefault from '@/components/ContainerDefault.vue';
import { useEmprendimentosStore } from '@/stores/emprendimentos';
import { onMounted } from 'vue';
import { useRouter } from 'vuetify/lib/composables/router';

const empreendimentoStore = useEmprendimentosStore();
const router = useRouter()




onMounted(()=>{
  empreendimentoStore.fetchEmpreendimentos();
})

const headers = [
    { title: 'Empreendimento', value: 'nomeEmpreendimento' },
    { title: 'Responsável', value: 'nomeResponsavel' },
    { title: 'Municipio', value: 'municipio' },
    { title: 'Segmento de Atuação', value: 'segmentoAtuacao' },
    {title:'Email', value:'email'},
    {title:'Status', value:'status'},
    { title: '', value: 'edit' }
  ];

  function clickForm(id) {
  return router.push(`/empreendimento/${id}`)
}

</script>