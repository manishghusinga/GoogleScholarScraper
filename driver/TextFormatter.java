package driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * TextFormatter formats the data scraped by MyParser for easier reading.
 * 
 * @author Val Wikblad
 *
 */
public class TextFormatter {
  
  /**
   * formatAuthorExtract formats the data scraped from a single author's page, 
   * and then appends it to a specified output string before returning said 
   * string.
   * 
   * @param output                          The formatted output string.
   * @param authorsNames                    The name of the author.
   * @param numberOfCitations               The number of citations.
   * @param citationsSince2009              The i10 citations since 2009.
   * @param articleNames                    The names of the top three article.
   * @param citationsOfFirstFiveArticles    The first five article citations.
   * @param numberOfCoAuthors               The number of co-authors.
   * @return                                The formatted output string.
   */
  public String formatAuthorExtract(String output, String authorsNames, 
      String numberOfCitations, String citationsSince2009, ArrayList<String> 
  articleNames, String citationsOfFirstFiveArticles, String numberOfCoAuthors) 
  {
    //Initialize the iterator for searching through the list of articles.
    Iterator<String> articleIterator = articleNames.iterator();
    //Begin appending the scraped data to the output string.
    output += "--------------------------------------------------------------"
        + "---------"
        + "\n1. Name of Author:"
        + "\n\t" + authorsNames
        + "\n2. Number of All Citations:"
        + "\n\t" + numberOfCitations
        + "\n3. Number of i10-index after 2009:"
        + "\n\t" + citationsSince2009
        + "\n4. Title of the first 3 publications:"
        + "\n\t1-   " + articleIterator.next()
        + "\n\t2-   " + articleIterator.next()
        + "\n\t3-   " + articleIterator.next()
        + "\n5. Total paper citation (first 5 papers):"
        + "\n\t" + citationsOfFirstFiveArticles
        + "\n6. Total Co-Authors:"
        + "\n\t" + numberOfCoAuthors
        + "\n";
    return output;
  }
  
  /**
   * formatCoAuthorList formats the list of all co-authors scraped from all
   * Google Scholar pages that were passed to MyParser, before appending it to
   * the string containing the previously-formatted data from the individual
   * author pages.
   * 
   * @param output              The formatted output string.
   * @param coAuthorList        The set of co-authors scraped from all HTMLs.
   * @return                    The formatted output string.
   */
  public String formatCoAuthorList(String output, 
      TreeSet<String> coAuthorList) {
    //Initialize the iterator for the set of co-author names.
    Iterator<String> authorIterator = coAuthorList.iterator();
    //Append the total number of co-authors to the output string.
    output += "\n------------------------------------------------------------"
        + "-----------"
        + "\n7. Co-Author list sorted (Total: " + coAuthorList.size() + ")"
        + ":\n";
    //Begin iterating through the set of co-authors, appending their names
    //to the output string.
    while (authorIterator.hasNext()) {
      output += authorIterator.next() + "\n";
    }
    output += "\n";
    return output;
  }
}