package com.example.proximabel.user.data.local.entities

data class UserCategoryEntity(
    val _id: String,
    val name: String,
    val user: String,
    val image: String,
    val subCategories: List<String>
)
