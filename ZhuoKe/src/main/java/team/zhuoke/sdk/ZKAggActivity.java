package team.zhuoke.sdk;

import android.widget.TextView;

import team.zhuoke.sdk.base.BaseActivity;

/**
 * ZK SDK 的彩蛋页面
 * Created by WangQing on 2018/1/12.
 */

public class ZKAggActivity extends BaseActivity {
    TextView word;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_zk_sdk_main;
    }

    @Override
    protected void initViews() {
        word = findViewById(R.id.word);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
