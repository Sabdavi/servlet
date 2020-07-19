package com.saeid.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyServlet extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("datasource");
        Statement statement = null;
        Connection connection = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String sql = "SHOW TABLES";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String tableName = resultSet.getString("Tables_in_Test");
                stringBuilder.append(tableName+"\n");
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + stringBuilder.toString() + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }
}
