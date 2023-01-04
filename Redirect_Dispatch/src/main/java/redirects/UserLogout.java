package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PrintWriter out ;
       Connection con ;

    public UserLogout() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	 con = (Connection) config.getServletContext().getAttribute("databaseConn");
    	super.init();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PreparedStatement ps;
		
		HttpSession ses = request.getSession(false);
		
		if( ses!=null)
		{
			ses.invalidate();
		}
				
		out = response.getWriter();
		out.print("<h3>Thank You for Shopping with Us</h3><br/><br/><br/>");
		out.print("<h4><a href= '/Redirect_Dispatch/Login.jsp'>LOGIN TO SHOPPING PAGE</a></h4>");
		
		try 
		{
			 ps = con.prepareStatement("update loghistory set logouttime = ? where logouttime is null ");
			 
			 Timestamp td = new Timestamp(new Date().getTime());
				
			 ps.setTimestamp(1, td);
			 
			 int n = ps.executeUpdate();
			 
			 System.out.println(n+" values updated");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
