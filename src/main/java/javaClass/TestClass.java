package javaClass;

import toolClass.CheckUser;
import toolClass.ErrorClass;

public class TestClass {
    public static void main(String[] args) {
        User user = new User();
        user.setName("admin112");
        user.setPassword("1234");

        CheckUser checkUser = new CheckUser();
        ErrorClass errorClass = checkUser.checkForLogin(user);
        System.out.println(errorClass.getErrorMessage());
    }
}


