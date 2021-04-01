package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Film;
import utility.FilmDAO;

/**
 * Servlet implementation class AddNewFilm
 */
@WebServlet("/insertFilm")
public class insertFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String jsonList ="";
		Gson g = new Gson();

		List<Film> myData;
		myData = new ArrayList<Film>();
		FilmDAO fd = new FilmDAO();

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if("test1".equals(action)) {
			String filmID = request.getParameter("filmID");
			String title = request.getParameter("title");
			String year = request.getParameter("year");
			String director = request.getParameter("director");
			String stars = request.getParameter("stars");
			String review = request.getParameter("review");

			Film a = fd.insertFilm(filmID,title,year,director,stars,review);
			out.print("\n" + "New film inserted!" + 
			"\n "+ "id:"+filmID+",  title:"+title
	    	+",  year:"+year+",  director:"+director
	    	+",  stars:"+stars+",  review:"+review);
		}
	}


}
