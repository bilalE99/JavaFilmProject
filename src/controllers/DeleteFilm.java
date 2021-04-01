package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Film;
import utility.FilmDAO;

/**
 * Servlet implementation class DeleteFilm
 */
@WebServlet("/deleteFilm")
public class DeleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonList ="";
		String filmID ="";
		String x ="";
		Gson g = new Gson();

		List<Film> myData;
		myData = new ArrayList<Film>();
		FilmDAO fd = new FilmDAO();
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		
		if("test2".equals(action)) {
			filmID = request.getParameter("filmID");
			x = filmID.toString();
			Film a = fd.deleteFilm((Integer.parseInt(filmID)));
			if(filmID == null || "".equals(filmID)) {
				System.out.println("Invalid film ID");
				out.print("Invalid film ID");
			}
			out.print("Film ID: " + x+ " has been deleted!");
		}
		
	}

}
