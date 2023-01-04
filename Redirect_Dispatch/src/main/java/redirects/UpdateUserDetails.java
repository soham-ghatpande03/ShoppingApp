package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.UserDetails;


@WebServlet("/UpdateUserDetails")
public class UpdateUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con ;
	PrintWriter out;
	
 
    public UpdateUserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void init(ServletConfig config) throws ServletException {
		
		con = (Connection) config.getServletContext().getAttribute("databaseConn");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		out = response.getWriter();
		
		PreparedStatement ps = null;
		
		try {
			
			ps = con.prepareStatement("update users set email = ? ,contact = ? where u_id = ? ");
			
			HttpSession ses = request.getSession();
			UserDetails u1 = (UserDetails) ses.getAttribute("newloggeduser");
			
			String uid = u1.getUid();
			String pwd = u1.getPass();
			String fn= u1.getFname();
			String mn = u1.getMname();
			String ln = u1.getLname();
			
			
			String email = request.getParameter("e1");
			String ctcn = request.getParameter("n1");
			
			ps.setString(1, email);
			ps.setString(2, ctcn);
			ps.setString(3, uid);
			
			
			
			
			int n = ps.executeUpdate();
			System.out.println(n+" value updated");
			
			out.print("<h3>Details updated successfully</h3>");
			out.print("<br/> <a href = 'HomePage'>Go To Shopping Page</a>");
			

			UserDetails ud = new UserDetails (uid,pwd,fn,mn,ln,email,ctcn);			
			ses.setAttribute("newloggeduser", ud);
			

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
