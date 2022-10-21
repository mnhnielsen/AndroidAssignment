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

        Thread {
            if (db.movieDao().getAllMovies().isEmpty()) {
                Log.i("DatabaseTest", "Ran Database Population")
                initDB()
            }
            movieList.addAll(db.movieDao().getAllMovies())
        }.start()

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

    private fun initDB() {
        val movie = Movie(1,
            "Apocalypse Now",
            getString(R.string.ApocalypseNow),
            R.drawable.apocalypse)

        val movie1 = Movie(2,
            "The Lighthouse",
            getString(R.string.TheLighthouse),
            R.drawable.lighthouse)

        val movie2 = Movie(3,
            "The Irishman",
            getString(R.string.TheIrishman),
            R.drawable.irishman)

        val movie3 = Movie(4,
            "Lawless",
            getString(R.string.Lawless),
            R.drawable.lawless)

        val movie4 = Movie(5,
            "Public Enemy",
            getString(R.string.PublicEnemy),
            R.drawable.publicenemy)

        db.movieDao().insert(movie)
        db.movieDao().insert(movie1)
        db.movieDao().insert(movie2)
        db.movieDao().insert(movie3)
        db.movieDao().insert(movie4)
    }

}