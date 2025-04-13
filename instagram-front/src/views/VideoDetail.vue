<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="4" md="6">
                <v-card>
                    <v-card-title class="text-h5 text-center">{{ title }}</v-card-title>
                    <v-card-text>
                        <v-respective aspect-ratio="16/9">
                            <iframe
                                v-if="videoId"
                                :src="`https://drive.google.com/file/d/${videoId}/preview`"
                                width="100%"
                                height="750"
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
import axios from 'axios'

export default {
    data() {
        return {
            id: this.$route.params.id,
            title: "",
            videoId: ""
        }
    },
    async created() {
        const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/api/video/${this.id}`)
        this.title = response.data.title
        this.videoId = response.data.videoId
        console.log(this.videoId)
    }
}
</script>