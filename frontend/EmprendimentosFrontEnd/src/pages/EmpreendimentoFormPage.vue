<script setup lang="ts">
import ContainerDefault from '@/components/ContainerDefault.vue';
import { useEmprendimentosStore } from '@/stores/emprendimentos';
import { onMounted, ref } from 'vue'
import { useNotification } from '@/composables/useNotification';
import { useRoute } from 'vue-router'
import { useRouter } from 'vuetify/lib/composables/router';
import { sortItems } from 'vuetify/lib/components/VDataTable/composables/sort';
import { useMunicipioStore } from '@/stores/municipios';



const route = useRoute()
const router = useRouter()

const store = useEmprendimentosStore()
const municipioStore = useMunicipioStore()
const notification = useNotification()
const isLoading = ref(true)  
const empreendimentoId = ref(null)
const form = ref({
    nomeEmpreendimento:'',
    nomeResponsavel:'',
    municipio:'',
    segmentoAtuacao:'',
    email:''

})

// const MunicipioList = [
//     {title:'Florianópolis', id: 1},
//     {title:'Chapecó', id: 2},
//     {title:'Joinvilles', id: 3},
//     {title:'Tubarão', id: 4},

// ]

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
        municipioStore.fetchmunicipios();
        console.log('id recebido', empreendimentoId.value)

        if(empreendimentoId.value > 0){
            const empreendimento = await store.getEmpreendimentoById(empreendimentoId.value)
            
            if(empreendimento){
                Object.assign(form.value, empreendimento)
                console.log('form populado', form.value)
            }
        }
        console.log('form zerado', form.value)

    }
    catch (err) {
        console.error('Erro ao carregar produto:', err)
    } finally {
    isLoading.value = false
    }

})

</script>

<template>



    <ContainerDefault>

        <v-container class="pa-sm-6 pa-2">

        <v-progress-linear color="secondary" v-if="store.isLoading" indeterminate />
     

        <v-row v-if="!store.isLoading">
            <v-col cols="12" sm="6">
            <v-text-field
                v-model="form.nomeEmpreendimento"
                prepend-inner-icon=" mdi-domain"
                label="Empreendimento"
                variant="outlined"
                density="compact"
                hide-details="auto"
                
            ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
                <v-select
                    v-model="form.municipio"
                    :items="municipioStore.municipios"
                    item-title="nome"
                    item-value="id"
                    variant="outlined"
                    density="compact"
                    hide-details="auto"
                    prepend-inner-icon="mdi-city-variant"
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
                    density="compact"
                    hide-details="auto">
                </v-text-field>
            </v-col>
        </v-row>

        

        </v-container>
    </ContainerDefault>
</template>