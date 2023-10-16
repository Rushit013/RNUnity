package com.rnunity; /* <---- change this with your own package name */

import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android.view.WindowManager;
import android.os.Bundle;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "RNUnity";
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    Log.i("TAG", "onActivityResult-- " +requestCode+"   "+ resultCode + "   "+data);

    if (requestCode == 1110) {

      if (data != null) {
        String callBackFromUnityToRN = data.getStringExtra("callBackFromUnityToRN");
        WritableMap params = Arguments.createMap();
        params.putString("data", callBackFromUnityToRN);
        sendEvent("onSubmit", params);
        return;
      }else {
        return;
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
    // }
  }

  private void sendEvent(String eventName, @Nullable WritableMap params) {
    ReactContext reactContext = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
    if (reactContext != null) {
      reactContext
              .getJSModule(RCTNativeAppEventEmitter.class)
              .emit(eventName, params);
    } else {
      Log.i("TAG", "sendEvent: react context is null --- ");
    }
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */

  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new MainActivityDelegate(this, getMainComponentName());
  }

  public static class MainActivityDelegate extends ReactActivityDelegate {
    public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
      super(activity, mainComponentName);
    }

    @Override
    protected ReactRootView createRootView() {
      ReactRootView reactRootView = new ReactRootView(getContext());
      // If you opted-in for the New Architecture, we enable the Fabric Renderer.
      reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED);
      return reactRootView;
    }
  }
}
