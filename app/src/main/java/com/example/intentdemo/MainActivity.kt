package com.example.intentdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {

            val name = nameEditText.text.toString().trim()

            // If name is empty, use Guest
            val userName = if (name.isEmpty()) {
                "Guest"
            } else {
                name
            }

            // Create Intent
            val intent = Intent(this, SecondActivity::class.java)

            // Send username to SecondActivity
            intent.putExtra("USER_NAME", userName)

            // Open Welcome screen
            startActivity(intent)
        }
    }
}