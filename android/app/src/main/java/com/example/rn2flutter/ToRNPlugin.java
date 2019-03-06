package com.example.rn2flutter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class ToRNPlugin implements MethodChannel.MethodCallHandler {
    private Context cx;
    private Activity activity;

    public ToRNPlugin(Context cx,Activity activity) {
        this.cx = cx;
        this.activity = activity;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "lmy/toRNPlugin");
        channel.setMethodCallHandler(new ToRNPlugin(registrar.context(),registrar.activity()));
    }

    private void toRNPage() {
        Intent intent = new Intent(activity,ReactNativeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case "toRNPage":
                this.toRNPage();
                break;
            default:
                break;
        }
    }
}
