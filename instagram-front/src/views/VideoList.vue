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
        <!-- 페이지네이션 -->
        <div class="text-center">
            <v-pagination
                v-model="currentPage"
                :length="totalPages"
                :total-visible="totalPages"
                rounded="circle"
                @update:modelValue="handlePageChange"
                color="primary">
            </v-pagination>
        </div>
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
            videoList: [],
            currentPage: 1, // vue는 1부터, spring은 0부터이므로 변환 필요
            totalPages: 0
        }
    },
    async created() {
        const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/api/video/list`)
        this.videoList = response.data.content;
    },
    mounted() {
        this.fetchVideos();
    },
    methods: {
        async deleteVideo(videoId) {
            await axios.delete(`${process.env.VUE_APP_API_BASE_URL}/api/video/delete/${videoId}`)
            window.location.reload();
        },
        async fetchVideos() {
            const page = this.currentPage - 1 // spring은 0부터 시작
            const size = 10;

            const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/api/video/list?page=${page}&size=${size}`)
            this.videoList = response.data.content;
            this.totalPages = response.data.totalPages;
        },
        handlePageChange(page) {
            if (page < 1 || page > this.totalPages) return; // 유효하지 않으면 무시
            this.currentPage = page;
            this.fetchVideos(page);
        }
    }
}
</script>