package team.zhuoke.sdk.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team.zhuoke.sdk.Constant;
import team.zhuoke.sdk.ZKBase;
import team.zhuoke.sdk.api.ZKApi;

/**
 * ZKConnectionManager 的网络管理类
 */
public class ZKConnectionManager {

    private static final String TAG = "ZKConnectionManager";


    //http://blog.csdn.net/u014695188/article/details/52985514
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();


    private static ZKConnectionManager instance = null;

    private ZKConnectionManager() {
    }

    public static ZKConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ZKConnectionManager.class) {
                ZKConnectionManager temp = instance;
                if (temp == null) {
                    temp = new ZKConnectionManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }


    private Gson getGson() {
        return new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    private Retrofit getRetrofit() {
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        if (ZKBase.isDebug()) {
            //log信息拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置Debug Log模式
            builder.addInterceptor(httpLoggingInterceptor);
        }

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // TODO: 2018/3/6  添加统一的 header
                Request.Builder requestBuilder = original.newBuilder();

//                Map<String, String> map = KDRequestUtils.getHeaderMaps();
//                for (Map.Entry<String, String> entry:
//                        map.entrySet()) {
//                    requestBuilder.header(entry.getKey(), entry.getValue());
//                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    public ZKApi getZKApi() {
        return getRetrofit().create(ZKApi.class);
    }

}
