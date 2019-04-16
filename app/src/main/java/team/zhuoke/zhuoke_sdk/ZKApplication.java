package team.zhuoke.zhuoke_sdk;

import android.app.Application;

import team.zhuoke.sdk.ZKManager;
import team.zhuoke.zhuoke_sdk.api.UrlConfig;

/**
 * team.zhuoke.zhuoke_sdk.ZKApplication
 * Created by WangQing on 2018/1/12.
 */

public class ZKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZKManager.getInstance().init(this);
        ZKManager.getInstance().initStethoForApplicaiton(this);
        ZKManager.getInstance().initTakePhotoForApplication();

        ZKManager.getInstance().setBaseUrl(UrlConfig.getBaseUrl());

    }
}
