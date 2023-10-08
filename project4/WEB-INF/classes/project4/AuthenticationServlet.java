/* Name: Olivier Ambroise
 Course: CNT 4714 – Spring 2023 – Project Four
 Assignment title: A Three-Tier Distributed Web-Based Application
 Date: April 23, 2023
*/


package project4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Scanner;


@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String credentialsFilePath = request.getServletContext().getRealPath("/WEB-INF/lib/credentials.txt");
        boolean userCredentialsOK = false;
        
        try {
            FileReader fr = new FileReader(credentialsFilePath);
            BufferedReader br = new BufferedReader(fr);
            Scanner scanner = new Scanner(br);

           
            String credentials = scanner.nextLine();

            
            while (credentials != null && !userCredentialsOK) {
                String[] parts = credentials.split(",");

                if (parts.length == 2) {
                    String fileUsername = parts[0].trim();
                    String filePassword = parts[1].trim();
                    
                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                        userCredentialsOK = true;
                        break;
                    }
                }
                
                if (scanner.hasNextLine()) {
                    credentials = scanner.nextLine();
                } else {
                    credentials = null;
                }
            }
            scanner.close();
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
           
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        if (userCredentialsOK) { 
            if (username.startsWith("root")) {
                
                response.sendRedirect("rootHome.jsp");
            	} 
            else if (username.startsWith("client")) {
                
                response.sendRedirect("clientHome.jsp");
            	} 
            else if (username.startsWith("data")) {
                
                response.sendRedirect("data.jsp");
            	} 
            else {
                
                response.sendRedirect("error.jsp");
            	}
        	} 
        else {
        
            response.sendRedirect("error.jsp");
        }
    }
}
