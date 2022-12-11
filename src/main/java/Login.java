

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String Name=request.getParameter("name");
		String Password=request.getParameter("pswd");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("select * from employeemanagementsystem where Name=? and Password=?");
			ps.setString(1, Name);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) 
			{
				response.sendRedirect("LoginedHome.html");
			}
			else 
			{
				out.print("<body style=\"background-color: gray;\"><fieldset style=\"background-color: white;\"> <h1>PLease Enter Valid Login Details</h1></fieldset>");
			}
			con.close();
		}
		catch(Exception ex)
		{
			out.print(ex);
		}
	}

}
