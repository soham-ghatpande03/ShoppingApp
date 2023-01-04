package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con ;
	PrintWriter out ;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con = (Connection)config.getServletContext().getAttribute("databaseConn");
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Statement stmt = null;
	ResultSet rs = null;
	
	
	try {
		out = response.getWriter();
		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from category");
		RequestDispatcher rds = request.getRequestDispatcher("/Header");
		rds.include(request, response);
		
		out.print("<h5>Select from the below product categories :</h5>\n");
		
		while(rs.next()) {		
			
			
			out.print("<a href ='ProductPage?catid="+rs.getInt(1)+"'>"+rs.getString(2)+"</a></br>");
		}
		out.print("</ol>");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
}
