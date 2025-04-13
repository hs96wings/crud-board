import { createRouter, createWebHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue"
import VideoTest from "@/views/VideoTest.vue"
import LoginPage from "@/views/LoginPage.vue"
import ManagePage from "@/views/ManagePage.vue"

const routes = [
    { path: '/', name: 'HomePage', component: HomePage },
    { path: '/video', name: 'VideoTest', component: VideoTest },
    { path: '/login', name: 'LoginPage', component: LoginPage },
    { path: '/manage', name: 'ManagePage', component: ManagePage }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router