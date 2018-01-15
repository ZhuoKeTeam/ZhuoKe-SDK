package team.zhuoke.sdk.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.Utils;

/**
 * @author Yunr
 * @date 2018/01/15 10:56
 */
public  class ZKBaseDialog extends AlertDialog.Builder {

    public ZKBaseDialog(@NonNull Context context) {
        super(context);
    }

    public ZKBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public AlertDialog create() {
        setPositiveButton("复制", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                copy();
            }
        });
        setNegativeButton("分享", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                share();
            }
        });
        return super.create();
    }

      void copy(){

      }

      void share(){


      }
}
