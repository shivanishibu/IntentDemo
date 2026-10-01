package com.example.intentdemo

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class SecondActivity : AppCompatActivity() {

    private val channelId = "my_channel_id"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val sqliteUserText = findViewById<TextView>(R.id.sqliteUserText)
        val sqlitePasswordText = findViewById<TextView>(R.id.sqlitePasswordText)
        val notifyButton = findViewById<Button>(R.id.notifyButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        // Get name from Intent
        val name = intent.getStringExtra("USER_NAME") ?: "Guest"

        // Display name
        welcomeText.text = "Welcome, $name! 👋"

        // Fetch stored user data from SQLite Database
        val dbHelper = DatabaseHelper(this)
        val storedUser = dbHelper.getStoredUser()
        if (storedUser != null) {
            sqliteUserText.text = "Stored Username: ${storedUser.first}"
            sqlitePasswordText.text = "Stored Password: ${if (storedUser.second.isNotEmpty()) "•••••••• (${storedUser.second})" else "None"}"
        } else {
            sqliteUserText.text = "Stored Username: $name (SharedPreferences)"
            sqlitePasswordText.text = "Stored Password: None"
        }

        // Show welcome pop-up (Toast)
        Toast.makeText(this, "Login Successful! Welcome, $name", Toast.LENGTH_SHORT).show()

        // Create Notification Channel
        createNotificationChannel()

        // Show Notification
        notifyButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                } else {
                    showNotification()
                }
            } else {
                showNotification()
            }
        }

        // Logout
        logoutButton.setOnClickListener {
            // Clear active login session in SharedPreferences
            val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            sharedPrefs.edit().putBoolean("is_logged_in", false).apply()

            // Return to MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Notifications"
            val descriptionText = "Channel for my notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Hello")
            .setContentText("This is an android notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(this@SecondActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                notify(notificationId, builder.build())
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}