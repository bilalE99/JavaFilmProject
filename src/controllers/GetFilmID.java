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

import com.google.gson.*;

import model.Film;
import utility.FilmDAO;
import utility.FilmUtils;

/**
 * Servlet implementation class GetFilmID
 */
@WebServlet("/getFilmID")
public class GetFilmID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonList ="";
		String filmID ="";
		Gson g = new Gson();

		List<Film> myData;
		myData = new ArrayList<Film>();
		FilmDAO fd = new FilmDAO();
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String radBtn = request.getParameter("format");	
		String radBtn2 = request.getParameter("searchBy");
		
			
		if("test1".equals(action)) {
			if ("ID".equals(radBtn2) || "id".equals(radBtn2)) {
				filmID = request.getParameter("filmID");
				Film a = fd.getFilmByID((Integer.parseInt(filmID)));
				myData.add(a); //film attribute appended to array list
				if(myData == null || "".equals(myData) ) {
					System.out.println("Invalid film ID");
					out.print("Invalid film ID");
				}
				if ("json".equals(radBtn) || "JSON".equals(radBtn)) 
				{ 
					jsonList = g.toJson(myData);	
					response.setContentType("text/javascript");
					System.out.println(jsonList);
					out.print("\n" + jsonList.toString());

				}
				if ("string".equals(radBtn) || "STRING".equals(radBtn)) 
				{ 
					response.setContentType("text/plain");
					System.out.println(jsonList);
					out.print("\n" + myData.toString());
				}
				else {
					out.print("\n" + myData.toString());
				}
			}
			else if ("name".equals(radBtn2) || "Name".equals(radBtn2) ) {
				filmID = request.getParameter("filmID");
				Film a = fd.getFilmByName(filmID);
				myData.add(a); //film attribute appended to array list
				if(myData == null || "".equals(myData) ) {
					System.out.println("Invalid film name");
					out.print("Invalid film ID");
				}
				if ("json".equals(radBtn) || "JSON".equals(radBtn)) 
				{ 
					jsonList = g.toJson(myData);	
					response.setContentType("text/javascript");
					System.out.println(jsonList);
					out.print("\n" + jsonList.toString());

				}
				if ("string".equals(radBtn) || "STRING".equals(radBtn)) 
				{ 
					response.setContentType("text/plain");
					System.out.println(jsonList);
					out.print("\n" + myData.toString());
				}
				else {
					out.print("\n" + myData.toString());
				}
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
