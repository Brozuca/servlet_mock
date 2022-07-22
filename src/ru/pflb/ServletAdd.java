package ru.pflb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.pflb.logic.Model;
import ru.pflb.logic.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    int count = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                jb.append(line);
            }
        }catch (Exception e){
            System.out.println("Error " + e);
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(jsonObject.get("id").getAsString());
        int fromUserId = Integer.parseInt(jsonObject.get("fromUserId").getAsString());
        int toUserId = Integer.parseInt(jsonObject.get("toUserId").getAsString());
        int amount = Integer.parseInt(jsonObject.get("amount").getAsString());
        String currency = jsonObject.get("currency").getAsString();

        Transaction transaction = new Transaction(id, fromUserId, toUserId, amount, currency);
        model.add(count++, transaction);
    }


}
