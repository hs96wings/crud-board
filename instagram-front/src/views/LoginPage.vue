<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="4" md="6">
                <v-card>
                    <v-card-title class="text-h5 text-center">로그인</v-card-title>
                    <v-card-text>
                        <v-form @submit.prevent="doLogin">
                            <v-text-field
                                label="아이디"
                                v-model="userid"
                                required>
                            </v-text-field>
                            <v-text-field
                                label="비밀번호"
                                v-model="password"
                                type="password"
                                required>
                            </v-text-field>
                            <v-btn type="submit" color="primary" block>로그인</v-btn>
                        </v-form>
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
            userid: "",
            password: ""
        }
    },
    methods: {
        async doLogin() {
            const loginData = { userid: this.userid, password: this.password }
            const response = await axios.post(`${process.env.VUE_APP_API_BASE_URL}/member/doLogin`, loginData)
            const token = response.data.token
            localStorage.setItem("token", token)
            window.location.href = "/"
        }
    }
}
</script>