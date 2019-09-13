package com.ky.gps.test.util;

import com.ky.gps.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileUtilTest {

    @Test
    public void test01(){
        List<File> fileList = FileUtil.readFiles(null);
        for (File file : fileList) {
            System.out.println(file.getName());
            System.out.println(file.getAbsolutePath());
        }
    }
}
