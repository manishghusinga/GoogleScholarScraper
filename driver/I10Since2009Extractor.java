package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I10Since2009Extractor takes a Google Scholar page's HTML and returns the 
 * number of i10 citations the author has had since 2009.
 * 
 * @author Val Wikblad
 *
 */
public class I10Since2009Extractor {
  
  /**
   * extract takes a string representation of the raw HTML of a Google Scholar
   * page and then returns a string containing the number of i10 citations
   * the author has had since 2009.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public String extract(String rawHtml) {
    //Creates the REGEX used to find the citations.
    String reForAuthorExtraction = "i10-index</a></td><td class="
        + "\"cit-borderleft cit-data\">([0-9]*)</td><td class="
        + "\"cit-borderleft cit-data\">([0-9]*)</td></tr></tbody></table></td>"
        + "<td valign=\"top\" width=\"475\"><b>Citations to my articles";
    //Initializes the output string.
    String output;
    output = "";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHtml);
    //Finds the i10 citations since 2009.
    while (matcherObject.find()) {
      output = matcherObject.group(2);
    }
    return output;
  }
}