package com.argz.issue3916;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ErrorsInMain";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        Class<DebugAppCheckProviderFactory> debugACFactoryClass =
                DebugAppCheckProviderFactory.class;

        // Get the (undocumented) constructor accepting a debug token as string
        Class<?>[] argType = {String.class};
        Constructor c = null;
        try {
            c = debugACFactoryClass.getDeclaredConstructor(argType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.d(TAG, "NoSuchMethodException: " + e.getMessage());
        }

        // Create a object containing the constructor arguments
        // and initialize a new instance.
        Object[] cArgs = {"YOUR_DEBUG_TOKEN"};
        try {
            firebaseAppCheck.installAppCheckProviderFactory(
                    (AppCheckProviderFactory) c.newInstance(cArgs));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.d(TAG, "IllegalAccessException: " + e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            Log.d(TAG, "InstantiationException: " + e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.d(TAG, "InvocationTargetException: " + e.getMessage());
        }

    }
}
