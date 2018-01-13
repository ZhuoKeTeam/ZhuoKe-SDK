package team.zhuoke.sdk;

import android.view.View;
import android.widget.TextView;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.dialog.ScreenDialog;

/**
 * ZK SDK 的彩蛋页面
 * Created by WangQing on 2018/1/12.
 */

public class ZKAggActivity extends BaseActivity {
    TextView word;
    TextView mScreen;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zk_sdk_main;
    }

    @Override
    protected void initViews() {
        word = findViewById(R.id.word);
        mScreen = findViewById(R.id.screen);

    }

    @Override
    protected void initListener() {
        mScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenDialog.show(ZKAggActivity.this);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
