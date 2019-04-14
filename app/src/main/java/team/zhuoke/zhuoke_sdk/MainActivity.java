package team.zhuoke.zhuoke_sdk;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;
import team.zhuoke.sdk.utils.SdkUtils;
import team.zhuoke.sdk.utils.ZKPageCtrl;

public class MainActivity extends BaseActivity  {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.recyclerView)
    ZKRecycleView recyclerView;
    private TestFloatWindow floatWindow;
    private CountDownTimer countDownTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        initRecyclerView();
    }

    private void initRecyclerView() {
        //        https://www.jianshu.com/p/b343fcff51b0
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setName("名字：" + i);
            list.add(itemBean);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ZKCommentAdapter(list));
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ToastUtils.showShort("sdk version: " + SdkUtils.version);
        ZKPageCtrl.startEggActivity(mContext);
    }

    public void showFloatWindow(View view) {

        if (floatWindow == null) {
            floatWindow = new TestFloatWindow();
            floatWindow.show();
            countDownTimer = new CountDownTimer(30000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    floatWindow.update("更新数据，剩余时间:" + millisUntilFinished / 1000 + "S");
                }

                @Override
                public void onFinish() {
                    floatWindow.update("演示完毕");
                }
            }.start();
        }
    }

    public void closeFloatWindow(View view) {
        if (floatWindow != null) {
            countDownTimer.cancel();
            floatWindow.close();
            floatWindow = null;
        }
    }

    public void showNotificationUtil(View view) {
        startActivity(new Intent(getBaseContext(), NotificationUtilActivity.class));
    }


}
