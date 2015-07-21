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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -711526206932034241L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String username = req.getParameter("userName");
		final String password = req.getParameter("password");

		UserDAO userDAO = new UserDAO(EntityManagerProvider.getEntityManager());
		final User requestedUser = userDAO.getUserByCredentials(username, password);
		if (requestedUser == null) {
			doGet(req, resp);
		} else {
			req.getSession().setAttribute("currentUser", username);
			if (requestedUser.isAdmin()) {
				resp.sendRedirect("/admin/photos");
			} else {
				resp.sendRedirect("/photos");
			}
		}
	}
}
