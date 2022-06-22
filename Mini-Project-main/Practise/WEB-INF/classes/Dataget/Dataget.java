package Dataget;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class Dataget extends HttpServlet{
    public Boolean AddData(String BN,String BA,String BJ){
        try{String url = "jdbc:mysql://localhost:3306/test_db";
        String user = "root";
        String pass = "Naveen@01";
        String a = "com.mysql.jdbc.Driver";
        Class.forName(a).newInstance();
        Connection con = DriverManager.getConnection(url,user,pass);
        PreparedStatement st = con.prepareStatement("INSERT INTO BOOKS(BOOK_NAME,BOOK_AUTHOR,BOOK_JOURNAL) VALUES (?,?,?)");
        st.setString(1,BN);
        st.setString(2,BA);
        st.setString(3,BJ);
        if(st.executeUpdate() == 1){
            return true;
        }else{
            return false;
        }
    }catch(Exception E){
        System.out.println(E);
    }
    return true;
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String Book_Name = req.getParameter("name");
        String Book_Author = req.getParameter("author");
        String Book_Journal = req.getParameter("journal");
        Boolean b = AddData(Book_Name,Book_Author,Book_Journal);
        if(b == true){
            pw.println("SuccessFull Updated");
        }else{
            pw.println("Not added and Updated");
        }
    }
}