package team.zhuoke.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by Doraemon on 2018/1/15.
 * Read from: http://www.trinea.cn/android/android-7-1-shortcuts-desc/
 */

public class ShortCutsCreator {
    private Context mContext;
    ShortcutManager shortcutManager;

    public ShortCutsCreator(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 添加一个 什么样的场景会用?
     *
     * @param id
     * @param shortlabel
     * @param longlabel
     * @param resId
     * @param disableMessage
     * @param pendingIntent
     */
    public void createShortCut(String id, String shortlabel,
                               String longlabel, @DrawableRes int resId,
                               String disableMessage, Intent pendingIntent) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return;
        }
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(mContext, "id不可为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(shortlabel)) {
            Toast.makeText(mContext, "shortlabel不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(shortlabel)) {
            Toast.makeText(mContext, "longlabel不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(disableMessage)) {
            Toast.makeText(mContext, "disableMessage不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pendingIntent == null) {
            Toast.makeText(mContext, "pendingIntent不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        shortcutManager = mContext.getSystemService(ShortcutManager.class);
        ShortcutInfo shortcut = new ShortcutInfo.Builder(mContext, id)
                .setShortLabel(shortlabel)
                .setDisabledMessage(disableMessage)
                .setLongLabel(longlabel)
                .setIcon(Icon.createWithResource(mContext, resId))//可选
                .setIntent(pendingIntent)//必须有action,new Intent(Intent.ACTION_VIEW, Uri.parse("http://zkteam.cc/")
                .build();
        if (shortcutManager.getDynamicShortcuts().size() + shortcutManager.getManifestShortcuts().size() < shortcutManager.getMaxShortcutCountPerActivity()) {
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
        } else {
            Toast.makeText(mContext, "不能再添加", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 动态禁用
     *
     * @param id
     */
    public void disableShortCut(String id) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return;
        }
        if (shortcutManager == null)
            shortcutManager = mContext.getSystemService(ShortcutManager.class);
        shortcutManager.disableShortcuts(Arrays.asList(id));

    }
}
