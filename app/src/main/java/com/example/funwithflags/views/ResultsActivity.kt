package com.example.funwithflags.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funwithflags.R
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val score: Int = intent.getIntExtra("score", 0)

        result.text = "$score / 10"

        playAgain.setOnClickListener {
            startActivity(Intent(this, QuestionActivity::class.java))
            finish()
        }
    }
}