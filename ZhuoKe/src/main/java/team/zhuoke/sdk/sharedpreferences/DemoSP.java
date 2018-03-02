package team.zhuoke.sdk.sharedpreferences;

import team.zhuoke.sdk.exception.ZKSharePreferencesException;

/**
 * Created by WangQing on 2018/1/25.
 */

public class DemoSP extends ZKSharedPreferences {

    private final String SP_NAME = "zk_bd_access_token";
    public static final String KEY_TEST = "key_access_token";

    @Override
    public String sharedPreferencesFileName() {
        return SP_NAME;
    }

    @Override
    public void put(String key, Object object) {
        try {
            super.put(key, object);
        } catch (ZKSharePreferencesException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key, Object defaultObject) {
        try {
            return super.get(key, defaultObject);
        } catch (ZKSharePreferencesException e) {
            e.printStackTrace();
        }
        return null;
    }
}
