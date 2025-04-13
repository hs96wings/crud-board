import { createRouter, createWebHistory } from 'vue-router'
import HomePage from "@/views/HomePage.vue"
import LoginPage from "@/views/LoginPage.vue"
import ManagePage from "@/views/ManagePage.vue"
import VideoList from "@/views/VideoList.vue"

const routes = [
    { path: '/', name: 'HomePage', component: HomePage },
    { path: '/video/list', name: 'VideoList', component: VideoList },
    { path: '/login', name: 'LoginPage', component: LoginPage },
    { path: '/manage', name: 'ManagePage', component: ManagePage }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router