package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Film;

public class updateFilmDAO {

	Film oneFilm = null;
	Film emptyFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "elfitob";
	String password = "Lerftase4";
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;

	private void openConnection(){
		// loading jdbc driver for mysql
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch(Exception e) 
		{ 
			System.out.println(e); 
		}

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			System.out.println("Connection made!");
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
				System.out.println("id:"+rs.getString("id")+"\ntitle:"+rs.getString("title")
				+"\nyear:"+rs.getString("year")+"\ndirector:"+rs.getString("director")
				+"\nstars:"+rs.getString("stars")+"\nreview:"+rs.getString("review")+"\n");
			}

			stmt.close();
			closeConnection();
		} catch(SQLException se) { System.out.println(se); }


		return allFilms;
	}

	public Film updateFilm(String id,String title,String year, String director,String stars, String review) {

		openConnection();
		oneFilm=null;
		String a = "";
		// Create update statement and execute it
		try{

			PreparedStatement stmt = conn.prepareStatement("Update films set title=?,year=?,director=?,"
					+ "stars=?,review=? where id="+id);
			stmt.setString(1, title);
			stmt.setString(2, year);
			stmt.setString(3,director);
			stmt.setString(4, stars);
			stmt.setString(5, review);    
			stmt.executeUpdate();
			int i = stmt.executeUpdate();
			if(i > 0)
			{
				System.out.println("Record Updated Successfully");
			}
			else
			{
				System.out.println("Error updating Record");
			}

			stmt.close();
			closeConnection();
		} 
		catch(SQLException se) { System.out.println(se); }

		return emptyFilm;

	}

}
