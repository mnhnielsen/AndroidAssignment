package com.example.androidassignment.Database

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="title") val movieTitle: String,
    @ColumnInfo(name = "description") val description: String,
    @DrawableRes
    @ColumnInfo(name = "image") val image: Int
)