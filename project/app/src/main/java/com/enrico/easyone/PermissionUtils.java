package com.enrico.easyone;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by Enrico on 02/08/2017.
 */

class PermissionUtils {

    private static final int OVERLAY_REQUEST_CODE = 0;
    static final int CONTACT_REQUEST_CODE = 1;
    static final int CALL_REQUEST_CODE = 2;

    static void requestCallPermission(Activity activity) {
        activity.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}
                , PermissionUtils.CALL_REQUEST_CODE);
    }

    static void requestContactPermission(Activity activity) {

        activity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}
                , PermissionUtils.CONTACT_REQUEST_CODE);
    }

    static void askDrawOverlayPermission(Activity activity) {

        final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + activity.getPackageName()));

        activity.startActivityForResult(intent, OVERLAY_REQUEST_CODE);
    }
}
