package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String name;
    private String password;

    public User() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("驱动加载成功");
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test_db?characterEncoding=UTF-8";
        String user = "root";
        String pwd = "nslfl963";
        return DriverManager.getConnection(url,user,pwd);
    }

    public String checkForRigist (){
        String sql = "select * form USER where name = ?";
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "注册成功";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
