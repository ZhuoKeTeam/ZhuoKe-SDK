package team.zhuoke.zhuoke_sdk;

import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
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

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.recyclerView)
    ZKRecycleView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        setTitle("首页");
        setRight(R.menu.item_register);

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
//        ShareUtil.shareText(mContext, "文本", "标题");
    }

    //菜单的点击事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        onRightClick(item);
        return true;
    }

    public void onRightClick(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.more) {//默认宽度 56dp
            //TODO 根据id处理点击事件

        }
    }
}
