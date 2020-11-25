package com.sforits.Utils.Archive;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/11/25 12:34
 * Created by IntelliJ IDEA.
 */
public class ZipTest {
    @Test
    public void test() throws FileNotFoundException {
        /** 测试压缩方法1  */
        FileOutputStream fos1 = new FileOutputStream(new File("D:/Test/mytest01.zip"));
        Zip.toZip("D:/Test/zip", fos1, true);

        /* 测试压缩方法2
         *  这个方法行不通 拒绝访问
         * */
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("D:/Test/zip"));
        fileList.add(new File("D:/Test/list"));
        FileOutputStream fos2 = new FileOutputStream(new File("D:/Test/mytest02.zip"));
        Zip.toZip(fileList, fos2);
    }

    @Test
    public void test01_toZip() throws FileNotFoundException {
        OutputStream outputStream = new ZipOutputStream(new FileOutputStream("D:/Test/list"));
        Zip.toZip("", outputStream, true);
    }
}
