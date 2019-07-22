package com.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class sampleServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/Html");
        response.getWriter().println("servlet example");
        
        ResultSet rs=getData();
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>All Employees</title></head>");
        out.println("<body>");
        out.println("<center><h1>All Employees</h1>");
        String st="<table>"+"<tr>"+"<th>emp_name</th>"+"<th>salary</th>"+"<th>manager_id</th>"+"</tr>";
        out.println(st);
        
        try {
			while(rs.next())
			{
				out.println("<tr>"+"<td>"+rs.getString(1)+"<td>"+"<td>"+rs.getString(2)+"<td>"+"<td>"+rs.getString(3)+"<td>"+"<tr>");			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        out.println("</table>"+"</body>"+"</html>");
       
    }
	
	
	
	public ResultSet getData()
	{
		final String url = "jdbc:mysql://localhost:3306";
		 
	    final String user = "root";
	 
	    final String password = "";
	 
	    
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	
	            Connection con = DriverManager.getConnection(url, user, password);
	            System.out.println("Success");
	            
	            String query = "use sample";
	            
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            
	            String q="select * from employee";
	            rs=st.executeQuery(q);
	            
	            return rs;
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return null;
	    }
	
	
	@Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }
	
    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }

}

