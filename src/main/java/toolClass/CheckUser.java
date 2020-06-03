package toolClass;

import javaClass.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CheckUser {

    private JDBCConnection jdbcTool;

    public CheckUser () {
        this.jdbcTool = new JDBCConnection();
    }
    /*
    * @raram 校验用户注册
    * */
    public ErrorClass checkUserRigist (User user) {
        ErrorClass errorClass = checkUsserNull(user);
        if (!errorClass.getErrorMessage().equals("")) {
            return errorClass;
        }

        ArrayList<User> userList = selectFromData(user);

        if (userList.isEmpty()) {
            errorClass.setErrorCode("1");
            errorClass.setErrorMessage("注册成功");
        }else  {
            errorClass.setErrorMessage("用户名已存在");
        }
        return errorClass;
    }

/*
* 登录校验
* */
    public ErrorClass checkForLogin (User user) {
        ErrorClass errorClass = checkUsserNull(user);
        if (!errorClass.getErrorMessage().equals("")) {
            return errorClass;
        }
        ArrayList<User> userList = selectFromData(user);
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User tempUser = iterator.next();
            if (tempUser.getPassword().equals(user.getPassword())){
                errorClass.setErrorMessage("登录成功");
                errorClass.setErrorCode("1");
            }
        }
        if (errorClass.getErrorCode().equals("0")) {
            errorClass.setErrorMessage("用户名或密码错误");
        }
        return errorClass;
    }

/*
* 根据用户名从数据库查询user
* */
    public ArrayList<User> selectFromData(User user) {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "select * from USERS where name = ?";
        try {
            PreparedStatement statement = this.jdbcTool.getConnection().prepareStatement(sql);
            statement.setString(1,user.getName());
            ResultSet resultSet = statement.executeQuery();
            String name = "";

            while (resultSet.next()) {
                User reUser = new User();
                reUser.setName(resultSet.getString("name"));
                reUser.setPassword(resultSet.getString("password"));
               users.add(reUser);
            }

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("读取数据库失败");
        }
        return users;
    }
/*
* 校验User必传项
* */
    public ErrorClass checkUsserNull (User user) {
        ErrorClass errorClass = new ErrorClass("0","");
        if (user.getName().equals("")) {
            errorClass.setErrorMessage("用户名不能为空");
        }else if (user.getPassword().equals("")) {
            errorClass.setErrorMessage("密码不能为空");
        }
        return errorClass;
    }
}
