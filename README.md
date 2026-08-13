# Intent Demo

A simple Android application designed to demonstrate the core concept of **Intents** for navigating between screens and passing data.

## 🚀 Features

*   **User Input:** A clean interface to enter your name.
*   **Data Persistence:** Seamlessly passes the entered name from the main screen to a welcome screen.
*   **Navigation:** Uses explicit Intents to transition between `MainActivity` and `SecondActivity`.
*   **Modern UI:** Built with Material Design 3 components.

## 🛠️ Built With

*   **Kotlin** - The primary programming language for modern Android development.
*   **Android SDK** - The core framework.
*   **Material 3** - For a modern look and feel.
*   **View Binding** - For safe and efficient UI interaction.

## 📸 Screenshots

| Login Screen | Welcome Screen |
| :---: | :---: |
| ![Login Screen](login_screen.png) | ![Welcome Screen](welcome_screen.png) |

## 🚦 Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/IntentDemo.git
    ```
2.  **Open in Android Studio:**
    Select the root folder of the project.
3.  **Run the app:**
    Select an emulator or physical device and click the **Run** button.

## 📖 How it Works

The app captures user input in `MainActivity`:
```kotlin
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("USER_NAME", name)
startActivity(intent)
```

And retrieves it in `SecondActivity`:
```kotlin
val name = intent.getStringExtra("USER_NAME")
welcomeTextView.text = "Welcome, $name!"
```

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
