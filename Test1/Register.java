import java.io.*;
import java.util.*;
import java.servlet.*;
import java.servlet.http.*;
import java.sql.*;
public class Register extends HttpServlet{
		static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		static final String DB_URL="com.jdbc:mysql://localhost:3306/TestDB";
		static final String USER="ssn";
		static final String PASS="ssn@123";
		public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
			try{
				Class.forName(JDBC_DRIVER);
				Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
				PreparedStatement stmt=conn.prepareStatement("INSERT INTO test values(?,?)");
				
				String username=req.getParameter("uname");
				String password=req.getParameter("password");
				stmt.setString(1,username);
				stmt.setString(2,password);
				stmt.executeUpdate();
				stmt.close();
				conn.close();
				RequestDispatcher rd=req.getRequestDispatcher("./login.html");
				rd.forward(req,res);

			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}