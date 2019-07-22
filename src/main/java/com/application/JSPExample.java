package com.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPExample extends HttpServlet{
	
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	response.setContentType("text/Html");
    response.getWriter().println("servlet example");
   sampleServlet sample=new sampleServlet();
    List<Employee> list=new ArrayList<Employee>();
    ResultSet rs=sample.getData();
    for(Employee employee:list)
    {
    	System.out.println(employee.getName()+"  "+employee.getEmp_id()+"  "+employee.getManager_id());
    }
    
    try {
    	while(rs.next())
    	   {
    		Employee e=new Employee(rs.getString(1),rs.getInt(2),rs.getInt(3));
 		   list.add(e);
    	   }
    } catch (SQLException e) {
    	
    	e.printStackTrace();
    }
  
    
    request.setAttribute("data",list);
    RequestDispatcher rd=request.getRequestDispatcher("printData.jsp");
    rd.forward(request, response);
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
