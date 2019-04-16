package team.zhuoke.zhuoke_sdk.adapter;


import java.util.List;

import androidx.annotation.Nullable;
import team.zhuoke.sdk.component.ZKAdapter;
import team.zhuoke.sdk.component.ZKViewHolder;
import team.zhuoke.zhuoke_sdk.R;
import team.zhuoke.zhuoke_sdk.bean.ItemBean;

/**
 * ZKCommentAdapter
 * Created by WangQing on 2018/1/15.
 */
public class ZKCommentAdapter extends ZKAdapter<ItemBean, ZKViewHolder> {

    public ZKCommentAdapter(@Nullable List<ItemBean> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(ZKViewHolder helper, ItemBean item) {
        helper.setText(R.id.id, item.getName());
    }
}
