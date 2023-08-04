package com.tutorial.reactunity;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class Game_platform extends ReactContextBaseJavaModule {

    static final String TAG = Game_platform.class.getSimpleName();

    private static ReactContext mReactContext;

    public Game_platform(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        if (reactContext == null) {
            Log.e(TAG, "GGLauncher: react context is null.");
        }
    }

    @ReactMethod
    public void sendDataToUnity(String data) {
        // Log.e(TAG, "sendDataToUnity: got it... " + authKey);
        Intent intent = new Intent(getCurrentActivity(), UnityPlayerActivity.class);

        // // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data", data);
        if (getCurrentActivity() != null) {
            getCurrentActivity().startActivityForResult(intent, 1110);
        }
    }

    private static void sendEvent(String eventName, @Nullable WritableMap params) {
        if (mReactContext != null) {
            mReactContext
                    .getJSModule(RCTNativeAppEventEmitter.class)
                    .emit(eventName, params);
        } else {
            Log.e(TAG, "sendEvent: react context is null.");
        }
    }

    @ReactMethod
    public void addListener(String eventName) {
        // Set up any upstream listeners or background tasks as necessary
        Log.e(TAG, "addListener: got event. " + eventName);
    }

    @ReactMethod
    public void removeListeners(Integer count) {
        // Remove upstream listeners, stop unnecessary background tasks
        Log.e(TAG, "removeListeners: got it. ");
    }

    // public static void callBackFromUnityToRN(String data) {
    // Log.e(TAG, "closeUnity: close called................ " + data);
    // WritableMap params = Arguments.createMap();
    // params.putString("data", data);
    // sendEvent("onSubmit", params);
    // // ((UnityPlayerActivity) UnityPlayer.currentActivity).finish();
    // }

    @NonNull
    @Override
    public String getName() {
        return Game_platform.class.getSimpleName();
    }

}
