package com.android.ucast.Model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class User(

	@field:SerializedName("telpone")
	val telpone: String? = null,

	@field:SerializedName("api_token")
	val apiToken: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String? = null,

	@field:SerializedName("firebase_token")
	val firebaseToken: Any? = null,

	@field:SerializedName("is_verified")
	val isVerified: String? = null,

	@field:SerializedName("firebase_token_web")
	val firebaseTokenWeb: Any? = null,

	@field:SerializedName("is_deleted")
	val isDeleted: Int? = null,

	@field:SerializedName("img_profile")
	val imgProfile: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
