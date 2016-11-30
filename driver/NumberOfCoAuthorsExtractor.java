package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NumberOfCoAuthorsExtractor takes a Google Scholar page's HTML and returns 
 * the number of co-authors.
 * 
 * @author Val Wikblad
 *
 */
public class NumberOfCoAuthorsExtractor {
  
  /**
   * extract takes a string representation of the raw HTML of a Google Scholar
   * page and then returns a string containing the number of co-authors the 
   * author has had.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public String extract(String rawHtml) {
    //Creates a substring of the co-author sidebar's HTML.
    String redactedHtmlString = rawHtml.substring(rawHtml.indexOf(
        ">&nbsp;Co-authors</div><div style=\"padding:.5em; border:.3em solid "
        + "#9FD9A7\">"), rawHtml.indexOf("</div></div></div></div><div "
            + "class=\"g-unit\">"));
    //Creates the REGEX used to find the number of co-authors.
    String reForAuthorExtraction = "title=\"(.*?)\"";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(redactedHtmlString);
    //Initializes the counter used to count the number of co-authors.
    int counter = 0;
    //Increments the counter for every co-author found in the sidebar.
    while (matcherObject.find()) {
      counter++;
    }
    //Converts the integer into a string before returning it.
    return counter + "";
  }
}