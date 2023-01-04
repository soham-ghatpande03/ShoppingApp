package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.UserDetails;


@WebServlet("/LoginCheckError")
public class LoginCheckError extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
       PrintWriter out;
    public LoginCheckError() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	
    	con = (Connection) config.getServletContext().getAttribute("databaseConn");
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
				PreparedStatement ps = null;
				PreparedStatement ps1 = null;
				ResultSet rs = null;
				
			
				
				String userid = request.getParameter("u1");
				String pwd = request.getParameter("p1");
				
				Cookie [] call = request.getCookies();
				
				for(Cookie c : call) {
					if(c.getName().equals("msg1")) 
					{
						c.setMaxAge(0);
						response.addCookie(c);
					}
					}
				
				try 
				{
					out = response.getWriter();
					ps=con.prepareStatement("select * from users where u_id = ? and password = ?");
					
					ps.setString(1, userid);
					ps.setString(2, pwd);
					
					rs = ps.executeQuery();
					 
					
					Timestamp td = new Timestamp(new Date().getTime());
					
					ps1 = con.prepareStatement("insert into loghistory (u_id,logintime) values (?,?)");
					
					ps1.setString(1, userid);
					ps1.setTimestamp(2, td);
			
					
					int n1 = ps1.executeUpdate();
					
					System.out.println(n1+" value inserted");
					
					if(rs.next()) 
					{
						
						
						HttpSession ses = request.getSession();
						UserDetails ud = new UserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
						
						ses.setAttribute("newloggeduser", ud);
						
						
						for(Cookie c : call) {
							if(c.getName().equals("msg1")) 
							{
								c.setMaxAge(0);
								response.addCookie(c);
							}
							}					
						RequestDispatcher reqdis = request.getRequestDispatcher("/HomePage");
						reqdis.forward(request, response);
						
					}
					
					else 
					{
						Cookie msg = new Cookie("msg1","User/Password_Invalid");
						response.addCookie(msg);
						response.sendRedirect("/Redirect_Dispatch/Login.jsp");
						
					}
							
					
				}
				
				
				catch (SQLException e) {
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
						ps.close();
						ps1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	
		
	}

}

