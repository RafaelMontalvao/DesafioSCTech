<template>
  <v-container>

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
    
  </v-container fluid class="pa-0">
</template>

<script setup>

import { useEmprendimentosStore } from '@/stores/emprendimentos';
import { onMounted } from 'vue';

const empreendimentoStore = useEmprendimentosStore();




onMounted(()=>{
  empreendimentoStore.fetchEmpreendimentos();
})

const headers = [
    { title: 'Empreendimento', value: 'nomeEmpreendimento' },
    { title: 'Responsavel', value: 'nomeResponsavel' },
    { title: 'Municipio', value: 'municipio' },
    { title: 'Segmento de Atuação', value: 'segmentoAtuacao' },
    {title:'Email', value:'email'},
    {title:'Status', value:'status'},
    { title: '', value: 'edit' }
  ];
</script>