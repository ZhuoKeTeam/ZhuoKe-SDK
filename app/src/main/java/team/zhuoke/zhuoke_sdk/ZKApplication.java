package team.zhuoke.zhuoke_sdk;

import android.app.Application;

import team.zhuoke.sdk.ZKBase;

/**
 * team.zhuoke.zhuoke_sdk.ZKApplication
 * Created by WangQing on 2018/1/12.
 */

public class ZKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZKBase.init(this, true);
    }
}
