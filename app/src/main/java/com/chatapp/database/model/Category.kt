package com.chatapp.database.model

import com.chatapp.R

data class Category(
    val id: String? = null,
    val name: String? = null,
    val imageId: Int? = null
) {
    companion object {
        const val MUSIC = "music"
        const val SPORTS = "sports"
        const val MOVIES = "movies"
        fun fromId(catId: String): Category {
            when (catId) {
                MUSIC -> {
                    return Category(
                        MUSIC,
                        name = "music",
                        imageId = R.drawable.music

                    )
                }
                SPORTS -> {
                    return Category(
                        SPORTS,
                        name = "sports",
                        imageId = R.drawable.sports
                    )
                }
                else -> {
                    return Category(
                        MOVIES,
                        name = "movies",
                        imageId = R.drawable.movies

                    )
                }
            }
        }

        fun getCategoriesList(): List<Category> {
            return listOf(
                fromId(MUSIC),
                fromId(SPORTS),
                fromId(MOVIES)
            )
        }
    }
}
