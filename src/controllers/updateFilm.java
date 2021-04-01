package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import utility.updateFilmDAO;

/**
 * Servlet implementation class UpdateFilm
 */
@WebServlet("/updateFilm")
public class updateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String button = request.getParameter("button");
		String button2 = request.getParameter("button2");

		if ("updateFilm".equals(button)) {

			updateFilmDAO fd = new updateFilmDAO();

			String filmID = request.getParameter("userInp");
			String msgToUser = "";

			ArrayList<Film> a = fd.displayFilm(Integer.parseInt(filmID));
			Film checkId = a.get(0);
			int uID = checkId.getId();
			String uTitle = checkId.getTitle();
			int uYear = checkId.getYear();
			String uDirector = checkId.getDirector();
			String uStars = checkId.getStars();
			String uReview = checkId.getReview();


			if (checkId != null ) 
			{

				request.setAttribute("filmID",uID);
				request.setAttribute("filmTitle", (uTitle));
				request.setAttribute("filmYear", (uYear));
				request.setAttribute("filmDirector", (uDirector));
				request.setAttribute("filmStars", (uStars));
				request.setAttribute("filmReview", (uReview));


				String address = "/WEB-INF/mvc-sharing/updateFilm.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.include(request, response);
			}
			else
			{
				msgToUser ="Unknown ID";
			}



			System.out.println(filmID);
			System.out.println(msgToUser);

		}

		if ("updFilm".equals(button2)) {

			updateFilmDAO fd = new updateFilmDAO();

			String filmID = request.getParameter("filmID");
			String title = request.getParameter("title");
			String year = request.getParameter("year");
			String director = request.getParameter("director");
			String stars = request.getParameter("stars");
			String review = request.getParameter("review");

			String msgToUser = "";

			Film a = fd.updateFilm(filmID,title,year,director,stars,review);

			if (a == null ) 
			{
				msgToUser = "Film Details Updated!";
				request.setAttribute("msg", (msgToUser));

				String address = "/WEB-INF/mvc-sharing/updatedFilm.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.include(request, response);
			}

			System.out.println(msgToUser);

		}
	}

}
