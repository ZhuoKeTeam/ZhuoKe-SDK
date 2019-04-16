package team.zhuoke.sdk.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;

import androidx.annotation.LayoutRes;

/**
 * @author Yunr
 * @date 2018/01/16 09:49
 */
public abstract class ZKBaseFloatWindow<DATA> {

    private View baseView;
    private int height;
    private int width;
    private float moveX;
    private float moveY;
    private float chaX;
    private float chaY;

    private WindowManager.LayoutParams lp;
    private WindowManager windowManager;

    public ZKBaseFloatWindow(View contentView, int widthDp, int heightDp) {
        baseView = contentView;
        buildFollow();
        init(widthDp, heightDp);
    }

    public ZKBaseFloatWindow(@LayoutRes int contentViewId, int widthDp, int heightDp) {
        LayoutInflater inflater =
                (LayoutInflater) Utils.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        baseView = inflater.inflate(contentViewId, null, false);
        init(widthDp, heightDp);

    }

    private void init(int widthDp, int heightDp) {
        this.width = SizeUtils.dp2px(widthDp);
        this.height = SizeUtils.dp2px(heightDp);
        initLayoutParams(width, height);
        windowManager = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        buildFollow();
        initView(baseView);
    }

    /**
     * findId，设置事件等
     *
     * @param contentView
     */
    protected abstract void initView(View contentView);

    /**
     * 更新数据
     *
     * @param data
     */
    public abstract void update(DATA data);


    /**
     * 如果调用show()时没有显示悬浮窗请检查您是否授权悬浮窗
     * <p>
     * 具体请自行设置
     * <p>
     * 可参考:<url>https://github.com/czy1121/settingscompat</url>
     */
    public void show() {
        if (baseView != null) {
            windowManager.addView(baseView, lp);
        }
    }

    public void close() {
        if (baseView != null) {
            windowManager.removeView(baseView);
            baseView = null;
            height = 0;
            width = 0;
        }
    }

    private void buildFollow() {
        baseView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        chaX = (int) (event.getRawX() - lp.x);
                        chaY = (int) (event.getRawY() - lp.y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = event.getRawX();
                        moveY = event.getRawY();
                        lp.x = (int) (moveX - chaX);
                        lp.y = (int) (moveY - chaY);
                        windowManager.updateViewLayout(baseView, lp);
                        break;
                }
                return true;
            }
        });
    }

    private void initLayoutParams(int width, int height) {
        lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
        //总是出现在应用程序窗口之上。
        lp.type = WindowManager.LayoutParams.TYPE_PHONE;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按,
        // 不设置这个flag的话，home页的划屏会有问题
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        //悬浮窗默认显示的位置
        lp.gravity = Gravity.START | Gravity.TOP;
        //悬浮窗的宽高
        lp.width = width;
        lp.height = height;
        lp.format = PixelFormat.TRANSPARENT;
    }
}
