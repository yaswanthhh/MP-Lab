import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
public class Register extends HttpServlet{
		static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		static final String DB_URL="jdbc:mysql://localhost:3306/TestDB";
		static final String USER="root";
		static final String PASS="root";
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