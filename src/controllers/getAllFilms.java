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

import model.Film;
import utility.FilmDAO;


/**
 * Servlet implementation class GetAllFilmsInfo
 */
@WebServlet("/getAllFilms")
public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 

	{
		String button = request.getParameter("button");


		if ("getAllInfo".equals(button)) {

			String a;
			List<Film> myData;
			myData = new ArrayList<Film>();
			FilmDAO fd = new FilmDAO();
			myData = fd.getAllFilms();
			String address ="/WEB-INF/mvc-sharing/getAllFilms.jsp";
			request.setAttribute("filmsInfo", (myData.toString()));	
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.include(request, response);     
		} 

	}


}

