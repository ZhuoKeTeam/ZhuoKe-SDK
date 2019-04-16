package team.zhuoke.sdk;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import team.zhuoke.sdk.manager.ZKConnectionManager;

/**
 * 卓客初始化 一些相关信息
 * Created by WangQing on 2018/3/6.
 */

public class ZKManager {

    private Context mContext;

    private static ZKManager instance = null;

    private ZKManager() {
    }

    public static ZKManager getInstance() {
        if (instance == null) {
            synchronized (ZKManager.class) {
                ZKManager temp = instance;
                if (temp == null) {
                    temp = new ZKManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        init(application, false);
    }

    public void setBaseUrl(String url) {
        Constant.BASE_API_URL = url;
    }

    /**
     * @param debug 通常使用 BuildConfig.DEBUG
     */
    public void init(Application application, boolean debug) {
        mContext = application;
        ZKBase.init(application, debug);
    }

    /**
     * 在 Application 中初始化 Stetho
     * @param mContext 上下文
     */
    public void initStethoForApplicaiton(final Context mContext) {
        Stetho.initialize(Stetho.newInitializerBuilder(mContext)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(mContext)
                                .provide(new HprofDumperPlugin(mContext))
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(mContext))
                .build());

        //添加 Stetho 的拦截器
        ZKConnectionManager.getInstance().getBuilder().addNetworkInterceptor(new StethoInterceptor());
    }

    /**
     * android 7.0系统解决拍照的问题
     */
    public void initTakePhotoForApplication() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            // android 7.0系统解决拍照的问题
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }


}
