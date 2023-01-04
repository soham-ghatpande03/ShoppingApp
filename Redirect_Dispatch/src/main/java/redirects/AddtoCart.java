package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	Connection con;
	PrintWriter out;
	
    public AddtoCart() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void init(ServletConfig config) throws ServletException {
		
		con = (Connection) config.getServletContext().getAttribute("databaseConn");
		
	}


	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		
		int selProd = Integer.parseInt(request.getParameter("s1"));
		
	//	String x = request.getParameter("s1");
		
		HttpSession ses= request.getSession();
		
		List<Integer> allproducts = null;
		
		allproducts = (List<Integer>) ses.getAttribute("cartlist");
		
		
		if (allproducts == null)
			allproducts = new ArrayList<>();
		allproducts.add(selProd);
		
		ses.setAttribute("cartlist", allproducts);
	
		out.print("Item with product id "+selProd+" has been added to cart <br/>");
		out.print("Total items in cart are : "+allproducts.size()+"<br/>");
		
		out.print(" <br/><a href ='ViewCart'>VIEW CART</a><br/><br/>");
		out.print("<a href ='HomePage'>Add More Items to Cart</a><br/>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
