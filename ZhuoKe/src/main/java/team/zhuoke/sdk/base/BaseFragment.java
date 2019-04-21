package team.zhuoke.sdk.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import team.zhuoke.sdk.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * BaseFragment
 * Created by WangQing on 2017/11/7.
 */

public abstract class BaseFragment extends Fragment {
    protected View rootView;

    //context
    protected Context mContext = null;

    // 获取布局资源文件
    public abstract @LayoutRes
    int getLayoutId();

    // 初始化布局
    public abstract void initView(View rootView);

    // 初始化数据
    public abstract void initData(Bundle savedInstanceState);

    // 初始化相关 View 的 listener
    public abstract void initListener();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        initView(rootView);
    }

    protected void showBarState(View rootView, @ColorRes int color, boolean isShow) {
        if (isShow) {
            BarUtils.subtractMarginTopEqualStatusBarHeight(rootView);

            if (color == 0) {
                color = R.color.transparent;
            }

            Activity activity = getActivity();
            if (activity != null) {
                BarUtils.setStatusBarLightMode(activity, true);
                BarUtils.setStatusBarColor(activity, activity.getResources().getColor(color), true);
            }
        }
    }

    protected void setBarState(View rootView) {
        showBarState(rootView, 0, true);
    }

    protected void setBarState(View rootView, @ColorRes int color, boolean isShow) {
        showBarState(rootView, color, isShow);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initData(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    public void setText(TextView textView, String str) {
        if (textView != null && null != str) {
            textView.setText(str);
        }
    }

    protected void setVisibility(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? VISIBLE : GONE);
        }
    }
}
