package utility;

import java.util.Map;

import model.Film;


/** Some helper utilities for making Strings describing films. */

public class FilmUtils {
  /** makeTextList returns a textual list describing the film,
   *  suitable for putting in a dialog box. 
   */
  
	  public static String makeTextList(Film f) {
	    String list = makeEntry("", f.toString());
	                  
	    return(list);
	  }
	private static String makeEntry(String prompt, 
	                                   String string) {
	    return(String.format("  %s %s\n",
	                         prompt, string));
	  }
  
}
