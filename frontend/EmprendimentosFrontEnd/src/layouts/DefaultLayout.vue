<template>
    <v-card>
        <v-layout>
            <v-app-bar color="primary" elevation="3">
                <template v-slot:prepend>
                    <v-app-bar-nav-icon @click.stop="drawer=!drawer"></v-app-bar-nav-icon>
                </template>

                <v-app-bar-title>Desafio Emprendimentos</v-app-bar-title>
                </v-app-bar>

                <v-navigation-drawer v-model="drawer">
                    <v-list :items="items"></v-list>
                    
                </v-navigation-drawer>

                <v-main style="height:100dvh;">
                    <router-view/>
                </v-main>
            
        </v-layout>
    </v-card>


</template>

<script setup>
const drawer = ref(true)

const items = [
    {
        title : 'Empreendimentos',
        value : 'empreendimentos'
    }
]
</script>