<template>
  <v-app>
    <!-- HeaderComponent에 isLogin과 doLogout 전달 -->
    <HeaderComponent :isLogin="isLogin" @logout="doLogout" />

    <v-main>
      <!-- router-view에도 isLogin 전달 -->
      <router-view :isLogin="isLogin"/>
    </v-main>
  </v-app>
</template>

<script>
import { jwtDecode } from 'jwt-decode';
import HeaderComponent from './components/HeaderComponent.vue'

export default {
  name: 'App',
  components: {
    HeaderComponent
  },
  data() {
    return {
      isLogin: false,
      isAdmin: false,
    };
  },
  created() {
    const token = localStorage.getItem("token");
    if (token){
      const decodeToken = jwtDecode(token);
      this.isAdmin = decodeToken.role === 'ADMIN';
      this.isLogin = true;
    }
  },
  methods: {
    doLogout() {
      localStorage.clear();
      this.isLogin = false;
      window.location.href = "/";
    }
  }
}
</script>