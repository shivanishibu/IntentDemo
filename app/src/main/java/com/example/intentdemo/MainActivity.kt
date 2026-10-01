package com.example.intentdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Auto-login if session is active
        if (sharedPrefs.getBoolean("is_logged_in", false)) {
            val savedUserName = sharedPrefs.getString("user_name", "Guest") ?: "Guest"
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("USER_NAME", savedUserName)
            }
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Pre-fill last saved username if available
        val lastSavedUserName = sharedPrefs.getString("user_name", "")
        if (!lastSavedUserName.isNullOrEmpty()) {
            nameEditText.setText(lastSavedUserName)
        }

        loginButton.setOnClickListener {

            val name = nameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // If name is empty, use Guest
            val userName = if (name.isEmpty()) {
                "Guest"
            } else {
                name
            }

            // 1. Save credentials in SQLite Database
            val dbHelper = DatabaseHelper(this)
            dbHelper.insertOrUpdateUser(userName, password)

            // 2. Save login session in SharedPreferences
            sharedPrefs.edit().apply {
                putBoolean("is_logged_in", true)
                putString("user_name", userName)
                apply()
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