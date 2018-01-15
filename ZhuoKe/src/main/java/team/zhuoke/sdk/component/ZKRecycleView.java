package team.zhuoke.sdk.component;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * ZKRecycleView
 *
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 *
 * https://www.jianshu.com/p/b343fcff51b0
 *
 * Created by WangQing on 2018/1/15.
 */

public class ZKRecycleView extends RecyclerView{

    public ZKRecycleView(Context context) {
        super(context);
    }

    public ZKRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZKRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
