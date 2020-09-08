package com.sforits.Utils.DBSQL;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @Author：sforits
 * @E-mail：sforits@gmail.com
 * @Date：2020/6/8-8:24
 */
public class DBStart {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   // com.microsoft.sqlserver.jdbc.SQLServerDriver
    private static String url = "jdbc:sqlserver://192.168.252.134:1433";  // jdbc:sqlserver://192.168.252.134:1433;DatavaseName=bmdb
    private static String username = "sa";
    private static String password = "root";

    /**
     * 创建数据库链接
     * 需要引入依赖
     *
     * @return
     */
    public Statement getStatement() {
        Connection con = null;
        Statement statement = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                statement = con.createStatement();
            } else {
                System.out.println("数据库链接为空, 请检查数据库连接属性");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return statement;
    }


    /**
     * 加载本地sql到数据库端执行
     * <p>
     * 使用的是Mybatis提供的ScriptRunner类
     */
    public static void exeSQLSript(String sqlPath) {

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);

            ScriptRunner runner = new ScriptRunner(conn);
            Resources.setCharset(Charset.forName("GBK")); // 设置字符集,不然中文乱码插入错误
            runner.setLogWriter(null);      //  设置是否输出日志

            runner.runScript(Resources.getResourceAsReader(sqlPath));
            runner.closeConnection();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
