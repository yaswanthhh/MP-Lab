import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;



public class Train1 extends HttpServlet{
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/lab";
            
    static final String USER = "root";
    static final String PASS = "pooja";
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
		
		String no =request.getParameter("train_no");
		
		//out.println("<html>\n");

		//out.println("<body>\n");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			
			Statement st=conn.createStatement();
			String det="Select * from details";
			ResultSet rs=st.executeQuery(det);
		
			
			while(rs.next()){
				String train_no = rs.getString("train_no");
				if(no.equals(train_no)){
					String val1 = rs.getString("source");
					String val2 = rs.getString("Destination");
					out.println("<p>Source:"+val1+"</p>\n");
					out.println("<p>Destination:"+val2+"</p>\n");
				}
			}
            st.close();
            conn.close();
			rs.close();
			//out.println("</body></html>\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}



