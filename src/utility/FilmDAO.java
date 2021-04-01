package utility;
import java.util.ArrayList;

import model.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Film emptyFilm = null;
	Connection conn = null;
    Statement stmt = null;
    String user = "elfitob";
    String password = "Lerftase4";
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;

	public FilmDAO() {
		
	}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch(Exception e) 
		{ 
			System.out.println(e); 
		}

		// connecting to local database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    Statement stmt = conn.createStatement();
		    String selectSQL = "select * from films";
		    ResultSet rs = stmt.executeQuery(selectSQL);
		       
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	allFilms.add(oneFilm);
		    	System.out.println("id:"+rs.getString("id")+"\ntitle:"+rs.getString("title")
		    	+"\nyear:"+rs.getString("year")+"\ndirector:"+rs.getString("director")
		    	+"\nstars:"+rs.getString("stars")+"\nreview:"+rs.getString("review")+"\n");
		   }
		  
		    stmt.close();
		    closeConnection();
		} 
		catch(SQLException se) 
			{ 
				System.out.println(se); 
			}

	   return allFilms;
   }

   //takes in passed id to search for specific film
   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs = stmt.executeQuery(selectSQL);
		    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	oneFilm.toString();
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   return oneFilm;
   }
   
 //takes in passed film title to search for specific film
   public Film getFilmByName(String title){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where title=" + "'" + title + "'";
		    ResultSet rs = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	oneFilm.toString();
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
		

	   return oneFilm;
  }
   
 //takes in passed film year to search for films in that year
   public ArrayList<Film> getFilmByYear(int year){
	   	ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where year=" + "'" + year + "'";
		    ResultSet rs = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	oneFilm.toString();
		    	allFilms.add(oneFilm);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
		

	   return allFilms;
  }
   
   public Film insertFilm(String id, String title,String year, String director,String stars, String review)
   {
	   
	   	openConnection();
	   	oneFilm=null;
	    // Create insert statement and execute it
		try{
			//use of prepared statement allows more security due to prevention of sql injection
		    PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO `films`(id,title,year,director,stars,review) VALUES (?, ?, ?, ?, ?,?)");
		    stmt1.setLong(1, Integer.parseInt(id));
		    stmt1.setString(2, title);
		    stmt1.setLong(3, Integer.parseInt(year));
		    stmt1.setString(4, director);
		    stmt1.setString(5, stars);
		    stmt1.setString(6, review);
		    stmt1.executeUpdate();
		    
		    int i = stmt1.executeUpdate();
		    //check to see if number of rows returned is > 0 to check for success in queries
 		    if(i > 0)
 		    {
 		    	System.out.println("Record Inserted Successfully");
 		    }
 		    else
 		    {
 		    	System.out.println("Error Inserting Record");
 		    }
 		    
		    stmt1.close();
		    closeConnection();
		} 
		catch(SQLException se) { System.out.println(se); }
	return emptyFilm;
	   
   }
   
   
   public ArrayList<Film> displayFilm(int id) {
		ArrayList<Film> allFilms = new ArrayList<Film>();
	   openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs = stmt.executeQuery(selectSQL);
		    while(rs.next()){
		    	oneFilm = getNextFilm(rs);
		    	allFilms.add(oneFilm);
		    	oneFilm.toString();
		    }
		    
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
	   
	   
		return allFilms;
  }
   
   public Film deleteFilm(int id)
   {
	   	openConnection();
	   	oneFilm=null;
	   	// Create delete statement and execute it
	 		try{
	 			
	 			PreparedStatement stmt = conn.prepareStatement("delete from films where id=?");
	 		    stmt.setLong(1, id);
	 		    stmt.executeUpdate();
	 		    int i = stmt.executeUpdate();
	 		    if(i > 0)
	 		    {
	 		    	System.out.println("Record Deleted Successfully");
	 		    }
	 		    else
	 		    {
	 		    	System.out.println("Error Deleting Record");
	 		    }
	 		    stmt.close();
	 		    closeConnection();
	 		} 
	 		catch(SQLException se) { System.out.println(se); }
	   	
	   	return emptyFilm;
   }
   
   
}
