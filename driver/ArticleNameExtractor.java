package driver;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ArticleNameExtractor extracts the names of the first three articles
 * on the Google Scholar page.
 * 
 * @author Val Wikblad
 *
 */
public class ArticleNameExtractor {
  
  /**
   * extract takes a raw HTML string representation of a Google Scholar page 
   * and returns the names of the first three articles as an ArrayList of 
   * strings.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public ArrayList<String> extract(String rawHtml) {
    //Create the ArrayList of strings for the article names.
    ArrayList<String> output = new ArrayList<String>();
    //Create the REGEX that needs to be found.
    String reForAuthorExtraction = "cit-dark-large-link\">(.*?)"
        + "</a><br><span class=\"cit-gray\">";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHtml);
    //Search for each of the first three articles in turn.
    for (int i = 1; i < 4; i++) {
      matcherObject.find();
      output.add(matcherObject.group(1));
    }
    return output;
  }
}