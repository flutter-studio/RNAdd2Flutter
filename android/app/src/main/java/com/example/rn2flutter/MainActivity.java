package com.example.rn2flutter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;


import com.facebook.react.ReactInstanceManager;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // 任写一个值
  private ReactInstanceManager mReactInstanceManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    mReactInstanceManager = MyReactInstanceManager.initObj(getApplication());
    ToRNPlugin.registerWith(this.registrarFor("com.example.rn2flutter.ToRNPlugin"));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (!Settings.canDrawOverlays(this)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.canDrawOverlays(this)) {
          // SYSTEM_ALERT_WINDOW permission not granted
        }
      }
    }
    mReactInstanceManager.onActivityResult( this, requestCode, resultCode, data );
  }

}
