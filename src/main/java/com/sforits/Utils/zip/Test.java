package com.sforits.Utils.zip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Test {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //需要压缩的文件或目录的路径
        File file = new File("D:/Test/zip");
        if (file.canRead()) {
            //FileOutputStream的文件路径必须带后缀，不然会出现“文件无法访问的异常”
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file.getPath() + ".zip"));
            //找到最后一个‘/’的位置，以便取出当前的文件名或目录名
            Zip(file.getPath(), file.getPath().lastIndexOf("//"), out);
            out.closeEntry();
            out.close();

        } else {
            System.out.println("file can not read.");
        }
    }

    public static void Zip(String path, int baseindex, ZipOutputStream out) throws IOException {
        //要压缩的目录或文件
        File file = new File(path);
        File[] files;

        if (file.isDirectory()) {   //  若是目录，则列出所有的子目录和文件
            files = file.listFiles();
        } else {    //  若为文件，则files数组只含有一个文件
            files = new File[1];
            files[0] = file;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                //  去掉压缩根目录以上的路径串，一面ZIP文件中含有压缩根目录父目录的层次结构
                String pathname = f.getPath().substring(baseindex + 1);
                //  空目录也要新建一个项
                out.putNextEntry(new ZipEntry(pathname + "/"));
                //  递归
                Zip(f.getPath(), baseindex, out);
            } else {
                //  去掉压缩根目录以上的路径串，一面ZIP文件中含有压缩根目录父目录的层次结构
                String pathname = f.getPath().substring(baseindex + 1);
                // 新建项为一个文件
                out.putNextEntry(new ZipEntry(pathname));
                //  读文件
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
                int c;
                while ((c = in.read()) != -1)
                    out.write(c);
                in.close();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 把指定源文件压缩到目标文件中，使用的是zip格式<br/>
     *
     * @param src  要压缩的文件
     * @param dest 压缩后生成的文件(zip格式)
     */
    public static void zipFile(File src, File dest) throws RuntimeException, IOException {
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
        byte[] b = new byte[8192];
        try {
            bis = new BufferedInputStream(new FileInputStream(src));// 针对源文件创建带缓冲的字节输入流来读数据
            zos = new ZipOutputStream(new FileOutputStream(dest)); // 针对目标文件创建压缩输出流
            // 一个被压缩文件就是压缩文件中的一个条目
            zos.putNextEntry(new ZipEntry(src.getName())); // 开始写入新的 ZIP
            // 文件条目并将流定位到条目数据的开始处
            for (int len = 0; (len = bis.read(b)) != -1; ) {
                zos.write(b, 0, len);
            }
            zos.flush(); // 刷新输出流
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //  IOHelper.close(bis, zos);
            bis.close();
            zos.close();
        }
    }

    /**
     * 把指定目录下的所有子孙目录和文件全部压缩到指定目标文件(zip格式)中 ，目标文件名为"源目录名.zip"
     *
     * @param srcDir
     * @param destBasePath
     */
    public static void zipDir(File srcDir, File destBasePath) throws IOException {
        zipDir(srcDir, destBasePath, null);
    }

    /**
     * 把指定目录下的所有子孙目录和文件全部压缩到指定目标文件(zip格式)中
     *
     * @param srcDir       要压缩的目录
     * @param destBasePath 存放zip文件的目录
     * @param destFileName 目标文件的名。如果没有提供，目标文件的名为"源目录名.zip"
     */
    public static void zipDir(File srcDir, File destBasePath,
                              String destFileName) throws RuntimeException, IOException {
        if (!srcDir.exists() || !srcDir.isDirectory()) {
            return;
        }
        byte[] b = new byte[8192];
        ZipOutputStream zos = null;
        BufferedInputStream bis = null;
        try {
            if (null == destFileName || "".equals(destFileName)) {
                destFileName = srcDir.getName() + ".zip";
            }
            zos = new ZipOutputStream(new FileOutputStream(new File(
                    destBasePath, destFileName)));
            List<File> subFiles = new ArrayList<File>();
            getAllSubFiles(srcDir, subFiles);
            if (subFiles.size() > 0) { // 有子文件
                for (File file : subFiles) { // 压缩每个子文件
                    bis = new BufferedInputStream(new FileInputStream(file));
                    // 往zip中添加一个条目
                    String zipEntryName = file.getAbsolutePath();
                    // 去掉指定目录的父目录路径名
                    File parent = srcDir.getParentFile(); // 得到指定目录的父目录
                    if (parent != null) {
                        String parentName = parent.getAbsolutePath();
                        zipEntryName = zipEntryName.substring(zipEntryName
                                .indexOf(parentName)
                                + parentName.length() + 1);
                    }
                    zos.putNextEntry(new ZipEntry(zipEntryName));
                    for (int len = 0; (len = bis.read(b)) != -1; ) {
                        zos.write(b, 0, len);
                    }
                    zos.flush();
                    bis.close();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
//            IOHelper.close(zos);

            zos.close();
        }
    }

    /**
     * 解压缩指定压缩文件(zip格式)
     *
     * @param src          要解压缩的文件
     * @param destBasePath 解压缩后的文件存放的目录
     */
    public static void unZip(File src, File destBasePath)
            throws RuntimeException, IOException {
        ZipInputStream zis = null;
        BufferedOutputStream bos = null;
        byte[] b = new byte[8192];
        try {
            zis = new ZipInputStream(new FileInputStream(src));
            // 把压缩文件中获取到每个条目
            for (java.util.zip.ZipEntry ze = null; (ze = zis.getNextEntry()) != null; ) {
                // 压缩文件中的每个条目就对应目标目录下的一个文件
                File file = new File(destBasePath, ze.getName());
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) { // 如果父目录下存在，就先创建
                    file.getParentFile().mkdirs();
                }
                // 针对文件创建出一个输出流
                bos = new BufferedOutputStream(new FileOutputStream(file));
                for (int len = 0; (len = zis.read(b)) != -1; ) {
                    bos.write(b, 0, len);
                }
                bos.flush();
                bos.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
//            IOHelper.close(zis);

            zis.close();
        }
    }

    /**
     * 递归获取指定目录下的所有子孙文件
     *
     * @param baseDir     要递归的目录
     * @param allSubFiles 存放所有子孙文件的列表
     */
    public static void getAllSubFiles(File baseDir, List<File> allSubFiles) {
        if (baseDir.exists()) { // baseDir存在
            if (baseDir.isDirectory()) { // 是目录
                File[] subFiles = baseDir.listFiles(); // 获取它的所有子目录和了文件
                int len = subFiles == null ? 0 : subFiles.length;
                for (int i = 0; i < len; i++) {
                    getAllSubFiles(subFiles[i], allSubFiles); // 递归调用
                }
            } else {
                allSubFiles.add(baseDir);
            }
        }
    }

}
