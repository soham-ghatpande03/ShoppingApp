package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductPage
 */
@WebServlet("/ProductPage")
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con ;
	PrintWriter out ;
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		con = (Connection)config.getServletContext().getAttribute("databaseConn");
	}
  
    public ProductPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		
		try {
			out = response.getWriter();
			pstmt = con.prepareStatement("select * from product where cat_id = ?");
			
			int x = Integer.parseInt(request.getParameter("catid"));
			pstmt.setInt(1,x);
			
			
			rs = pstmt.executeQuery();
			
			
			String str = "";
//					str+="<table border=1>";
//					str+="<tr>";
//					str+="<th>P_id</th>";
//					str+="<th>P_name</th>";
//					str+="<th>P_Despriction</th>";
//					str+="</tr>";
			str+="<form action='AddtoCart'>";
			str+="<select name = 's1'>";
			str+="<option>Select A Product</option>";
			while(rs.next()) 
			{
				
//				str+="<tr>";
//				str+="<td>"+rs.getInt(1)+"</td>";
//				str+="<td>"+rs.getString(2)+"</td>";
//				str+="<td>"+rs.getString(3)+"</td>";
//				str+="</tr>";
				
				str+="<option value ="+rs.getInt(1)+">";
				str+=rs.getString(2);
				str+="</option>";
				str+="<br/>";
			}	
			
			str+="</select> <br/>";
			str+="<input type='submit' value='Add To cart' />";
			str+="</form>";
			
			out.println("Select a product : ");			
			out.print(str);
			
			
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
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
