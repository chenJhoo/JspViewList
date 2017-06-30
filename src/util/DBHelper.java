package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by cjh on 2017-06-07.
 */
public class DBHelper {
    private static final String driver="com.mysql.jdbc.Driver";//数据库驱动
    private static final String URL="jdbc:mysql://127.0.0.1:3306/zeze";
    private static final String NAME="root";//数据库用户名
    private static final String PASSWORD="84840016";//数据库密码

    private static Connection conn=null;
    //静态代码块负责加载驱动
    static {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //单例模式返回数据库连接对象
    public static Connection getConnection(){
        try {
            if (conn==null){
                conn= DriverManager.getConnection(URL,NAME,PASSWORD);
                return conn;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


public static void main(String []args){
        Connection connection=DBHelper.getConnection();
        if (connection!=null){
            System.out.println("数据库连接正常");
        }else{
            System.out.println("数据库连接异常");
        }
}















}

