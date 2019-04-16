package team.zhuoke.zhuoke_sdk.api;

import retrofit2.Call;
import retrofit2.http.GET;
import team.zhuoke.zhuoke_sdk.bean.ZKBean;
import team.zhuoke.zhuoke_sdk.ZKConstant;

interface ZhuoKeApi {

    @GET(ZKConstant.URL_TEST)
    Call<ZKBean> requestTest();
}
