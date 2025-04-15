<template>
  <v-app-bar app dark>
      <v-container>
          <v-row align="center">
              <v-col class="d-flex justify-start">
                  <v-btn :to="{path: '/video/list'}">영상 목록</v-btn>
                  <v-btn :to="{path: '/photo'}">사진 목록</v-btn>
              </v-col>
              <v-col class="text-center">
                  <v-btn :to="{path: '/'}">Home</v-btn>
              </v-col>
              <v-col class="d-flex justify-end">
                  <v-btn v-if="isLogin" :to="{path: '/manage'}">관리</v-btn>
                  <v-btn v-if="!isLogin" :to="{path: '/login'}">로그인</v-btn>
                  <v-btn v-if="isLogin" @click="doLogout">로그아웃</v-btn>
              </v-col>
          </v-row>
      </v-container>
  </v-app-bar>
</template>

<script>
export default {
    props: {
        isLogin: {
            type: Boolean,
            required: true
        }
    },
    emits: ['logout'],
    methods: {
        doLogout() {
            this.$emit('logout'); // 부모에게 로그아웃 알림
        }
    }
}
</script>