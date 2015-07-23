package org.snaptown.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snaptown.dao.UserDAO;
import org.snaptown.models.User;
import org.snaptown.providers.EntityManagerProvider;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 6745539696761021063L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String userName = req.getParameter("username");
		final String password = req.getParameter("password");

		if (userName == null || password == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		} else {
			UserDAO userDAO = new UserDAO(EntityManagerProvider.getEntityManager());
			final User newUser = new User(userName, password, false);
			userDAO.addUser(newUser);

			req.getSession().setAttribute("currentUser", userName);
			resp.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
