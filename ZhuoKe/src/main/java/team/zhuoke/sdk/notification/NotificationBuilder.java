package team.zhuoke.sdk.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;

import team.zhuoke.sdk.R;

/**
 * @author Yunr
 * @date 2018/03/02 10:29
 */
public final class NotificationBuilder {

    private Context context;
    private NotificationManagerCompat notificationManager;
    private NotificationMsgReceiver msgReceiver;

    protected static final String NOTIFICATION_CLICK = "NOTIFICATION_CLICK";
    private final RemoteViews parentGroup;
    private final NotificationCompat.Builder builder;
    private int childCount;

    private ArrayList<ItemDataBean> list;
    private boolean isCloseed = false;

    public NotificationBuilder(Context context) {
        this.context = context;
        notificationManager = NotificationManagerCompat.from(context);
        builder = new NotificationCompat.Builder(context, "11");
        builder.setContentTitle("ZhuoKeSDK-Notification Util");//标题
        builder.setContentText("");//内容
        builder.setSmallIcon(R.mipmap.ic_launcher_round);//Icon 如果不设置Icon,Notification不会跳出来
        builder.setAutoCancel(false);//点击以后是否自动清除,true-点击后自动清除,false-点击以后不会自动清除
        builder.setOngoing(true);//设置是否为正在进行的通知
        builder.setOnlyAlertOnce(false);//setOnlyAlertOnce 是否提示一次. true - 如果Notification已经存在状态栏即使在调用notify函数也不会更新.
        //最外层布局Group
        parentGroup = new RemoteViews(context.getPackageName(), R.layout.notification_util_group);
        childCount = -1;
    }

    public void addItem(ItemDataBean itemDataBean) {
        if (list == null) {
            list = new ArrayList<>();
        }

        if (list.size() >= 6) {
            Toast.makeText(context, "噢~工具栏放不下了，删除些再添加吧。", Toast.LENGTH_SHORT).show();
            return;
        }

        list.add(itemDataBean);
        update();
    }

    public void updateItem(ItemDataBean itemDataBean, int index) {
        if (list == null || list.size() <= 0) {
            return;
        }

        list.set(index, itemDataBean);
        update();
    }

    public void updateItem(ItemDataBean itemDataBean) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            ItemDataBean itemBean = list.get(i);
            if (itemBean.getItemId().contentEquals(itemDataBean.getItemId())) {
                list.set(i, itemDataBean);
            }
        }

        update();
    }

    public void removeItem(int index) {
        if (list == null) {
            return;
        }

        list.remove(index);
        update();
    }

    public void removeItem(String id) {
        if (list == null) {
            return;
        }

        for (ItemDataBean itemBean : list) {
            if (itemBean.getItemId().contentEquals(id)) {
                if (list.remove(itemBean)) {
                    update();
                }
            }
        }
    }

    public void removeAllItem() {
        if (list == null) {
            return;
        }

        list = new ArrayList<>();
        update();
    }

    private void update() {
        childCount = -1;
        parentGroup.removeAllViews(R.id.notifiy_item_parent_group);
        for (ItemDataBean itemBean : list) {
            RemoteViews itemView = new RemoteViews(context.getPackageName(), R.layout.notification_item);
            itemView.setTextViewText(R.id.notifiy_item_text, itemBean.getText());
            itemView.setImageViewResource(R.id.notifiy_item_img, itemBean.getImgId());
            Intent intent = new Intent(NOTIFICATION_CLICK);
            intent.putExtra("data", itemBean);
            childCount++;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, childCount, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            itemView.setOnClickPendingIntent(R.id.notification_item_view, pendingIntent);
            parentGroup.addView(R.id.notifiy_item_parent_group, itemView);
        }

        show();
    }

    public void setOnItemClickListener(NotificationItemClickListener clickListener) {
        if (clickListener != null) {
            msgReceiver = new NotificationMsgReceiver(clickListener);
            context.registerReceiver(msgReceiver, new IntentFilter(NOTIFICATION_CLICK));
        }
    }

    private void show() {
        builder.setContent(parentGroup);
        notificationManager.notify(1, builder.build());
        isCloseed = false;
    }

    public void close() {
        if (isCloseed) {
            return;
        }

        isCloseed = true;
        notificationManager.cancel(1);
        parentGroup.removeAllViews(R.id.notifiy_item_parent_group);
        list = new ArrayList<>();
        if (msgReceiver != null) {
            context.unregisterReceiver(msgReceiver);
            msgReceiver = null;
        }
    }
}
