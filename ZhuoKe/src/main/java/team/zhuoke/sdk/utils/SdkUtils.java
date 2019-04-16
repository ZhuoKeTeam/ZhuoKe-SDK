package team.zhuoke.sdk.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * sdk 的工具类
 * Created by WangQing on 2017/4/21.
 */
public class SdkUtils {

    /**
     * 当前 sdk 的版本号
     */
    public static final String version = "1.0.0-beta4";

    /**
     * 复制文本
     *
     * @param label
     * @param content
     * @param context
     */
    public static void copyText(String label, String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(label, content);
        cmb.setPrimaryClip(clipData);
    }

}
