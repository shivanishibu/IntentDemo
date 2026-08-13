package com.example.intentdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val backButton = findViewById<Button>(R.id.backButton)

        val name = intent.getStringExtra("USER_NAME")

        welcomeTextView.text = "Welcome, $name!"

        backButton.setOnClickListener {
            finish()
        }
    }
}