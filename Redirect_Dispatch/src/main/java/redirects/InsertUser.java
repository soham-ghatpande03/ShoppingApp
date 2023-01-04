package redirects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con ;
	PrintWriter out ;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con = (Connection)config.getServletContext().getAttribute("databaseConn");
	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		out = response.getWriter();
		
		PreparedStatement ps = null;
		
		String uid = request.getParameter("u1");
		String pwd = request.getParameter("p1");
		String fname = request.getParameter("f1");
		String mname = request.getParameter("m1");
		String lname = request.getParameter("l1");
		String email = request.getParameter("e1");
		String pnum = request.getParameter("n1");
		
		try 
		{
			ps = con.prepareStatement("insert into users values (?,?,?,?,?,?,?) ");
			
			ps.setString(1, uid);
			ps.setString(2, pwd);
			ps.setString(3, fname);
			ps.setString(4, mname);
			ps.setString(5, lname);
			ps.setString(6, email);
			ps.setString(7, pnum);
			
			int n = ps.executeUpdate();
			
			out.print("<h1>Succesfully inserted "+n+" Record(s)</h1>");
			out.print("<br/><br/><h4><a href= '/Redirect_Dispatch/Login.jsp'>LOGIN TO SHOPPING PAGE</a></h4>");
			
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally 
		{
			try 
			{
				ps.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
