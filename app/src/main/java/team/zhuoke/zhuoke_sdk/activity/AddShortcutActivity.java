package team.zhuoke.zhuoke_sdk.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.utils.ShortCutsCreator;
import team.zhuoke.zhuoke_sdk.R;

public class AddShortcutActivity extends BaseActivity {
    ShortCutsCreator shortCutsCreator;
    private int count;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_shortcut;
    }

    @Override
    protected void initViews() {
        setTitle("添加快捷方式");
    }

    @Override
    protected void initListener() {
        shortCutsCreator = new ShortCutsCreator(mContext);

    }

    @Override
    protected void initData() {

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


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
