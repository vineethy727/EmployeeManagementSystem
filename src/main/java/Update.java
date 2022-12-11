

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		long Mobileno=Long.parseLong(request.getParameter("mobno"));
		String Email=request.getParameter("mail");
		String Gender=request.getParameter("gender");
		String Address=request.getParameter("addr");
		String City=request.getParameter("city");
		String State=request.getParameter("state");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("update employeemanagementsystem set Password=?, Mobileno=?, Email=?, Gender=?, Address=?, City=?, state=? where name=?");
			ps.setString(8,Name);
			ps.setString(1,Password);
			ps.setLong(2,Mobileno);
			ps.setString(3,Email);
			ps.setString(4,Gender);
			ps.setString(5,Address);
			ps.setString(6,City);
			ps.setString(7,State);
			int i=ps.executeUpdate();
			out.println(i+"<body style=\"background-color: gray;\"><fieldset style=\"background-color: white;\"> <h1>Employee Record Updated successfully</h1></fieldset>");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}

}
