# ZhuoKe-SDK

[![](https://jitpack.io/v/ZhuoKeTeam/zhuoke-sdk.svg)](https://jitpack.io/#ZhuoKeTeam/zhuoke-sdk)

ZhuoKe-SDK

## 使用指南

### 依赖最新的 SDK 方式是：

Step 1.
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. 
```
dependencies {
    implementation 'com.github.ZhuoKeTeam:zhuoke-sdk:1.0.8'
}
```

Step 3.
```
android {
    compileOptions {
        sourceCompatibility '1.8'
        targetCompatibility '1.8'
    }
}
```

具体原因是：okhttp 3.14.1 的代码中使用了 lamda 表达式，所以使用新的 SDK 必须添加上面代码。



## 高级用法

1. 引入 versions.gradle

```
buildscript {
    //apply from: 'versions.gradle'
    apply from: 'https://www.zkteam.cc/android/gradle/versions.gradle' //推荐使用在线版本，更新及时，还能随时查看。
    ...
}
```

2. 导入常用的 maven 库，内部包含 google()，jcenter()，mavenCentral()，jitpack.io，maven.aliyun.com

项目根目录下的：gradle.build
```
buildscript {
    ...
    addRepos(repositories)
    ...
}
```

```
allprojects {
    addRepos(repositories)
}
```

只要这个一个即可

3. 引入构建版本

```
android {

    compileSdkVersion build_versions.target_sdk
    buildToolsVersion build_versions.build_tools
    
    defaultConfig {
        minSdkVersion build_versions.min_sdk
        targetSdkVersion build_versions.target_sdk
    }
}
```

4. 引用依赖

然后就可以使用库中的所有资源，如果要导入相关库，请直接使用根目录下的 version.gradle，使用方式如下：

```
dependencies {

    // okhttp
    implementation deps.okhttp
    implementation deps.okhttp_interceptor
    
}
```

5. 在自定义的 Application 类中添加相关功能：

```
    ZKManager.getInstance().init(this);  //必须
    ZKManager.getInstance().initStethoForApplicaiton(this); //可选 手机 USB 连接电脑，可以通过 Chrome 中输入：chrome://inspect,查看网络，资源，数据库，SP 文件
    ZKManager.getInstance().initTakePhotoForApplication();  //推荐添加 兼容7.0以上无法拍照问题

    ZKManager.getInstance().setBaseUrl("http://www.gdky005.com"); //必选，设置当前 app 请求域名的 Url
```

6. 网络请求范例

参考 app  model 中的使用方式：

```
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
```

##### 备注：
Maven插件gradle兼容android库项目：

https://github.com/dcendents/android-maven-gradle-plugin