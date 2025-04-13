<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="4" md="6">
                <v-card>
                    <v-card-title class="text-h5 text-center">영상</v-card-title>
                    <v-card-text>
                        <v-respective aspect-ratio="16/9">
                            <iframe
                                src="https://drive.google.com/file/d/1UFrMr297g0-OAPwoKIPSU4LVAUSLoDbC/preview"
                                width="100%"
                                height="700"
                                allowfullscreen
                            ></iframe>
                        </v-respective>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

export default {
    name: 'VideoTest',
}
</script>