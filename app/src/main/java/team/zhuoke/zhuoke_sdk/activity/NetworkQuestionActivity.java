package team.zhuoke.zhuoke_sdk.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.manager.ZKConnectionManager;
import team.zhuoke.zhuoke_sdk.R;
import team.zhuoke.zhuoke_sdk.api.UrlConfig;
import team.zhuoke.zhuoke_sdk.api.ZhuoKeApi;
import team.zhuoke.zhuoke_sdk.bean.ZKBean;

public class NetworkQuestionActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "NetworkQuestionActivity";

    @BindView(R.id.tv_request_url)
    TextView tvRequestUrl;
    @BindView(R.id.tv_response_data)
    TextView tvResponseData;
    @BindView(R.id.bt_request)
    Button btRequest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initViews() {
        tvRequestUrl = findViewById(R.id.tv_request_url);
        tvResponseData = findViewById(R.id.tv_response_data);
        btRequest = findViewById(R.id.bt_request);
        btRequest.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvRequestUrl.setText(String.format("%s%s", UrlConfig.getBaseUrl(), UrlConfig.URL_TEST));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_request:

                ZKConnectionManager connectionManager = ZKConnectionManager.getInstance();
                connectionManager.setBaseUrl(UrlConfig.getBaseUrl());

                ZhuoKeApi zhuoKeApi = (ZhuoKeApi) connectionManager.getApi(ZhuoKeApi.class);
                zhuoKeApi.requestTest().enqueue(new Callback<ZKBean>() {
                    @Override
                    public void onResponse(Call<ZKBean> call, Response<ZKBean> response) {
                        tvResponseData.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ZKBean> call, Throwable t) {
                        tvResponseData.setText(t.getMessage());
                    }
                });

            break;
        }
    }
}
