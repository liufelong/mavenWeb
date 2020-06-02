package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public Connection connection;
    public JDBCConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
            String user = "root";
            String pwd = "nslfl963";

            this.connection = DriverManager.getConnection(url,user,pwd);
            System.out.println("连接数据库成功");
        }catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }

    }
}
