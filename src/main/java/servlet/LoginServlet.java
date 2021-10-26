package servlet;


import com.google.gson.Gson;
import javaClass.User;
import toolClass.CheckUser;
import toolClass.ErrorClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();

        String jsonString = stringBuilder.toString();
        System.out.println(jsonString);

        Gson gson = new Gson();
        User user = gson.fromJson(jsonString,User.class);
        System.out.println(user.getName());
        CheckUser checkUser = new CheckUser();
        ErrorClass errorClass = checkUser.checkForLogin(user);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(gson.toJson(errorClass));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
