package com.example.androidassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidassignment.Database.Movie
import com.example.androidassignment.Database.MovieAdapter
import com.example.androidassignment.Database.MovieDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db : MovieDatabase
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MovieDatabase.getAppDatabase(this)!!


        if(db.movieDao().getAllMovies().isEmpty()){
            Log.i("DatabaseTest", "Ran Database Population")
            var movie = Movie(1, "Apocalyse Now", "Best movie ever made")
            var movie1 = Movie(2, "The Lighthouse", "Second Best movie ever made")


            db.movieDao().insert(movie)
            db.movieDao().insert(movie1)
        }


        var movieList : ArrayList<Movie> = arrayListOf()
        for (movie in db.movieDao().getAllMovies()){
            movieList.add(movie)
        }
        adapter = MovieAdapter(movieList)

        var recyclerView: RecyclerView = findViewById(R.id.movieView)
        recyclerView.setHasFixedSize(true)
        var layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}