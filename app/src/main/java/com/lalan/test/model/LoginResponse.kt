package com.lalan.test.model

data class LoginResponse(val data: String?, val meta: Meta)

data class Meta(val message: String)