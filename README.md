# ZhuoKe-SDK

[![](https://jitpack.io/v/ZhuoKeTeam/zhuoke-sdk.svg)](https://jitpack.io/#ZhuoKeTeam/zhuoke-sdk)

ZhuoKe-SDK

1. Maven插件gradle兼容android库项目：

https://github.com/dcendents/android-maven-gradle-plugin

2. 使用最新的 ZhuoKe-SDK ,请务必 在自己的 app 中添加这个：

```
android {
    compileOptions {
        sourceCompatibility '1.8'
        targetCompatibility '1.8'
    }
}
```

具体原因是：okhttp 3.14.1 的代码中使用了 lamda 表达式，所以使用新的 SDK 必须添加上面代码。

3. 依赖最新的 SDK 方式是：

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
	        implementation 'com.github.ZhuoKeTeam:zhuoke-sdk:1.0.7'
}
```