package redirects;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	PrintWriter out;
    public DeleteItem() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	@SuppressWarnings({ "unused", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		HttpSession ses = request.getSession();
		
		List<Integer> allprods = (List<Integer>) ses.getAttribute("cartlist");
		
		int srno = Integer.parseInt(request.getParameter("srno"));
		allprods.remove(srno-1);
		
		if(allprods == null) 
		{
			out.print("<h5>No Products avaialble in cart</h5>");
		}
		
		else 
			response.sendRedirect("ViewCart");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
