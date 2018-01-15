package team.zhuoke.zhuoke_sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import team.zhuoke.sdk.utils.ShortCutsCreator;

public class AddShortcutActivity extends Activity {
    private Context mContext;
    ShortCutsCreator shortCutsCreator;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_add_shortcut);
        shortCutsCreator = new ShortCutsCreator(mContext);
    }

    public void OnViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                count++;
                shortCutsCreator.createShortCut("id" + count, "id" + count, "long-" + "id" + count, 0, "disabled", new Intent(Intent.ACTION_VIEW, Uri.parse("http://zkteam.cc/")));//R.mipmap.ic_add
                break;
            case R.id.btn_remove:
                shortCutsCreator.disableShortCut("id1");
                break;
        }
    }


}
