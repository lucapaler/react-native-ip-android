// ReactNativeIpAndroidModule.java

package com.lucapalermo;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;
import com.facebook.react.bridge.Promise;

public class ReactNativeIpAndroidModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReactNativeIpAndroidModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeIpAndroid";
    }

    @ReactMethod
    public void getNeighbors(Promise promise) {
        String result = null;

        try {
            Process process = Runtime.getRuntime().exec("ip neigh show");

            InputStream inputStream = process.getInputStream();

            Scanner s = new Scanner(inputStream).useDelimiter("\\A");

            result = s.hasNext() ? s.next() : null;
        } catch (IOException e) {
            e.printStackTrace();

            promise.reject("getNeighbors failed", e);
        }

        promise.resolve(result);
    }
}
