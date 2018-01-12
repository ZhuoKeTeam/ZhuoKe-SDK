package team.zhuoke.sdk.utils;

import android.content.Context;
import android.content.Intent;

import team.zhuoke.sdk.ZKAggActivity;

/**
 * PageCtrl
 * Created by WangQing on 2018/1/12.
 */

public class ZKPageCtrl {

    /**
     * 启动彩蛋页面
     * @param context
     */
    public static void startEggActivity(Context context) {
        Intent intent = new Intent(context, ZKAggActivity.class);
        context.startActivity(intent);
    }
}
