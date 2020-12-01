package com.example.funwithflags.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.funwithflags.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		buttonStart.setOnClickListener {
			if (nameInput.text.isEmpty()) {
				Toast.makeText(this, "Name is required", Toast.LENGTH_LONG).show()
			} else {
				startActivity(Intent(this, QuestionActivity::class.java))
				finish()
			}
		}

	}
}