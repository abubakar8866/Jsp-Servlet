<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Student Records</h2>
<table border="1" cellpadding="5" cellspacing="0">
	<tr>
	    <th>ID</th>
	    <th>Name</th>
	    <th>City</th>
	    <th>Gender</th>
	    <th>Hobby</th>
	    <th>Address</th>
	    <th>DOB</th>
	</tr>
	<%
	try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info","root","abubakar@8866");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM student");
		while(rs.next()){
	%>
	<tr>
		<td><%= rs.getInt("id") %></td>
		<td><%= rs.getString("name") %></td>
	    <td><%= rs.getString("city") %></td>
	    <td><%= rs.getString("gender") %></td>
	    <td><%= rs.getString("hobby") %></td>
	    <td><%= rs.getString("address") %></td>
	    <td><%= rs.getDate("dob") %></td>		
	</tr>
	<%
		}
		con.close();
	}catch(Exception e){
		e.printStackTrace();
		out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
	}
	%>
</table>
<br>
<a href="index.html">Back to Menu</a>
</body>
</html>