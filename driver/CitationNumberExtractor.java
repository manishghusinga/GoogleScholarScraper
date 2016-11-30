package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CitationNumberExtractor takes a Google Scholar page's HTML and returns the 
 * number of times the author has had their articles cited.
 * 
 * @author Val Wikblad
 *
 */
public class CitationNumberExtractor {
  
  /**
   * extract takes a string representation of the raw HTML of a Google Scholar
   * page and then returns a string containing the number of times the author
   * has been cited.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public String extract(String rawHtml) {
    //Create the REGEX that needs to be found.
    String reForAuthorExtraction = "Citations</a></td><td class="
        + "\"cit-borderleft cit-data\">([0-9]*)</td>";
    //Initialize the output string.
    String output;
    output = "";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHtml);
    //Find the number of times the author has been cited.
    while (matcherObject.find()) {
      output = matcherObject.group(1);
    }
    return output;
  }
}