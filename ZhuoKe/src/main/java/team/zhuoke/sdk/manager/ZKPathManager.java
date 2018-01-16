package team.zhuoke.sdk.manager;

import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SDCardUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import team.zhuoke.sdk.ZKBase;

/**
 * 路径获取
 * Created by WangQing on 2018/1/15.
 */
public class ZKPathManager {

    private static ZKPathManager instance = null;

    private ZKPathManager() {
    }

    public static ZKPathManager getInstance() {
        if (instance == null) {
            synchronized (ZKPathManager.class) {
                ZKPathManager temp = instance;
                if (temp == null) {
                    temp = new ZKPathManager();
                    instance = temp;
                }
            }
        }
        return instance;
    }

    /**
     * 获取 当前 App 中可用的所有存储目录, 未校验所有的目录读写权限
     *
     * 【仅仅提供 读取的目录，不支持向该目录写入。】 如果需要向目录中写入文件，请使用以下方法：
     *
     * getAllAvailablePath()
     *
     * getDefaultPath()
     * getAvailablePath()
     * getPrivatePath()
     * getPublicSDCardPath()
     * getOuterSDCardPath()
     * getInnerSDCardPath()
     *
     * @return  可用的所有存储目录,不支持向该目录写入。
     */
    public ArrayList<String> getAllPath() {
        ArrayList<String> availablePaths = new ArrayList<>();

//        外置 SD 卡
        List<String> outerSdCardPaths = SDCardUtils.getSDCardPaths(true);
//        内置 SD 卡
        List<String> innerSdCardPaths = SDCardUtils.getSDCardPaths(false);
//        外部公开  App 在 SD 卡的目录
        String publicSDCardPath = getPublicSDCardPath();
//        app 内部私有目录
        String privatePath = getPrivatePath();

        if (outerSdCardPaths != null && outerSdCardPaths.size() > 0) {
            availablePaths.addAll(outerSdCardPaths);
        }

        if (innerSdCardPaths != null && innerSdCardPaths.size() > 0) {
            availablePaths.addAll(innerSdCardPaths);
        }

        if (!TextUtils.isEmpty(publicSDCardPath)) {
            availablePaths.add(publicSDCardPath);
        }

        if (!TextUtils.isEmpty(privatePath)) {
            availablePaths.add(privatePath);
        }

        return availablePaths;
    }


    /**
     * 获取可用的所有目录, 已经校验所有的读写权限。但是会忽略某些不可写入的目录。
     *
     * @return 可用的所有目录
     */
    public ArrayList<String> getAllAvailablePath() {
        ArrayList<String> availablePaths = new ArrayList<>();

//        外置 SD 卡
        List<String> outerSdCardPaths = SDCardUtils.getSDCardPaths(true);
//        内置 SD 卡
        List<String> innerSdCardPaths = SDCardUtils.getSDCardPaths(false);
//        外部公开  App 在 SD 卡的目录
        String publicSDCardPath = getPublicSDCardPath();
//        app 内部私有目录
        String privatePath = getPrivatePath();

        if (outerSdCardPaths != null && outerSdCardPaths.size() > 0) {
            for (String outerPath: outerSdCardPaths) {
                String path = checkSDPath(outerPath);

                if (!TextUtils.isEmpty(path)) {
                    availablePaths.add(path);
                }
            }
        }

        if (innerSdCardPaths != null && innerSdCardPaths.size() > 0) {
            for (String innerPath: innerSdCardPaths) {
                String path = checkSDPath(innerPath);

                if (!TextUtils.isEmpty(path)) {
                    availablePaths.add(path);
                }
            }
        }

        if (!TextUtils.isEmpty(publicSDCardPath)) {
            availablePaths.add(publicSDCardPath);
        }

        if (!TextUtils.isEmpty(privatePath)) {
            availablePaths.add(privatePath);
        }

        return availablePaths;
    }

    /**
     * 获取当前可用的存储空间
     * 顺序是：
     * 外置 SD 卡根目录 > 内置 SD 卡根目录 > 当前可用的 SD 卡公开目录 > App  的私有目录
     *
     * @return 返回一个可以使用的存储空间。
     */
    public String getDefaultPath() {
        return getAvailablePath();
    }

    /**
     * 获取当前可用的存储空间
     * 顺序是：
     * 外置 SD 卡根目录 > 内置 SD 卡根目录 > 当前可用的 SD 卡公开目录 > App  的私有目录
     *
     * @return 返回一个可以使用的存储空间。
     */
    public String getAvailablePath() {
        String path = null;
        try {
            path = getOuterSDCardPath();

            if (TextUtils.isEmpty(path)) {
                path = getInnerSDCardPath();

                if (TextUtils.isEmpty(path)) {
                    path = getPublicSDCardPath();

                    if (TextUtils.isEmpty(path)) {
                        path = getPrivatePath();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 获取 App 的私有存储路径
     * @return 私有存储路径
     */
    public String getPrivatePath() {
        File file = ZKBase.getContext().getFilesDir();

        if (file != null) {
            String path = file.getPath();
            return checkSDPath(path);
        }
        return null;
    }

    /**
     * 获取 App 的 SD 卡的公开存储路径
     * @return SD卡公开存储路径
     */
    public String getPublicSDCardPath() {
        File file = ZKBase.getContext().getExternalFilesDir(null);

        if (file != null) {
            String path = file.getPath();
            return checkSDPath(path);
        }
        return null;
    }

    /**
     * 获取 app 的外置 SD 的路径
     * @return app 的外置 SD 的路径
     */
    public String getOuterSDCardPath() {
        return getSdDirPathForList(SDCardUtils.getSDCardPaths(true));
    }

    /**
     * 获取 app 的内置 SD 的路径
     * @return app 的内置 SD 的路径
     */
    public String getInnerSDCardPath() {
        return getSdDirPathForList(SDCardUtils.getSDCardPaths(false));
    }

    /**
     * 检测 SD 卡的一组数据是否有可用的
     *
     * @return 返回可用的路径
     */
    private static String getSdDirPathForList(List<String> sdCardPaths) {
        String path = null;
        for (String sdCardPath : sdCardPaths) {
            String sdDirPath = checkSDPath(sdCardPath);
            if (!TextUtils.isEmpty(sdCardPath)) {
                path = sdDirPath;
                break;
            }
        }
        return path;
    }

    /**
     * 检测 SD 卡是否可用
     *
     * @param sdCardDirPath SD 卡的路径
     * @return 如可用返回 SD 卡的路径，否则返回 null。
     */
    private static String checkSDPath(String sdCardDirPath) {
        String tempFile = sdCardDirPath + "/temp";
        boolean b = FileUtils.createOrExistsFile(tempFile);
        if (b) {
            boolean deleteState = FileUtils.deleteFile(tempFile);
            if (deleteState)
                return sdCardDirPath;
        }
        return null;
    }

}
