package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.UserDetails;


@WebServlet("/Proceed")
public class Proceed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public Proceed() {
        super();
        // TODO Auto-generated constructor stub
    }

    Connection con ;
	PrintWriter out ;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con = (Connection)config.getServletContext().getAttribute("databaseConn");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		HttpSession ses = request.getSession();
		UserDetails u1 = (UserDetails) ses.getAttribute("newloggeduser");
		
		float total = (Float)ses.getAttribute("totalprice");
		
		String email = u1.getEmail();
		String ctcn = u1.getCntc();
		
		out.print("<h2><br/>Your order is confirmed");
		out.print("<br/>Your e-bill will be mailed at : "+email );
		out.print("<br/>You will receive msg before delivery on : "+ctcn);
		out.print("<br/>Total amount to be paid : "+total+"</h2>");
		out.print("<br/><a href='UserLogout'> LOGOUT </a>");
	
		
		String uid = u1.getUid();
		
		Timestamp td = new Timestamp(new Date().getTime());
	
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into shopping(user_id,shoppingDate,totalprice) values(?,?,?)");
			ps.setString(1, uid );
			ps.setTimestamp(2,td );
			ps.setFloat(3, total );
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
