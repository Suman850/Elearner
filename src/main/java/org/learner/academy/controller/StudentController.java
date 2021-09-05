package org.learner.academy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.learner.academy.config.HibernateConfig;
import org.learner.academy.entity.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/students")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionFactory sf = HibernateConfig.buildSessionFactory();
		Session sessionHb = null;

		try {
			sessionHb = sf.openSession();
			sessionHb.beginTransaction();

			Query q2 = sessionHb.createQuery("from Student ");
			@SuppressWarnings("unchecked")
			List<Student> stdList = (List<Student>) q2.list();

			request.setAttribute("stdList", stdList);

			sessionHb.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionHb != null) {
				sessionHb.close();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("students.jsp");
		rd.forward(request, response);

	}

}