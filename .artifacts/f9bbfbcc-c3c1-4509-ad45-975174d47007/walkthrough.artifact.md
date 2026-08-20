# Walkthrough - Experiment 5: Android Notifications

I have successfully implemented the notification feature as described in your lab manual (Experiment No. 5).

## Changes Made

### 1. Permissions
Updated [AndroidManifest.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/AndroidManifest.xml) to include the `POST_NOTIFICATIONS` permission, which is required for Android 13 and above.

### 2. User Interface
Added a new **"SHOW NOTIFICATION"** button to the [activity_second.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/res/layout/activity_second.xml) layout. This button is styled to match your existing premium design.

### 3. Notification Logic
Implemented the following in [SecondActivity.kt](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/java/com/example/intentdemo/SecondActivity.kt):
- **Notification Channel:** Created a channel named "My Notifications" for Android 8.0+ compatibility.
- **Permission Handling:** Added logic to check and request notification permissions on Android 13+ devices.
- **Notification Builder:** Configured the notification with:
    - **Title:** "Hello"
    - **Text:** "This is an android notification"
    - **Icon:** Uses the default app foreground icon.
    - **AutoCancel:** Set to `true` so it disappears when clicked.

## How to Verify
1.  **Launch the App:** Log in to reach the Welcome screen.
2.  **Click "SHOW NOTIFICATION":**
    - On Android 13+, a permission dialog will appear. Click **"Allow"**.
3.  **Check the Tray:** Pull down the notification drawer to see your "Hello" notification!

## Screenshots
*(I recommend taking a screenshot of the notification appearing at the top of your screen and adding it to your README!)*
