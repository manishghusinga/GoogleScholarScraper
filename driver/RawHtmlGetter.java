package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * RawHTMLGetter takes the specified URLs and/or HTML files, then returns 
 * string representations of their raw HTML.
 * 
 * @author Val Wikblad
 *
 */
public class RawHtmlGetter {
  
  /**
   * get takes a string containing the name of an HTML file or a URL, then
   * returns a string representation of the raw HTML found.
   * 
   * @param sourceLocation  The URL or HTML file to be converted into a string.
   * @return                The string of raw HTML.
   * @throws Exception
   */
  public String get(String sourceLocation) throws Exception {
    //Create something to assemble the HTML source as it's parsed
    StringBuilder rawHtml = new StringBuilder();
    //Try to connect to the URL, if specified.
    URL url = new File(sourceLocation).toURI().toURL();
    //Create something to buffer the file's source as it's being read.
    BufferedReader sourceReader =
        new BufferedReader(new InputStreamReader(url.openStream()));
    String sourceLine;
    //Assemble the HTML source as each source code line is read.
    while ((sourceLine = sourceReader.readLine()) != null) {
      rawHtml.append(sourceLine);
    }
    sourceReader.close();
    //Convert the raw HTML into a string before returning it.
    return rawHtml.toString();
  }
}