package abu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
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
		String name = request.getParameter("name");
        String city = request.getParameter("city");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobby");
        String hobby = (hobbies!=null) ? String.join(",", hobbies) : "";
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        
        try {
			
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info","root","abubakar@8866");
        	PreparedStatement pst = con.prepareStatement("UPDATE student SET name=?, city=?, gender=?, hobby=?, address=?, dob=? WHERE id=?");
        	pst.setInt(7, id);
        	pst.setString(1, name);
        	pst.setString(2, city);
            pst.setString(3, gender);
            pst.setString(4, hobby);
            pst.setString(5, address);
            pst.setDate(6, Date.valueOf(dob));
            int rows = pst.executeUpdate();
            if (rows > 0) {
				response.sendRedirect("success.html");
			}else {
				response.sendRedirect("error.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

}
