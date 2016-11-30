package driver;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * MyParser takes one or more Google Scholar pages, scrapes them for certain
 * data, and then returns the collated data.
 * 
 * @author Val Wikblad
 *
 */
public class MyParser {
  
  /**
   * Takes an HTML and converts it into a string.
   */
  private static RawHtmlGetter htmlGetter = new RawHtmlGetter();
  
  /**
   * Scrapes the names of the first three articles.
   */
  private static ArticleNameExtractor articleNameExtractor = new 
      ArticleNameExtractor();
  
  /**
   * Scrapes the name of the author.
   */
  private static AuthorNameExtractor authorNameExtractor = new 
      AuthorNameExtractor();
  
  /**
   * Formats the collated data before printing.
   */
  private static TextFormatter textFormatter = new TextFormatter();
  
  /**
   * Scrapes the total number of citations.
   */
  private static CitationNumberExtractor citationNumberExtractor = new 
      CitationNumberExtractor();
  
  /**
   * Scrapes the names of all co-authors.
   */
  private static CoAuthorNamesExtractor coAuthorNamesExtractor = new 
      CoAuthorNamesExtractor();
  
  /**
   * Scrapes the citations of the first five articles.
   */
  private static FirstFiveArticleCitationExtractor articleCitationExtractor = 
      new FirstFiveArticleCitationExtractor();
  
  /**
   * Scrapes the number of i10 citations since 2009.
   */
  private static I10Since2009Extractor i10Extractor = new 
      I10Since2009Extractor();
  
  /**
   * Scrapes the total number of co-authors.
   */
  private static NumberOfCoAuthorsExtractor coAuthorNumExtractor = new 
      NumberOfCoAuthorsExtractor();
  
  /**
   * Used to print the collated data to the shell.
   */
  private static ShellPrinter shellPrinter = new ShellPrinter();
  
  /**
   * Used to print the collated data to a text file.
   */
  private static TextFilePrinter textFilePrinter = new TextFilePrinter();
  
  /**
   * Contains the string representation of a page's raw HTML.
   */
  private static String rawHtml;
  
  /**
   * Contains the name of a page's author.
   */
  private static String authorName;
  
  /**
   * Contains the number of citations from a page.
   */
  private static String numberOfCitations;
  
  /**
   * Contains the number of i10 cites since 2009 from a page.
   */
  private static String citationsSince2009;
  
  /**
   * Contains the names of the first three articles from a page.
   */
  private static ArrayList<String> articleNames;
  
  /**
   * Contains the cites of the first five articles from a page.
   */
  private static String citationsOfFirstFiveArticles;
  
  /**
   * Contains the number of co-authors from a page.
   */
  private static String numberOfCoAuthors;
  
  /**
   * Contains the names of all co-authors from all pages scraped.
   */
  private static TreeSet<String> coAuthorList = new TreeSet<String>();

  /**
   * This method takes the HTML files of one or more Google Scholar pages, and
   * optionally takes the name of a text file. The pages are scraped for
   * specific bits of data, and then a text representation of the collated data
   * for all the pages specified is printed onto the shell. If the name of a
   * text file is specified, instead of printing the data to the shell, the 
   * data will be printed in the specified text file, overwriting its contents.
   * If a file of the same name already exists, it will be overwritten with the
   * data.
   * 
   * @param args       HTML files, and optionally a text file for output.
   */
  public static void main(String[] args) {
    //Initialize output string.
    String output = "";
    //Convert the names of the HTML files into an array.
    String inputFiles[] = args[0].split(",");
    //First attempt to parse the HTML files.
    try {
      //For each HTML file specified...
      for (String inputFile : inputFiles) {
        //Parse the HTML file into a raw HTML string.
        rawHtml = htmlGetter.get(inputFile);
        //Scrape everything required.
        authorName = authorNameExtractor.extract(rawHtml);
        numberOfCitations = citationNumberExtractor.extract(rawHtml);
        citationsSince2009 = i10Extractor.extract(rawHtml);
        articleNames = articleNameExtractor.extract(rawHtml);
        citationsOfFirstFiveArticles = articleCitationExtractor.extract(
            rawHtml);
        numberOfCoAuthors = coAuthorNumExtractor.extract(rawHtml);
        coAuthorList = coAuthorNamesExtractor.extract(rawHtml, coAuthorList);
        //After scraping, try to format the data scraped from the current page.
        output = textFormatter.formatAuthorExtract(output, authorName, 
            numberOfCitations, citationsSince2009, articleNames, 
            citationsOfFirstFiveArticles, numberOfCoAuthors);
      }
      //After scraping and formatting individual pages, format the list of
      //all co-authors scraped from all pages.
      output = textFormatter.formatCoAuthorList(output, coAuthorList);
      //Then, try to print the formatted text to a text file, if specified.
      try {
        textFilePrinter.print(output, args[1]);
      }
      //If no text file was specified, print to the shell.
      catch (Exception error) {
        shellPrinter.print(output);
      }
    }
    //If any of the HTML files were broken or invalid, throw an error.
    catch (Exception error) {
      System.out.println("Malformed URL or HTML file, or cannot connect to "
          + "specified URL.");
    }
  }
}
