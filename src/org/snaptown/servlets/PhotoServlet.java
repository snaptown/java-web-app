package org.snaptown.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.snaptown.converters.PhotoToJsonConverter;
import org.snaptown.dao.PhotoDAO;
import org.snaptown.dao.UserDAO;
import org.snaptown.models.Photo;
import org.snaptown.models.User;
import org.snaptown.providers.EntityManagerProvider;

@WebServlet(urlPatterns = "/photo")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long photoId = Long.parseLong(request.getParameter("photoId"));

		PhotoDAO photoDao = new PhotoDAO(EntityManagerProvider.getEntityManager());
		UserDAO userDao = new UserDAO(EntityManagerProvider.getEntityManager());
		Photo photo = photoDao.getPhotoById(photoId);
		User creator = userDao.getUserById(photo.getUserId());

		JSONObject result = new JSONObject();
		JSONObject photoJsonObj;
		try {
			photoJsonObj = new PhotoToJsonConverter().convert(photo);
			result.put("photo", photoJsonObj);
			result.put("userCreator", creator.getUsername());
			System.out.println(result.toJSONString());
			response.getWriter().write(result.toJSONString());
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
