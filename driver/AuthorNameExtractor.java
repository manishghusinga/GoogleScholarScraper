package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AuthorNameExtractor takes a Google Scholar page's HTML and returns the name
 * of the author whose page it is.
 * 
 * @author Val Wikblad
 *
 */
public class AuthorNameExtractor {
  
  /**
   * extract takes a string representation of the raw HTML of a Google Scholar
   * page and then returns a string containing the name of said page's author.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public String extract(String rawHtml) {
    //Create the REGEX that needs to be found.
    String reForAuthorExtraction =
        "<span id=\"cit-name-display\" "
            + "class=\"cit-in-place-nohover\">(.*?)</span>";
    //Initialize the output string.
    String output;
    output = "";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHtml);
    //Find the author name.
    while (matcherObject.find()) {
      output = matcherObject.group(1);
    }
    return output;
  }
}