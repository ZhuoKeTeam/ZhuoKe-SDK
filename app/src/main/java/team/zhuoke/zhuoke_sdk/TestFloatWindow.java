package team.zhuoke.zhuoke_sdk;

import android.view.View;
import android.widget.TextView;

import team.zhuoke.sdk.window.ZKBaseFloatWindow;

/**
 * @author Yunr
 * @date 2018/01/16 16:36
 */
public class TestFloatWindow extends ZKBaseFloatWindow<String> {

    private TextView textView;
    private TextView btnView;

    public TestFloatWindow() {
        super(R.layout.float_layout, 200, 200);
    }

    @Override
    protected void initView(View contentView) {
        textView = contentView.findViewById(R.id.test_window_text);
        btnView = contentView.findViewById(R.id.test_window_btn);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    @Override
    public void update(String s) {
        textView.setText(s);
    }
}
