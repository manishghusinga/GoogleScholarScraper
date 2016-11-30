package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FirstFiveArticleCitationExtractor takes a Google Scholar page's HTML and 
 * returns the number of times their top five articles have been cited.
 * 
 * @author Val Wikblad
 *
 */
public class FirstFiveArticleCitationExtractor {
  
  /**
   * extract takes a raw HTML string representation of a Google Scholar page 
   * and returns the number of times the author's top five articles have been
   * cited.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @return            The scraped data.
   */
  public String extract(String rawHtml) {
    //Creates the REGEX used to find the number of times the articles have been
    //cited.
    String reForAuthorExtraction = "\">([0-9]*)</a>"
        + "</td><td id=\"col-asterisk\">";
    //Initializes the integer for the total number of citations.
    int output = 0;
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(rawHtml);
    //Finds the citation totals for each article and adds them together.
    for (int i = 1; i < 6; i++) {
      matcherObject.find();
      output += Integer.parseInt(matcherObject.group(1));
    }
    //Converts the integer into a string while being returned.
    return output + "";
  }
}