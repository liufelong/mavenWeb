package servlet;

import com.google.gson.Gson;
import javaClass.User;
import toolClass.ErrorClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "RegsiterServlet")
public class RegsiterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String jsonString = builder.toString();
        System.out.println(jsonString);

        Gson gson = new Gson();
        User user = gson.fromJson(jsonString,User.class);

        ErrorClass errorClass = user.checkForRigist();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
//        Writer writer = response.getWriter();
        writer.println(gson.toJson(errorClass));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
