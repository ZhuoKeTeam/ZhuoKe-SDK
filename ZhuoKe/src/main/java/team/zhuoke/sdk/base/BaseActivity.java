package team.zhuoke.sdk.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import team.zhuoke.sdk.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * BaseActivity
 * <p>
 * Created by WangQing on 2017/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setTintResource(R.color.blue);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(false);

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbar();
        mContext = this;
        initViews();
        initListener();
        initData();
    }


    //获取资源ID
    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initListener();

    protected abstract void initData();

    //设置标题
    protected void setTitle(String title) {
        mTitle.setText(title);
    }

    protected void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.tv_title);
        if (mToolbar != null) {
            if (mToolbar.getVisibility() == View.GONE)
                mToolbar.setVisibility(View.VISIBLE);
            mToolbar.setNavigationIcon(R.mipmap.arrow_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLeftClick();
                }
            });
        }

    }

    //设置右边菜单
    protected void setRight(int itemId) {
        mToolbar.getMenu().clear();
        if (itemId > 0) {
            mToolbar.inflateMenu(itemId);
            mToolbar.setOnMenuItemClickListener(this);
        }
    }

    protected void onLeftClick() {
        finish();
    }

    protected void setVisibility(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    //菜单的点击事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        onRightText(item);
        return true;
    }

    public void onRightText(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.more) {//默认宽度 56dp
            Toast.makeText(mContext, "疼", Toast.LENGTH_SHORT).show();

        }
    }
}
