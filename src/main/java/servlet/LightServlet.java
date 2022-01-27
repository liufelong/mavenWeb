package servlet;

import com.google.gson.Gson;
import javaClass.LightBody;
import javaClass.LightMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "LightServlet")
public class LightServlet extends HttpServlet {
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
        LightBody body = gson.fromJson(jsonString,LightBody.class);
        String btnTag = body.getBtnTag();
//        String path = "C:/xampp/tomcat/webapps/conf.txt";
        String path = "/Users/liufeilong/Desktop/ideaProtects/mavenWeb/target/conf.txt";
        LightMode lightMode = readTextJson(path);

        System.out.println(btnTag);
        if (btnTag.equals("1")) {
            lightMode.setYunxi(lightMode.getYunxi().equals("1") ? "0" : "1");
        }else if (btnTag.equals("2")){
            lightMode.setYuanlan(lightMode.getYuanlan().equals("1") ? "0" : "1");
        }else if (btnTag.equals("3")){
            lightMode.setShangzhuang(lightMode.getShangzhuang().equals("1") ? "0" : "1");
        }else if (btnTag.equals("4")){
            lightMode.setGongfa(lightMode.getGongfa().equals("1") ? "0" : "1");
        }else if (btnTag.equals("5")){
            lightMode.setNiaokan(lightMode.getNiaokan().equals("1") ? "0" : "1");
        }else if (btnTag.equals("7")){
            lightMode.setAlllight(lightMode.getAlllight().equals("1") ? "0" : "1");
        }else if (btnTag.equals("8")) {
            lightMode.setLeft("1");
        }else if (btnTag.equals("9")) {
            lightMode.setRight("1");
        }


        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if (writeTextJson(path,gson.toJson(lightMode))) {
            writer.println(gson.toJson(lightMode));
        }else  {
            writer.println("文件写入错误");
        }

//        Writer writer = response.getWriter();
//        writer.println(jsonString);

    }

    public LightMode readTextJson(String text) {
        File file = new File(text);
        String jsonString = "";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                jsonString += line;
            }

//            System.out.println(jsonString);
            Gson gson = new Gson();
            LightMode lightMode = gson.fromJson(jsonString,LightMode.class);
            return lightMode;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Boolean writeTextJson(String path, String text) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            out.write(text);
            out.close();
            return true;
        } catch (IOException e) {

        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
