package com.dicoding.mysimplelogin

class UserRepository(private val sesi: com.riopermana.core.SessionManager) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(sesi: com.riopermana.core.SessionManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(sesi)
            }
    }

    fun loginUser(username: String) {
        sesi.createLoginSession()
        sesi.saveToPreference(com.riopermana.core.SessionManager.KEY_USERNAME, username)
    }

    fun getUser() = sesi.getFromPreference(com.riopermana.core.SessionManager.KEY_USERNAME)

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()
}