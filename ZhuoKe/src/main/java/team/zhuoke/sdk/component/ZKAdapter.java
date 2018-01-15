package team.zhuoke.sdk.component;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * ZKAdapter
 * Created by WangQing on 2018/1/15.
 */

public abstract class ZKAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public ZKAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public ZKAdapter(@Nullable List<T> data) {
        super(data);
    }

    public ZKAdapter(int layoutResId) {
        super(layoutResId);
    }
}
