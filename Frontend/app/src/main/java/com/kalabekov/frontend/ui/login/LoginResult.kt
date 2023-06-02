package com.kalabekov.frontend.ui.login
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    var regStatus:Boolean = false
)