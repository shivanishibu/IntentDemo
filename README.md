# Intent Demo - Premium Login, SQLite & SharedPreferences Persistence

A comprehensive Android application demonstrating **Data Persistence using SQLite & SharedPreferences**, **System Notifications**, and **Explicit Intents** wrapped in a modern Material 3 interface.

## ✨ Features

*   **SQLite Database Persistence (`DatabaseHelper`):** Persists `username` and `password` inside an on-device SQLite database (`UserDatabase.db`) using `SQLiteOpenHelper`.
*   **Session Persistence & Auto-Login (`SharedPreferences`):** Remembers active login sessions (`isLoggedIn`) and username. Reopening the app automatically skips the login screen until the user logs out.
*   **Username Pre-fill:** Retains the last logged-in username to pre-fill the login input field after logging out.
*   **System Notifications (Expt 5):** Demonstrates the Android Notification API with custom channels and runtime permission handling for Android 13+.
*   **Premium UI Design:** Features custom gradient backgrounds (`login_background`), rounded cards (`welcome_card`, `white_card`), and smooth Material 3 buttons (`login_button`).
*   **Dynamic Greetings & Inputs:** Handles user input dynamically and defaults to a "Guest" session if empty.

## 🛠️ Tech Stack

*   **Kotlin** - Modern, concise, and safe Android programming.
*   **SQLite (`SQLiteOpenHelper`)** - Structured database storage for user credentials.
*   **SharedPreferences** - Key-value storage for persistent session state.
*   **Material Design 3** - Following the latest Android UI standards.
*   **Explicit Intents** - Inter-activity navigation and data passing.
*   **NotificationManager & NotificationCompat** - System-level notification alerts.

## 📐 Design & Layout Assets

*   `DatabaseHelper.kt`: SQLite helper managing `UserDatabase.db` and the `users` table.
*   `login_background.xml`: Smooth 135-degree gradient from White to Light Lavender.
*   `login_button.xml`: Vibrant Purple gradient with soft 18dp rounded corners.
*   `edittext_background.xml`: Clean input fields with 16dp rounded borders and lavender strokes.
*   `welcome_card.xml` & `white_card.xml`: Modern rounded cards displaying welcome and persistence status details.

## 🚦 Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/IntentDemo.git
    ```
2.  **Open in Android Studio:**
    Open the root folder of the project in Android Studio.
3.  **Run the App:**
    Select an emulator or connected physical device and click **Run**.

## 📖 Key Code Implementations

### 1. SQLite Database Persistence (`DatabaseHelper.kt`)
Inserting and querying user credentials in SQLite:
```kotlin
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDatabase.db", null, 1) {
    fun insertOrUpdateUser(username: String, password: String): Long {
        val db = writableDatabase
        db.delete("users", null, null)
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }
        return db.insert("users", null, values)
    }

    fun getStoredUser(): Pair<String, String>? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT username, password FROM users LIMIT 1", null)
        // Returns Pair(username, password)
        ...
    }
}
```

### 2. Session Management (`SharedPreferences`)
Auto-login check in `MainActivity.kt`:
```kotlin
val sharedPrefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

if (sharedPrefs.getBoolean("is_logged_in", false)) {
    val savedUserName = sharedPrefs.getString("user_name", "Guest") ?: "Guest"
    startActivity(Intent(this, SecondActivity::class.java).apply {
        putExtra("USER_NAME", savedUserName)
    })
    finish()
}
```

### 3. System Notifications (Experiment 5)
Triggering a system notification in `SecondActivity.kt`:
```kotlin
val builder = NotificationCompat.Builder(this, channelId)
    .setSmallIcon(R.drawable.ic_launcher_foreground)
    .setContentTitle("Hello")
    .setContentText("This is an android notification")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setAutoCancel(true)

NotificationManagerCompat.from(this).notify(notificationId, builder.build())
```

## 📸 Screenshots

| Login & Persistence Screen | Welcome & Notification Screen |
| :---: | :---: |
| ![Login & Persistence Screen](<app/screenshot1 (2).jpeg>) | ![Welcome & Notification Screen](<app/screenshot 2.jpeg>) |

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
