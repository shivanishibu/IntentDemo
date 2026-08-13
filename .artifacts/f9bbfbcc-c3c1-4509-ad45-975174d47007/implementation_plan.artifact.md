# Implementation Plan - Perfecting the Login Page

The goal is to transform the existing simple input screen into a modern, professional Login page following Material 3 design principles and Android best practices.

## Proposed Changes

### [Component] Build Configuration
#### [MODIFY] [build.gradle.kts](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/build.gradle.kts)
- Enable `viewBinding`.

### [Component] Resources
#### [MODIFY] [strings.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/res/values/strings.xml)
- Add strings for login labels, hints, and validation errors.

### [Component] Layout
#### [MODIFY] [activity_main.xml](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/res/layout/activity_main.xml)
- Replace `LinearLayout` with `ConstraintLayout`.
- Use `com.google.android.material.textfield.TextInputLayout` for Email and Password.
- Use `TextInputEditText` for the actual input fields.
- Add a password visibility toggle.
- Improve spacing and alignment.
- Style the Login button as a `MaterialButton`.

### [Component] Activity Logic
#### [MODIFY] [MainActivity.kt](file:///C:/Users/USER/AndroidStudioProjects/IntentDemo/app/src/main/java/com/example/intentdemo/MainActivity.kt)
- Implement View Binding.
- Implement Edge-to-edge support for a modern look.
- Add input validation logic:
    - Check for empty Email and Password.
    - Validate Email format.
    - Show real-time or button-click error feedback using `TextInputLayout.error`.
- On successful validation, pass the "username" (part of email) to `SecondActivity`.

## Verification Plan

### Manual Verification
- Deploy to an Android device/emulator.
- Verify the UI looks professional and follows Material 3 (Material Design 3).
- Test validation:
    - Click "Login" with empty fields -> should show errors.
    - Enter invalid email -> should show error.
    - Enter valid details -> should navigate to `SecondActivity` and display the name correctly.
- Verify password masking and visibility toggle.
- Check Edge-to-edge behavior (system bars should be transparent/blended).
