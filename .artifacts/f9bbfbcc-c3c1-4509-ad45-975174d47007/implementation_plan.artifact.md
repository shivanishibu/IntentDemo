# Implementation Plan - Experiment 5: Display Notification in Android

Implement the notification feature as specified in the lab manual, integrating it into the existing `SecondActivity`.

## User Review Required

> [!IMPORTANT]
> This feature requires the `POST_NOTIFICATIONS` permission on Android 13 (API 33) and above. I will add logic to request this permission when the "Show Notification" button is clicked.

## Proposed Changes

### [Component] Android Manifest
#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/AndroidManifest.xml)
- Add `<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />`.

### [Component] Layout
#### [MODIFY] [activity_second.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/res/layout/activity_second.xml)
- Add a "Show Notification" button above the logout button.

### [Component] Activity Logic
#### [MODIFY] [SecondActivity.kt](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/java/com/example/intentdemo/SecondActivity.kt)
- Define `channelId` and `notificationId` constants.
- In `onCreate`:
    - Call `createNotificationChannel()`.
    - Set up the "Show Notification" button click listener.
- Implement `createNotificationChannel()` to set up the channel for Android 8.0+.
- Implement `showNotification()` using `NotificationCompat.Builder` with:
    - Title: "Hello"
    - Text: "This is an android notification"
    - Small Icon: `R.drawable.ic_launcher_foreground`
    - Priority: `PRIORITY_DEFAULT`
    - AutoCancel: `true`
- Add permission handling logic to request `POST_NOTIFICATIONS` if needed before showing the notification.

## Verification Plan

### Manual Verification
1.  Run the app and log in to reach `SecondActivity`.
2.  Click the new "Show Notification" button.
3.  If on Android 13+, allow the notification permission when prompted.
4.  Verify that a notification appears in the system tray with the title "Hello" and the text "This is an android notification".
