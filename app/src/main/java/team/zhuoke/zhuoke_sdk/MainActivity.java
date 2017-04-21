package team.zhuoke.zhuoke_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import team.zhuoke.sdk.SdkUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "sdk version: " + SdkUtils.version, Toast.LENGTH_SHORT).show();
    }
}
