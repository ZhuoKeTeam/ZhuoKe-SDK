package team.zhuoke.zhuoke_sdk.api;

import retrofit2.Call;
import retrofit2.http.GET;
import team.zhuoke.zhuoke_sdk.bean.ZKBean;

public interface ZhuoKeApi {

    @GET(UrlConfig.URL_TEST)
    Call<ZKBean> requestTest();
}
