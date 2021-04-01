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
 * Servlet implementation class AddNewFilm
 */
@WebServlet("/addFilm")
public class getNewFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address="";
		String button = request.getParameter("button");	
		if ("redirectPage".equals(button)) 
		{
			address = "/WEB-INF/mvc-sharing/insertFilm.jsp";

		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.include(request, response); 
	}

}
