package driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeSet;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * CoAuthorNamesExtractor takes a Google Scholar page's HTML and returns the 
 * names of all co-authors of the author's articles.
 * 
 * @author Val Wikblad
 *
 */
public class CoAuthorNamesExtractor {
  
  /**
   * extract takes a string representation of the raw HTML of a Google Scholar
   * page and a TreeSet of strings that contains the co-authors of all authors
   * that have been processed by MyParser.java, and returns the same TreeSet
   * with this author's co-authors added to it.
   * 
   * @param rawHTML     The raw HTML to be scraped.
   * @param coAuthors   The set containing the names of all scraped co-authors.
   * @return            The set containing the names of all scraped co-authors.
   */
  public TreeSet<String> extract(String rawHtml, TreeSet<String> coAuthors) {
    //Creates a substring for just the co-author sidebar alone.
    String redactedHtmlString = rawHtml.substring(rawHtml.indexOf(
      ">&nbsp;Co-authors</div><div style=\"padding:.5em; border:.3em solid "
      + "#9FD9A7\">"), rawHtml.indexOf("</div></div></div></div><div "
          + "class=\"g-unit\">"));
    //Creates the REGEX used to find the names of individual co-authors.
    String reForAuthorExtraction = "title=\"(.*?)\"";
    Pattern patternObject = Pattern.compile(reForAuthorExtraction);
    Matcher matcherObject = patternObject.matcher(redactedHtmlString);
    //Searches for the individual co-authors.
    while (matcherObject.find()) {
      coAuthors.add(StringEscapeUtils.unescapeHtml3(matcherObject.group(1)));
    }
    return coAuthors;
  }
}