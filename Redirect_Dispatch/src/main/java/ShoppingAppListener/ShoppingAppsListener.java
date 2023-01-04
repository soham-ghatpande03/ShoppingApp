package ShoppingAppListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ShoppingApps
 *
 */
@WebListener
public class ShoppingAppsListener implements ServletContextListener {
Connection con;
    /**
     * Default constructor. 
     */
    public ShoppingAppsListener() {
        // TODO Auto-generated constructor stub
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    	ServletContext ctx = sce.getServletContext();
    	
    	String driver = ctx.getInitParameter("driverclass");
    	String url = ctx.getInitParameter("url");
    	String user = ctx.getInitParameter("user");
    	String pwd = ctx.getInitParameter("pass");
    	
    	try 
    	{
			Class.forName(driver);
		} 
    	catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try 
    	{
			con = DriverManager.getConnection(url,user,pwd);
			
			System.out.println("Connection Established");
			
			ctx.setAttribute("databaseConn", con);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}