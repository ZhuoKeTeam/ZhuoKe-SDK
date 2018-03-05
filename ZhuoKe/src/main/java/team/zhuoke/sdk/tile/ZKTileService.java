package team.zhuoke.sdk.tile;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import team.zhuoke.sdk.R;

/**
 * @author Yunr
 * @date 2018/03/05 10:24
 */
@TargetApi(Build.VERSION_CODES.N)
public class ZKTileService extends TileService {

    @Override
    public void onDestroy() {
        Log.e("-----", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();

        Log.e("-----", "onTileAdded");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.e("-----", "onTileRemoved");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Log.e("-----", "onStartListening" + getQsTile().getState());
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.e("-----", "onStopListening" + getQsTile().getState());
    }

    boolean switcher = true;

    @Override
    public void onClick() {
        super.onClick();
        Log.e("-----", "onClick" + switcher);
        Tile tile = getQsTile();
        if (switcher) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_timer_off));
        } else {
            tile.setState(Tile.STATE_ACTIVE);
            tile.setIcon(Icon.createWithResource(this, R.drawable.ic_timer));

//            AlertDialog dialog = new AlertDialog.Builder(this)
//                    .setTitle("卓客时间")
//                    .setMessage("这是自定义选项点击后弹出的dialog")
//                    .create();
//            showDialog(dialog);

        }

        switcher = !switcher;
        //更新状态
        tile.updateTile();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("-----", "onBind");
        return super.onBind(intent);
    }
}
