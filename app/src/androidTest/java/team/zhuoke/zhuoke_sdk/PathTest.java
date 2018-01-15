package team.zhuoke.zhuoke_sdk;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;
import android.text.TextUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import team.zhuoke.sdk.ZKBase;
import team.zhuoke.sdk.manager.ZKPathManager;
import team.zhuoke.sdk.utils.L;

/**
 * PathTest
 * Created by WangQing on 2018/1/15.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class PathTest {

    Context context;
    String packageName;

    @Before
    public void init() {
        context = InstrumentationRegistry.getTargetContext();

        packageName = context.getPackageName();
        ZKBase.init((Application) context.getApplicationContext(), true);
    }

    @Test
    public void testPrivatePath() {
        String path = ZKPathManager.getInstance().getPrivatePath();
        L.d("testPrivatePath: " + path);

        if (!TextUtils.isEmpty(path)
                && path.contains("/data")
                && path.contains("/files")
                && path.contains(packageName)) {
            Assert.assertTrue("当前 PrivatePath 路径是：" + path, true);
            return;
        }
        Assert.assertTrue("当前 PrivatePath 路径是：" + path, false);
    }

    @Test
    public void testPublicSDCardPath() {
        String path = ZKPathManager.getInstance().getPublicSDCardPath();
        L.d("testPublicSDCardPath: " + path);

        if (!TextUtils.isEmpty(path)
                && path.contains("/Android/data")
                && path.contains("/files")
                && path.contains(packageName)) {
            Assert.assertTrue("当前 PublicSDCardPath 路径是：" + path, true);
            return;
        }
        Assert.assertTrue("当前 PublicSDCardPath 路径是：" + path, false);
    }

    @Test
    public void testInnerSDCardPath() {
        String path = ZKPathManager.getInstance().getInnerSDCardPath();
        L.d( "testInnerSDCardPath: " + path);


        if (!TextUtils.isEmpty(path)) {
            Assert.assertTrue("当前 InnerSDCardPath 路径是：" + path, true);
            return;
        }
        Assert.assertTrue("当前 InnerSDCardPath【请手动打开 app 的存储权限】 路径是：" + path, false);
    }

    @Test
    public void testOuterSDCardPath() {
        String path = ZKPathManager.getInstance().getOuterSDCardPath();
        L.d("testOuterSDCardPath: " + path);

        if (!TextUtils.isEmpty(path)) {
            Assert.assertTrue("当前 OuterSDCardPath 路径是：" + path, true);
            return;
        }
        Assert.assertTrue("当前 OuterSDCardPath【请手动打开 app 的存储权限】 路径是：" + path, false);
    }

}
