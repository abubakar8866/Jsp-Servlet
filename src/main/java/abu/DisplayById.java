package abu;

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
 * Servlet implementation class DisplayById
 */
@WebServlet("/DisplayById")
public class DisplayById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        
        try {
			
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info","root","abubakar@8866");
        	PreparedStatement pst = con.prepareStatement("SELECT * FROM student WHERE id=?");
        	pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	out.println("<h2>Student Record</h2>");
                out.println("<p><b>ID:</b> " + rs.getInt("id") + "</p>");
                out.println("<p><b>Name:</b> " + rs.getString("name") + "</p>");
                out.println("<p><b>City:</b> " + rs.getString("city") + "</p>");
                out.println("<p><b>Gender:</b> " + rs.getString("gender") + "</p>");
                out.println("<p><b>Hobby:</b> " + rs.getString("hobby") + "</p>");
                out.println("<p><b>Address:</b> " + rs.getString("address") + "</p>");
                out.println("<p><b>DOB:</b> " + rs.getDate("dob") + "</p>");
			}else {
				out.println("<h3 style='color:red;'>No record found with ID " + id + "</h3>");
			}
            out.println("<a href='displayById.html'>Back</a>");
            con.close();

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

}
