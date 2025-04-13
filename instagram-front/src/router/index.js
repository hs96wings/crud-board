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
    { path: '/manage', name: 'ManageAddVideo', component: ManageAddVideo, meta: { requireAuth: true } },
    { path: '/video/:id', name: 'videoDetail', component: VideoDetail },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 네비게이션 가드
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem("token")

    if (to.meta.requireAuth && !token) {
        next('/login') // 로그인이 되어 있지 않으면 로그인 페이지로 이동
    } else {
        next() // 통과
    }
})

export default router