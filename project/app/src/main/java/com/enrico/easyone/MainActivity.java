package com.enrico.easyone;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView rationale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            rationale = findViewById(R.id.permission);

            switchPermission(PermissionUtils.CONTACT_REQUEST_CODE);

        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case PermissionUtils.CONTACT_REQUEST_CODE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    switchPermission(PermissionUtils.CALL_REQUEST_CODE);

                }
            }
            break;

            case PermissionUtils.CALL_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "thankyou:)", Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
                break;
        }
    }

    private void switchPermission(int code) {

        final boolean phone = code != PermissionUtils.CONTACT_REQUEST_CODE;

        int drawable = phone ? R.drawable.ic_phone : R.drawable.ic_contact;

        int bg_color = phone ? R.color.colorPhone : R.color.colorContact;
        int sb_color = phone ? R.color.colorPhoneDark : R.color.colorContactDark;

        String text = phone ? getString(R.string.call_permission) : getString(R.string.contact_perm);

        rationale.setCompoundDrawablesWithIntrinsicBounds(0, drawable, 0, 0);

        getWindow().getDecorView().getRootView().setBackgroundColor(getResources().getColor(bg_color, getTheme()));
        getWindow().setStatusBarColor(getResources().getColor(sb_color, getTheme()));
        getWindow().setNavigationBarColor(getResources().getColor(sb_color, getTheme()));

        rationale.setText(text);

        rationale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phone) {
                    PermissionUtils.requestCallPermission(MainActivity.this);
                } else {
                    PermissionUtils.requestContactPermission(MainActivity.this);
                }
            }
        });
    }
}
