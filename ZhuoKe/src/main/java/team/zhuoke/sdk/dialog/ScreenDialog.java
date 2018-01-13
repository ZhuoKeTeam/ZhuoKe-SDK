package team.zhuoke.sdk.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.widget.TextView;

import team.zhuoke.sdk.R;

/**
 * @author Yunr
 * @date 2018/01/13 15:48
 */
public class ScreenDialog {

    private static AlertDialog dialog;

    public static void show(Context context) {
        if (dialog == null) {
            buildDialog(context);
        }

        dialog.show();
        TextView mMessageView = dialog.findViewById(android.R.id.message);
        mMessageView.setTextColor(context.getResources().getColor(R.color.secondary_text));
    }

    private static void buildDialog(Context context) {
        StringBuilder builder = new StringBuilder();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int widthPx = metrics.widthPixels;//宽度px
        int heightPx = metrics.heightPixels;//高度px
        int dpi = metrics.densityDpi;//密度dp
        String dpiText = getDPIText(dpi);
        String scaledDensity = String.format("%.2f", metrics.scaledDensity);//字体缩放因子


        float xdpi = metrics.xdpi;//宽度密度
        float ydpi = metrics.ydpi;//高度密度

        builder.append("屏幕分辨率:")
                .append(widthPx)
                .append(" × ")
                .append(heightPx)
                .append(" px\n");

        builder.append("密度:")
                .append(dpi)
                .append(" dp / ")
                .append(dpiText)
                .append("\n");

        builder.append("精确密度:")
                .append(xdpi)
                .append(" × ").append(ydpi)
                .append(" dp\n");

//        builder.append("屏幕尺寸:")
//                .append(xInch).append("\"")
//                .append(" × ")
//                .append(yInch).append("\"")
//                .append("\n").append(inch)
//                .append("英寸")
//                .append("\n");

        builder.append("字体缩放比例:")
                .append(scaledDensity)
                .append("\n");


        dialog = new AlertDialog.Builder(context).setTitle("屏幕")
                .setMessage(builder).setCancelable(true).create();

    }

    private static String getDPIText(int dpi) {
        if (dpi <= DisplayMetrics.DENSITY_LOW) {
            return "ldpi";
        } else if (dpi <= DisplayMetrics.DENSITY_MEDIUM) {
            return "mdpi";
        } else if (dpi <= DisplayMetrics.DENSITY_HIGH) {
            return "hdpi";
        } else if (dpi <= DisplayMetrics.DENSITY_XHIGH) {
            return "xhdpi";
        } else if (dpi <= DisplayMetrics.DENSITY_XXHIGH) {
            return "xxhdpi";
        } else {
            return "xxxhdpi";
        }
    }
}
