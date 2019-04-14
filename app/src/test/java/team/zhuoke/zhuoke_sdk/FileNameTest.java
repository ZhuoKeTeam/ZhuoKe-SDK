package team.zhuoke.zhuoke_sdk;

import com.blankj.utilcode.util.FileUtils;

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * FileNameTest
 * Created by WangQing on 2018/1/23.
 */
public class FileNameTest {

    private static final String TAG = "FileNameTest";

    @Test
    public void test_cast() {
//        String path = "/Users/WangQing/Android_Pro/KaiDunPro/app/src/main/res";
        String path = "/Users/WangQing/Desktop/pic";
        dirList(path);
    }

    private void dirList(String path) {
        List<File> list = FileUtils.listFilesInDir(path, true);

        if (list == null)
            return;

        for (File f : list) {

            if (f.isDirectory()) {
                dirList(f.getPath());
                continue;
            }

            String fileName = FileUtils.getFileName(f);

            if (!fileName.toLowerCase().equals(fileName) || fileName.contains("-")) {
                String oldFileName = fileName;

                if (fileName.contains("-")) {
                    fileName = fileName.replaceAll("-", "_");
                }

                String newFileName = fileName.toLowerCase();

                File file = new File(f.getParent() + "/" + newFileName);

                boolean b = f.renameTo(file);

                System.out.println("test_cast: 原来的文件名是：" + oldFileName + ", 新文件名是：" + newFileName + ", rename state:" + b);
            }
        }
    }
}
