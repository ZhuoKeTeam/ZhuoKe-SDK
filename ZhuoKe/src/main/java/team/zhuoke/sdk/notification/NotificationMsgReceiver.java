package team.zhuoke.sdk.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.lang.reflect.Method;

import static team.zhuoke.sdk.notification.NotificationBuilder.NOTIFICATION_CLICK;

/**
 * @author Yunr
 * @date 2018/03/02 14:28
 */
public final class NotificationMsgReceiver extends BroadcastReceiver {
    private NotificationItemClickListener clickListener;

    public NotificationMsgReceiver(NotificationItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().contentEquals(NOTIFICATION_CLICK)) {
            return;
        }

        if (clickListener == null) return;

        collapseStatusBar(context);
        clickListener.onItemClicked((ItemDataBean) intent.getSerializableExtra("data"));
    }

    /**
     * 收起通知栏
     *
     * @param context
     */
    public static void collapseStatusBar(Context context) {
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;
            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
