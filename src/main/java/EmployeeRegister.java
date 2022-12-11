

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
 * Servlet implementation class EmployeeRegister
 */
@WebServlet("/EmployeeRegister")
public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeRegister() {
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
			PreparedStatement ps=con.prepareStatement("insert into employeemanagementsystem values(?,?,?,?,?,?,?,?)");
			ps.setString(1,Name);
			ps.setString(2,Password);
			ps.setLong(3,Mobileno);
			ps.setString(4,Email);
			ps.setString(5,Gender);
			ps.setString(6,Address);
			ps.setString(7,City);
			ps.setString(8,State);
			int i=ps.executeUpdate();
			out.println(i+"<body style=\"background-color: gray;\"><fieldset style=\"background-color: white;\"> <h1>Employee Registered successfully<br>Please Go to Login page </h1></fieldset>");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
