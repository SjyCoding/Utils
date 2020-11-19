package com.sforits.Utils.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    // 测试
    public static void main(String[] args) {
        getZip("D:/Test/zip");
    }

    public static void getZip(String path) {
        File file = new File(path);
        File[] files;

        if (file.isDirectory()) {   //  如果是一个目录
            files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1);
            }
        } else {    //  若为文件，则files数组只含有一个文件
            files = new File[1];
            files[0] = file;
        }

    }

    public static void addZip() {

    }
}
