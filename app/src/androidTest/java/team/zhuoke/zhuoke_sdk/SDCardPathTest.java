package team.zhuoke.zhuoke_sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import team.zhuoke.sdk.ZKBase;

/**
 * SDCardPathTest
 * Created by WangQing on 2018/1/11.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class SDCardPathTest {
    Context context;

    @Before
    public void init() {
        context = InstrumentationRegistry.getTargetContext();
        ZKBase.init(context.getApplicationContext(), true);
    }

    @Test
    public void testPath() {
        String path = ZKBase.getAvailablelPath();


        Assert.assertTrue("当前获取的 SD 卡路径是：" + path, false);
    }

}
