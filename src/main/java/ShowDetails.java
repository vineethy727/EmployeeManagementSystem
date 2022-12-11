

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowDetails
 */
@WebServlet("/ShowDetails")
public class ShowDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetails() {
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
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","nbatch","nbatch");
			PreparedStatement ps=con.prepareStatement("select * from employeemanagementsystem where name=?");
			ps.setString(1, Name);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			out.print("      <body style=\"background-color:gray ;\"> <table border=\"2\" cellpadding=\"5px\" style=\"background-color:white ;\" align=\"center\">");
			int n=rsmd.getColumnCount();
			for(int i=1; i<=n; i++)
				out.println("<th>"+"<br>"+rsmd.getColumnName(i));
			out.println("<tr>");
			while(rs.next())
			{
				for(int i=1; i<=n; i++)
					out.println("<td><br>"+rs.getString(i));
				out.println("<tr>");
			}
			out.print("</table></body></html>");
			con.close();
		}
		catch(Exception ex)
		{
			out.print(ex);
		}
		
	}

}
