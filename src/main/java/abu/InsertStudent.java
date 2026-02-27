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
 * Servlet implementation class InsertStudent
 */
@WebServlet("/InsertStudent")
public class InsertStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStudent() {
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
        	PreparedStatement pst = con.prepareStatement("INSERT INTO student (id,name,city,gender,hobby,address,dob) VALUES (?,?,?,?,?,?,?)");
        	pst.setInt(1, id);
        	pst.setString(2, name);
        	pst.setString(3, city);
            pst.setString(4, gender);
            pst.setString(5, hobby);
            pst.setString(6, address);
            pst.setDate(7, Date.valueOf(dob));
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
