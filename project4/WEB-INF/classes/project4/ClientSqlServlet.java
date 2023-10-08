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
public class ClientSqlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sqlCommand = request.getParameter("sqlCommand");

     
        Properties prop = new Properties();
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/lib/client.properties");
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
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            if (sqlCommand.trim().toLowerCase().startsWith("select")) {
                ResultSet rs = stmt.executeQuery(sqlCommand);
                result = resultSetToHtmlTable(rs);
            } else {
                int rowsAffected = stmt.executeUpdate(sqlCommand);
                result = "Rows affected: " + rowsAffected;
            }
        } catch (SQLException e) {
            result = "Error: " + e.getMessage();
        }

    
        HttpSession session = request.getSession();
        session.setAttribute("result", result);
        request.getRequestDispatcher("/clientHome.jsp").forward(request, response);
    }

    private String resultSetToHtmlTable(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        sb.append("<table><thead><tr>");
        for (int i = 1; i <= columnCount; i++) {
            sb.append("<th>").append(metaData.getColumnLabel(i)).append("</th>");
        }
        sb.append("</tr></thead>");
      
        sb.append("<tbody>");
        while (rs.next()) {
            sb.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                sb.append("<td>").append(rs.getString(i)).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</tbody></table>");
        return sb.toString();
    }
}
