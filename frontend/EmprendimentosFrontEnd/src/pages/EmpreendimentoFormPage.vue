<script setup lang="ts">
import ContainerDefault from '@/components/ContainerDefault.vue';
import { useEmprendimentosStore } from '@/stores/emprendimentos';
import { onMounted, ref } from 'vue'
import { useNotification } from '@/composables/useNotification';
import { useRoute } from 'vue-router'
import { useRouter } from 'vue-router'

import { useMunicipioStore } from '@/stores/municipios';



const route = useRoute()
const router = useRouter()

const store = useEmprendimentosStore()
const municipioStore = useMunicipioStore()
const notification = useNotification()
const isLoading = ref(true)  
const empreendimentoId = ref(null)
const formEmpreendimento = ref(null)
const dialogModel = ref(false)
const dialogType = ref(null)
const isSaving = ref(false)
const form = ref({
    id:null,
    nomeEmpreendimento:'',
    nomeResponsavel:'',
    municipio:'',
    segmentoAtuacao:'',
    email:'',
    status:''

})

const rules = {
  required: v => !!v || 'Campo obrigatório',
  min3: v => v?.length >= 3 || 'Min 3 caracteres',
  uniqueName: v => {
    if (!v) return true
    return !store.empreendimentoExiste(v, form.value.id) || 'Empreendimento já existe'
  }
}

 


const statusList = [
    {title:'Ativo', value:'ATIVO'},
    {title:'Inativo', value:'INATIVO'}

]


const segmentosAtuacaoList = [
    {title: 'Tecnologia' , value:'TECNOLOGIA'},
    {title: 'Comércio', value:'COMERCIO'},
    {title: 'Indústria', value:'INDUSTRIA'},
    {title: 'Serviço', value:'SERVICO'},
    {title: 'Saúde', value:'SAUDE'},
    {title: 'Agronegócio', value:'AGRONEGOCIO'},
]



onMounted(async ()=> {
  try {

    empreendimentoId.value = Number(route.params.id)
    console.log('empreendimentoValue',empreendimentoId.value)

    await Promise.all([
      municipioStore.fetchmunicipios(),

      // carrega só se não tiver no store
      !store.empreedimentos.length ? store.fetchEmpreendimentos() : null
    ])

    if (empreendimentoId.value > 0) {
      const empreendimento = await store.getEmpreendimentoById(empreendimentoId.value)

      if (empreendimento) {
        Object.assign(form.value, empreendimento)
      }
    }

  } catch (err) {
    console.error('Erro ao carregar empreendimento:', err)
  } finally {
    isLoading.value = false
  }
})

async function clickSendForm() {

  try{

    const { valid } = await formEmpreendimento.value.validate()
    if (!valid) return

    const formSend = {...form.value}

    if (empreendimentoId.value > 0) {
      await store.editEmpreendimento(empreendimentoId.value, formSend)
      console.log('form edit', formSend )
      notification.success('EMPREENDIMENTO ATUALIZADO')
    } else {
      await store.createEmpreendimento(formSend)
      notification.success('EMPREENDIMENTO CRIADO COM SUCESSO')
      console.log('form create', formSend )
    }
    

     setTimeout(() => {
             router.back()
           }, 500)

  }

  catch (error) {

  const msg =
    error?.response?.data?.erros?.[0] ||
    error?.response?.data?.message ||
    error?.message ||
    'Erro ao salvar Empreendimento'

  notification.error(msg)
}

}


function clickDeleteEmpreendimento () {

  dialogModel.value = true
  dialogType.value = 'deleteEmpreendimento'
}

async function deleteEmpreendimento (){
  try{
    await store.deleteEmpreendimento(empreendimentoId.value)
    notification.success('Empreendimento Deletado')
    dialogModel.value = false
  }
  catch(error) {
    const msg = 
    error?.response?.data?.errors?.[0] || 
    error?.response.data.message ||
    error?.message || 'Error Delting product'

    notification.error(msg)
  }
    router.back()


}



</script>

<template>
    


    <ContainerDefault > 
        <v-progress-linear color="secondary" v-if="store.isLoading" indeterminate />   

        <div v-if="!store.isLoading">
            <v-form  class="mt-4" ref="formEmpreendimento" validate-on="blur" @submit.prevent>
                <v-row >
                    <v-col cols="12" sm="6">
                    <v-text-field
                        v-model="form.nomeEmpreendimento" 
                        prepend-inner-icon=" mdi-domain"
                        label="Empreendimento"
                        variant="outlined"
                        density="compact"
                        hide-details="auto"
                        :rules="[rules.uniqueName, rules.required]"    
                    ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                        <v-select
                            v-model="form.municipio"
                            :items="municipioStore.municipios"
                            item-title="nome"
                            item-value="nome"
                            variant="outlined"
                            density="compact"
                            hide-details="auto"
                            prepend-inner-icon="mdi-city-variant"
                            :rules="[ rules.required]"    
                            label="Município">
                            
                        </v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                        <v-text-field
                            v-model="form.nomeResponsavel"
                            prepend-inner-icon="mdi-account"
                            label="Responsável"
                            variant="outlined"
                            density="compact"
                            :rules="[ rules.required]" 
                            hide-details="auto">
                        </v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6">
                        <v-select
                            v-model="form.segmentoAtuacao"
                            :items="segmentosAtuacaoList"
                            item-title="title"
                            item-value="value"
                            variant="outlined"
                            density="compact"
                            :rules="[ rules.required]" 
                            hide-details="auto"
                            prepend-inner-icon="mdi-city-variant"
                            label="Segmento de Atuação">
                        </v-select>
                    </v-col>
                        <v-col cols="12" sm="6">
                        <v-text-field
                            v-model="form.email"
                            prepend-inner-icon="mdi-email"
                            label="email"
                            variant="outlined"
                            :rules="[ rules.required]" 
                            density="compact"
                            hide-details="auto">
                        </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                        <v-select
                            v-model="form.status"
                            :items="statusList"
                            item-title="title"
                            item-value="value"
                            variant="outlined"
                            :rules="[ rules.required]" 
                            density="compact"
                            hide-details="auto"
                            hide-selected
                            prepend-inner-icon="mdi-city-variant"
                            label="Status">
                        </v-select>
                    </v-col>
                </v-row>
            </v-form> 

            
            <v-footer float-end class="px-0">
                <v-toolbar density="comfortable" width="100%"  class="bg-transparent pr-0 align-end">
                <v-btn v-if="empreendimentoId> 0" color="error" variant="tonal" density="comfortable"  @click="(clickDeleteEmpreendimento)">Excluir</v-btn>
                <v-spacer></v-spacer>
                <v-btn color="grey" variant="tonal"  density="comfortable" @click="router.back()">Cancelar</v-btn>
                <v-btn color="success" class="ml-2" density="comfortable" variant="tonal"   @click="(clickSendForm)">Salvar</v-btn>
            </v-toolbar>


            </v-footer>

            

        </div>

          <v-dialog
            v-model="dialogModel"
            width="auto"
            >
            <v-card
                max-width="400"
                prepend-icon="mdi-delete"
                title=" Excluir Empreediemento?"
                class="bg-secondary"
                >
                <template v-slot:actions>
                    <v-btn
                    text="Cancelar"
                    @click="dialogModel = false"
                ></v-btn>
                <v-btn
                    text="Ok"
                    @click="deleteEmpreendimento()"
                ></v-btn>

                </template>
            </v-card>
            </v-dialog>
    </ContainerDefault>


</template>