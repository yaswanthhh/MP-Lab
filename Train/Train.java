 import java.io.*;
 import java.util.*;
 import java.sql.*;
 import javax.servlet.*;
 import javax.servlet.http.*;





 public class Train extends HttpServlet{
     static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
     static final String DB_URL="jdbc:mysql://localhost:3306/lab";
            
     static final String USER = "root";
     static final String PASS = "pooja";
     public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
         response.setContentType("text/html");
 		PrintWriter out=response.getWriter();
 		//out.println("<html>\n");

 		//out.println("<body>\n");
		
        
         try {
             Class.forName("com.mysql.jdbc.Driver");
            
             Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);


             PreparedStatement st = conn.prepareStatement("insert into details values(?,?,?)");
             st.setString(1, request.getParameter("train_no"));
             st.setString(2, request.getParameter("source"));
             st.setString(3, request.getParameter("destination"));
             st.executeUpdate();

            
             st.close();
             conn.close();
             out.println("<h2>Successfully Inserted</h2>\n");
 			//out.println("</body>\n");
 			//out.println("</html>\n");

		
             //response.sendRedirect("form1.html");
         }
        catch (Exception e) {
             e.printStackTrace();
         }
    }


 }

