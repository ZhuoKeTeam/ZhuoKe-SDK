package team.zhuoke.zhuoke_sdk.activity;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import team.zhuoke.zhuoke_sdk.MainActivity;
import team.zhuoke.zhuoke_sdk.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;


/**
 * MainActivityTest
 * 参考： https://www.jianshu.com/p/22a09ae7b995
 *
 * Created by WangQing on 2018/1/17.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {

    }

    @Test
    public void testStartEggActivity() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ViewInteraction edit_text = onView(withId(R.id.edit_text));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction button1 = onView(withId(R.id.button1));
        ViewInteraction textButton = onView(withText(R.string.start_egg_text));

//        // TODO: 2018/1/17  这个测试方法很特殊，需要查看文档
//        DataInteraction recyclerView = onData(withId(R.id.recyclerView));
//        recyclerView.perform(scrollTo(), click());

//        textButton.perform(click());
        edit_text.perform(replaceText("Hello WangQing! 和了哦, 巴拉巴拉小魔仙"), closeSoftKeyboard());
//        edit_text.perform(typeText("Jack"),closeSoftKeyboard());

//        edit_text.check(matches(withText("Hello")));
        edit_text.check(matches(isDisplayed()));

        //界面上有一个文本为点击的按钮并点击按钮
        onView(allOf(withText("点击显示 Toast"), instanceOf(Button.class)))
                .check(matches(isDisplayed()))
                .perform(click());


        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 下面的命令会直到出现 非当前应用的窗口出现 Toast，否则不会结束。 // TODO: 2018/1/18  是否有超时时间设置？
        onView(withText("Toast")).inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

}
