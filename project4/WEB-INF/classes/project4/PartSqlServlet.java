/* Name: Olivier Ambroise
 Course: CNT 4714 – Spring 2023 – Project Four
 Assignment title: A Three-Tier Distributed Web-Based Application
 Date: April 23, 2023
*/

package project4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

@SuppressWarnings("serial")
public class PartSqlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pnum = request.getParameter("pnum");
        String pname = request.getParameter("pname");
        String color = request.getParameter("color");
        String weight = request.getParameter("weight");
        String city = request.getParameter("city");

      
        Properties prop = new Properties();
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/lib/dataentry.properties");
        prop.load(inputStream);

        String driverClass = prop.getProperty("MYSQL_DB_DRIVER_CLASS");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error loading MySQL driver: " + e.getMessage(), e);
        }

        String url = prop.getProperty("MYSQL_DB_URL");
        String user = prop.getProperty("MYSQL_DB_USERNAME");
        String password = prop.getProperty("MYSQL_DB_PASSWORD");

      
        String result = "";
        String sqlCommand = "INSERT INTO parts (pnum, pname, color, weight, city) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {
            pstmt.setString(1, pnum);
            pstmt.setString(2, pname);
            pstmt.setString(3, color);
            pstmt.setString(3, weight);
            pstmt.setString(4, city);
            result = "New supplier record: (" + pnum + ", " + pname + ", " + color + ", " + weight + ", " + city + ") - successfully entered into the database.";
        } catch (SQLException e) {
            result = "Error: " + e.getMessage();
        }

        // Return result to JSP
        HttpSession session = request.getSession();
        session.setAttribute("result", result);
        request.getRequestDispatcher("/data.jsp").forward(request, response);
    }
}
