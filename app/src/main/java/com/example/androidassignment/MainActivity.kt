package com.example.androidassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidassignment.Database.Movie
import com.example.androidassignment.Database.MovieAdapter
import com.example.androidassignment.Database.MovieDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db: MovieDatabase
    lateinit var adapter: MovieAdapter
    var movieList: ArrayList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MovieDatabase.getAppDatabase(this)!!

        Log.i("Test", "this is a test")

        if (db.movieDao().getAllMovies().isEmpty()) {
            Log.i("DatabaseTest", "Ran Database Population")
            var movie = Movie(1, "Apocalypse Now", "Best movie ever made", R.drawable.apocalypse)
            var movie1 =
                Movie(2, "The Lighthouse", "Second Best movie ever made", R.drawable.lighthouse)
            db.movieDao().insert(movie)
            db.movieDao().insert(movie1)
        }


        movieList.addAll(db.movieDao().getAllMovies())
        adapter = MovieAdapter(movieList)

        var recyclerView: RecyclerView = findViewById(R.id.movieView)
        recyclerView.setHasFixedSize(true)
        var layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.onItemClick = { movie ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("Movie", movie.id)
            Log.d("CLICKED", movie.movieTitle)
            startActivity(intent)
        }
    }

}