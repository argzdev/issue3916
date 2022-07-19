# issue 3916
### GitHub link
- https://github.com/firebase/firebase-android-sdk/issues/3916
### Prerequisite
- Add google-services.json
- Register app to App Check with SafetyNet
- *Optional*: Add Debug token on Firebase project settings and in app line 41
### Steps to reproduce
- Open app
- Check logs and find error `IllegalAccessException: Class java.lang.Class<com.argz.issue3916.MainActivity> cannot access  method void com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory.<init>(java.lang.String) of class java.lang.Class<com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory>`
### Summary
- Enabling DebugAppCheckProviderFactory in Android 6 to Android 9 causes an `IllegalAccessException`. Android 10 to 12 works fine.
