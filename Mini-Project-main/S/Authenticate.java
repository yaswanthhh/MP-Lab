import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class Authenticate extends HttpServlet{
		static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		static final String DB_URL="jdbc:mysql://localhost:3306/TestDB";
		static final String USER="root";
		static final String PASS="root";
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			out.println("<html><body>");
			try{
				Class.forName(JDBC_DRIVER);
				Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=conn.createStatement();
				String sql;
				String username=req.getParameter("uname");
				String password=req.getParameter("password");
				sql="SELECT * FROM TestDB.test";
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next())
				{
				String uname=rs.getString("uname_col");	
				String pwd=rs.getString("password_col");
				if(uname.equals(username) && password.equals(pwd))
				{
					out.println("Authenticated");
				}
				else
				{
					RequestDispatcher rd=req.getRequestDispatcher("Signup");
					rd.forward(req,res);
				}
				}
				//PreparedStatement stmt=conn.prepareStatement("")
			}
			catch(Exception e){
				e.printStackTrace();
			}
			out.println("</body></html>");
		
	}
	
}