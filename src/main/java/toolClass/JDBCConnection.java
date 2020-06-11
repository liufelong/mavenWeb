package toolClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private Connection connection;
    private static JDBCConnection jdbcConnection = null;
    private JDBCConnection(){
    }

    //单例模式 优点：线程安全，进行双重检查，保证只在实例未初始化前进行同步，效率高 缺点：还是实例非空判断，耗费一定资源
    public static JDBCConnection getJDConnection () {
        if (jdbcConnection == null){
            synchronized (JDBCConnection.class) {
                if (jdbcConnection == null) {
                    jdbcConnection = new JDBCConnection();
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://127.0.0.1:3306/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
                        String user = "root";
                        String pwd = "nslfl963";
                        jdbcConnection.setConnection(DriverManager.getConnection(url,user,pwd));
                        System.out.println("连接数据库成功");
                    }catch (ClassNotFoundException | SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.println("连接数据库失败");
                    }
                }
            }
        }
        return jdbcConnection;
    }
    public Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
}
