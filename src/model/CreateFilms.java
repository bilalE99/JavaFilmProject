package model;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import utility.FilmDAO;

//For jdk1.5 with built in xerces parser
//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

//For JDK 1.3 or JDK 1.4  with xerces 2.7.1
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;


public class CreateFilms {

	//No generic
	List<Film> myData;
	Document dom;

	public CreateFilms() {
		//create a list to hold the data
		myData = new ArrayList<Film>();

		//Initialise the list
		loadData();

		//Get a DOM object
		createDocument();
	}


	public void runExample(){
		System.out.println("Started .. ");
		createDOMTree();
		printToFile();
		System.out.println("Generated file successfully.");
	}

	/**
	 * Add a list of films to the list
	 * In a production system you might populate the list from a DB
	 */
	private void loadData(){
		
		// Create an instance of a FilmDAO, which will read the film database
		FilmDAO fd = new FilmDAO();
		// Ask the FilmDAO for a list of all films from the database
		myData = fd.getAllFilms();
		
		// Film(int id, String title, int year, String director, String stars, String review)
	}

	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 */
	private void createDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		dom = db.newDocument();

		}catch(ParserConfigurationException pce) {
			//dump it
			System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
			System.exit(1);
		}

	}

	/**
	 * The real workhorse which creates the XML structure
	 */
	private void createDOMTree(){

		//create the root element <Films>
		Element rootEle = dom.createElement("films");
		dom.appendChild(rootEle);

		//No enhanced for
		Iterator<Film> it  = myData.iterator();
		while(it.hasNext()) {
			Film f = (Film)it.next();
			// output to show processing
			System.out.println("Film : "+f.getId() + " " + f.getTitle());
			//For each Film object  create <Film> element and attach it to root
			Element filmEle = createFilmElement(f);
			rootEle.appendChild(filmEle);
		}

	}

	/**
	 * Helper method which creates a XML element <Film>
	 * @param f The film for which we need to create an xml representation
	 * @return XML element snippet representing a film
	 */
	private Element createFilmElement(Film f){

		Element filmEle = dom.createElement("film");
		// no attributes for film element

		//create id element and id text node and attach it to filmElement
		Element idEle = dom.createElement("id");
		Text idText = dom.createTextNode("" + f.getId());
		idEle.appendChild(idText);
		filmEle.appendChild(idEle);

		//create title element and title text node and attach it to filmElement
		Element titleEle = dom.createElement("title");
		Text titleText = dom.createTextNode(f.getTitle());
		titleEle.appendChild(titleText);
		filmEle.appendChild(titleEle);
		
		//create year element and year text node and attach it to filmElement
		Element yearEle = dom.createElement("year");
		Text yearText = dom.createTextNode("" + f.getYear());
		yearEle.appendChild(yearText);
		filmEle.appendChild(yearEle);

		//create director element and director text node and attach it to filmElement
		Element directorEle = dom.createElement("director");
		Text directorText = dom.createTextNode(f.getDirector());
		directorEle.appendChild(directorText);
		filmEle.appendChild(directorEle);
		
		//create stars element and stars text node and attach it to filmElement
		Element starsEle = dom.createElement("stars");
		Text starsText = dom.createTextNode(f.getStars());
		starsEle.appendChild(starsText);
		filmEle.appendChild(starsEle);
		
		//create review element and review text node and attach it to filmElement
		Element reviewEle = dom.createElement("review");
		Text reviewText = dom.createTextNode(f.getReview());
		reviewEle.appendChild(reviewText);
		filmEle.appendChild(reviewEle);

		return filmEle;

	}

	/**
	 * This method uses Xerces specific classes
	 * prints the XML document to file.
     */
	private void printToFile(){

		try
		{
			//print
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);


			//to generate a file output use fileoutputstream instead of system.out
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File("createfilms.xml")), format);

			serializer.serialize(dom);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}


	public static void main(String args[]) {

		//create an instance
		CreateFilms xce = new CreateFilms();

		//run the example
		xce.runExample();
	}
}
