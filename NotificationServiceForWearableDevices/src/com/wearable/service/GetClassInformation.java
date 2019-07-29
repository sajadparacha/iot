package com.wearable.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.runtime.JSONFunctions;

/**
 * Servlet implementation class GetClassInformation
 */
@WebServlet("/GetClassInformation")
public class GetClassInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().append("Served at: ").append();
		//** documenting while working on TV
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
//	private ClassBean getClass() {
//		ClassBean bean=new ClassBean();
//		bean.setSection("D");
//		bean.setClassName("Class 5");
//		StudentBean studentBean = new StudentBean();
//		studentBean.setName("Sheraz");
//		studentBean.setRollNumber(new Long(4));
//		bean.setStudentBean(studentBean);
//		return bean;
//	}

}
