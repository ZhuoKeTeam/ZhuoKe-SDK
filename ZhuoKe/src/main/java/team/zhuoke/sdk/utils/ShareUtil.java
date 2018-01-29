package team.zhuoke.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;

/**
 * ShareUtil
 * Created by WangQing on 2018/1/29.
 */

public class ShareUtil {

    public static void shareText(Context context, String text, String title) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(Intent.createChooser(intent, title));
        } catch (Exception e) {
            e.printStackTrace();
            sendError(context);
        }
    }

    public static void shareImage(Context context, Uri uri, String title) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/png");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(Intent.createChooser(intent, title));
        } catch (Exception e) {
            e.printStackTrace();
            sendError(context);
        }
    }

    public static void sendEmail(Context context, String title, String emailAddress) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailAddress));
            context.startActivity(Intent.createChooser(intent, title));
        } catch (Exception e) {
            e.printStackTrace();
            sendError(context);
        }
    }

    public static void sendMoreImage(Context context, ArrayList<Uri> imageUris, String title) {
        try {
            Intent mulIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            mulIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            mulIntent.setType("image/jpeg");
            context.startActivity(Intent.createChooser(mulIntent, title));
        } catch (Exception e) {
            e.printStackTrace();
            sendError(context);
        }
    }

    public static void sendError(Context context) {
        ToastUtils.showShort("分享错误");
    }
}
