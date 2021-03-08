package com.vedom.cinema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.first_activity_text)
        textView.setOnClickListener { moveToMovieDetailsActivity() }
    }

    private fun moveToMovieDetailsActivity() {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        startActivity(intent)
    }
}