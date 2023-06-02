package com.kalabekov.frontend.auth

object StaticTokenManager {
        private var token: String? = null

        fun saveToken(token: String) {
            StaticTokenManager.token = token
        }

        fun getToken(): String? {
            return token
        }

}