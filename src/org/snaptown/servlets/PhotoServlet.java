package org.snaptown.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.snaptown.dao.PhotoDAO;
import org.snaptown.dao.UserDAO;
import org.snaptown.models.Photo;
import org.snaptown.models.User;
import org.snaptown.providers.EntityManagerProvider;

@WebServlet(urlPatterns = "/photos")
@MultipartConfig(location = "C:/Users/Tsvety/Desktop/snaptown/")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String creatorUsername = parseMultiPartTextPart(request, "username");
		String comment = parseMultiPartTextPart(request, "comment");
		String latitudeStr = parseMultiPartTextPart(request, "lat");
		String longitudeStr = parseMultiPartTextPart(request, "lon");

		Double longitude = Double.parseDouble(longitudeStr);
		Double latitude = Double.parseDouble(latitudeStr);

		EntityManager em = EntityManagerProvider.getEntityManager();
		PhotoDAO photoDAO = new PhotoDAO(em);
		UserDAO userDao = new UserDAO(em);
		final User newPhotoCreator = userDao.getUserByUsername(creatorUsername);

		if (newPhotoCreator == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			final Photo newPhoto = new Photo(newPhotoCreator.getId(), "", longitude, latitude, comment);
			photoDAO.addPhoto(newPhoto);
			savePhotoWithId(request, newPhoto);

			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	private void savePhotoWithId(HttpServletRequest request, Photo photo) throws IOException, ServletException {
		final String newImgPath = "C:/Users/Tsvety/Desktop/snaptown/" + photo.getId() + ".jpeg";
		InputStream is = request.getPart("PHOTO").getInputStream();

		OutputStream out = new FileOutputStream(newImgPath);
		IOUtils.copy(is, out);
		out.flush();
		is.close();
		out.close();

		photo.setImgPath(newImgPath);
	}

	private String parseMultiPartTextPart(HttpServletRequest request, final String partName)
			throws IOException, ServletException {
		StringBuilder result = new StringBuilder();
		int i;
		InputStream in = request.getPart(partName).getInputStream();
		while ((i = in.read()) != -1) {
			result.append((char) i);
		}
		return result.toString();
	}
}
