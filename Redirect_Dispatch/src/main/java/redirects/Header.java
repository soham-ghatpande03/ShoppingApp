package redirects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.UserDetails;


@WebServlet("/Header")
public class Header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Header() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out =  response.getWriter();
		out.print("<a href = 'UpdateUser.jsp' style = 'float:right'>Update Profile</a><br/>");
		out.print("<h2>Welcome</h2>");
		HttpSession ses = request.getSession();
		UserDetails user = (UserDetails) ses.getAttribute("newloggeduser");		
		String fname = user.getFname();
		String lname = user.getLname();
		out.print("<h3>"+fname+" "+lname+"</h3><hr/>");
		out.print("<br/><a href='UserLogout'  style = 'float:right'> LOGOUT </a>");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
