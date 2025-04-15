<template>
    <v-container>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-title class="text-center text-h5">영상 목록</v-card-title>
                    <v-card-text>
                        <v-table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>제목</th>
                                    <th>업로드 날짜</th>
                                    <th v-if="isLogin" colspan="2">관리 메뉴</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="video in videoList" :key="video.id">
                                    <td>{{ video.id }}</td>
                                    <td><v-btn :to="{path: `/video/${video.id}`}">{{ video.title }}</v-btn></td>
                                    <td>{{ video.uploadedAt }}</td>
                                    <td v-if="isLogin"><v-btn :to="{path: `/manage/update/${video.id}`}">수정</v-btn></td>
                                    <td v-if="isLogin"><v-btn @click="deleteVideo(video.id)">삭제</v-btn></td>
                                </tr>
                            </tbody>
                        </v-table>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'

export default {
    props: {
        isLogin: {
            type: Boolean,
            default: false,
            required: true
        }
    },
    data() {
        return {
            videoList: []
        }
    },
    async created() {
        const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/api/video/list`)
        this.videoList = response.data;
    },
    methods: {
        async deleteVideo(videoId) {
            await axios.delete(`${process.env.VUE_APP_API_BASE_URL}/api/video/delete/${videoId}`)
            window.location.reload();
        }
    }
}
</script>