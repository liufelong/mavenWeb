package javaClass;

import toolClass.CheckUser;
import toolClass.ErrorClass;
import toolClass.JDBCConnection;

import java.sql.*;

public class User {
    private String name;
    private String password;

    private JDBCConnection jdClass;
    public ErrorClass checkForRigist (){
        CheckUser checkUser = new CheckUser();
        ErrorClass errorClass = checkUser.checkUserRigist(this);
        System.out.println(errorClass.getErrorMessage());
        if (errorClass.getErrorCode().equals("1")) {
            insertUser();
        }
        return errorClass;
    }

    public void insertUser () {
        String sql = "insert into USERS values(null,?,?,null)";
        try (
                PreparedStatement statement = JDBCConnection.getJDConnection().getConnection().prepareStatement(sql);
                ) {
                statement.setString(1,this.name);
                statement.setString(2,this.password);
                statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
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
