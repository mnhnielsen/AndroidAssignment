package com.example.androidassignment.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE :movieId = id")
    fun getMovieById(movieId: Int): Movie

    @Insert
    fun insert(movie: Movie)
}