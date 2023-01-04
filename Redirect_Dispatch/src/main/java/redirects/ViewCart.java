package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con = (Connection)config.getServletContext().getAttribute("databaseConn");
	}

    public ViewCart() {
        super();
        
    }


	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession();
		
		
		List<Integer> allprods = (List<Integer>) ses.getAttribute("cartlist");
		
		
		if(allprods == null) 
		{
			out.print("<h4>No itmes added in the Cart</h4>");
		}
		
		
		else
		{
			
			PreparedStatement ps =null;
			ResultSet rs = null;
			int x = 0;
			try 
			{
				out.print("<table border=1>");
				out.print("<tr>");
				out.print("<th>Sr No.</th>");
				out.print("<th>Name</th>");
				out.print("<th>Descp</th>");
				out.print("<th>Quantity</th>");
				out.print("<th>Price</th>");				
				out.print("</tr>");
				float total = 0;
				ps = con.prepareStatement("select * from product where p_id = ?");
				
				for(int n : allprods) 
				{
					
					
					ps.setInt(1, n);
					
					rs = ps.executeQuery();
					
					
					if(rs.next())
					{
											 
						out.print("<tr>");
						out.print("<td>"+(++x)+"</td>");
						out.print("<td>"+rs.getString(2)+"</td>");
						out.print("<td>"+rs.getString(3)+"</td>");
						out.print("<td>1</td>");
						out.print("<td>"+Float.parseFloat(rs.getString(4))+"</td>");
						
						out.print("<td> <a href='DeleteItem?srno="+x+"'> Delete Item </a> </td>");
						total += Float.parseFloat(rs.getString(4));
						out.print("</tr>");
						
					}
				}
				out.print("<tr>");
				out.print("<td colspan=4>Total Price</td>");
				out.print("<td>"+total+"</td>");
				out.print("</tr>");				
				out.print("</table>");
				
				ses.setAttribute("totalprice", total);
				
				out.print(" <br/><a href ='Proceed'>Proceed to Checkout</a><br/><br/>");
				out.print("<a href ='HomePage'>Add More Items to Cart</a><br/>");
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
