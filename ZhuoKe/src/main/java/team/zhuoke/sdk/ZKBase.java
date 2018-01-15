package team.zhuoke.sdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;

import team.zhuoke.sdk.exception.ZKBaseNullPointerException;
import team.zhuoke.sdk.manager.ZKPathManager;


/**
 * JMBase 初始化相关
 * Created by WangQing on 2017/5/12.
 */
public final class ZKBase {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static boolean isDebug = BuildConfig.DEBUG;

    /**
     * 获取当前可用的 存储空间路径
     */
    private static String avaliblePath;

    private ZKBase() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类 (推荐)
     */
    public static void init(@NonNull Application app) {
        init(app, false);
    }

    /**
     * 初始化工具类（可以直接设置 debug 的状态）
     */
    public static void init(@NonNull Application app, boolean debug) {
        ZKBase.context = app.getApplicationContext();
        ZKBase.isDebug = debug;

        Utils.init(app);

        avaliblePath = ZKPathManager.getInstance().getDefaultPath();
    }



    @CheckResult
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new ZKBaseNullPointerException("context is null!");
    }

    /**
     * 当前是否是  debug 的状态
     *
     * @return boolean
     */
    @CheckResult
    public static boolean isDebug() {
        return isDebug;
    }
}
