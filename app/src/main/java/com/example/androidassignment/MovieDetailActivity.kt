package com.example.androidassignment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidassignment.Database.MovieDatabase

class MovieDetailActivity : AppCompatActivity() {
    lateinit var db: MovieDatabase

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.moviedetails)
        db = MovieDatabase.getAppDatabase(this)!!

        title = findViewById(R.id.movieDetailTitle)
        image = findViewById(R.id.movieDetailsImage)
        description = findViewById(R.id.movieDetailDescription)

        val clickedMovie: Int = intent.getIntExtra("Movie", 0)
        Thread {
            var movie = db.movieDao().getMovieById(clickedMovie)
            title.text = movie.movieTitle
            image.setImageResource(movie.image)
            description.text = movie.description

        }.start()

    }
}