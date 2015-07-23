package org.snaptown.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * Servlet implementation class Androidtest
 */
@WebServlet(urlPatterns = "/Androidtest")
@MultipartConfig(location = "C:/Users/Tsvety/Desktop")
public class AndroidTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("a");
		int i;
		char c;
		String comment = "";
		String lat = "";
		String lon = "";

		InputStream in = request.getPart("comment").getInputStream();
		while ((i = in.read()) != -1) {
			// converts integer to character
			c = (char) i;
			comment += c;

		}
		in = request.getPart("lat").getInputStream();
		while ((i = in.read()) != -1) {
			// converts integer to character
			c = (char) i;
			lat += c;

		}
		in = request.getPart("lon").getInputStream();
		while ((i = in.read()) != -1) {
			// converts integer to character
			c = (char) i;
			lon += c;

		}

		InputStream is = request.getPart("PHOTO").getInputStream();
		OutputStream out = new FileOutputStream("C:/Users/Tsvety/Desktop" + comment + ".jpeg");
		IOUtils.copy(is, out); // The function is below
		out.flush();
		out.close();
		System.out.println(comment);
		System.out.println(lon);
		System.out.println(lat);

	}

}
