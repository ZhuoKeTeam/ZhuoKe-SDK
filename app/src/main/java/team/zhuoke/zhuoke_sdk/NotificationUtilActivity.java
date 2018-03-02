package team.zhuoke.zhuoke_sdk;

import android.view.View;
import android.widget.Toast;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.notification.ItemDataBean;
import team.zhuoke.sdk.notification.NotificationBuilder;
import team.zhuoke.sdk.notification.NotificationItemClickListener;

public class NotificationUtilActivity extends BaseActivity implements NotificationItemClickListener {

    private NotificationBuilder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_util;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 添加第一个选项进通知栏工具
     */
    public void addOneItem(View view) {
        builder.addItem(new ItemDataBean("选项一", R.mipmap.ic_launcher, "1"));
    }

    /**
     * 添加第二个选项进通知栏工具
     */
    public void addTwoItem(View view) {
        if (builder == null) {
            builder = new NotificationBuilder(getBaseContext());
        }

        builder.addItem(new ItemDataBean("选项二", R.mipmap.ic_launcher_round, "2"));
    }

    /**
     * 替换第一个选项内容
     */
    public void repOneItem(View view) {
        if (builder == null) {
            return;
        }

        ItemDataBean dataBean = new ItemDataBean("新选项", R.mipmap.head_img, "3");
        builder.updateItem(dataBean, 0);
    }

    /**
     * 关闭工具栏
     */
    public void closeUtilBar(View view) {
        if (builder == null) {
            return;
        }

        builder.close();
    }

    @Override
    public void onItemClicked(ItemDataBean clickItemBean) {
        Toast.makeText(mContext, clickItemBean.toString(), Toast.LENGTH_SHORT).show();
        switch (clickItemBean.getItemId()) {
            case "1":
                if (clickItemBean.getImgId() == R.mipmap.ic_launcher) {
                    clickItemBean.setText("被点击了");
                    clickItemBean.setImgId(R.drawable.ic_screen);
                } else {
                    clickItemBean.setText("取消点击");
                    clickItemBean.setImgId(R.mipmap.ic_launcher);
                }
                break;
            case "2":
                if (clickItemBean.getImgId() == R.mipmap.ic_launcher_round) {
                    clickItemBean.setText("被点击了");
                    clickItemBean.setImgId(R.drawable.ic_screen);
                } else {
                    clickItemBean.setText("取消点击");
                    clickItemBean.setImgId(R.mipmap.ic_launcher_round);
                }
                break;
            case "3":
                Toast.makeText(mContext, "点击了新替换的选项", Toast.LENGTH_SHORT).show();
                break;
        }

        builder.updateItem(clickItemBean);
    }

    public void initNotifiy(View view) {
        if (builder == null) {
            builder = new NotificationBuilder(getBaseContext());
        }

        builder.setOnItemClickListener(this);

    }
}
