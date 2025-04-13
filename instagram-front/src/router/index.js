import { createRouter, createWebHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue"
import LoginPage from "@/views/LoginPage.vue"
import ManageAddVideo from "@/views/ManageAddVideo.vue"
import VideoList from "@/views/VideoList.vue"
import VideoDetail from "@/views/VideoDetail.vue"

const routes = [
    { path: '/', name: 'HomePage', component: HomePage },
    { path: '/video/list', name: 'VideoList', component: VideoList },
    { path: '/login', name: 'LoginPage', component: LoginPage },
    { path: '/manage', name: 'ManageAddVideo', component: ManageAddVideo },
    { path: '/video/:id', name: 'videoDetail', component: VideoDetail },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router