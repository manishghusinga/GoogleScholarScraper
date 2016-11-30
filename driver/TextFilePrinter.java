package driver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * TextFilePrinter takes a string and then attempts to print it to a specified
 * text file. If the file does not exist, it creates the file. If the file does
 * exist, it overwrites the file's contents with the string.
 * 
 * @author Val
 *
 */
public class TextFilePrinter {
  
  /**
   * print takes an output string and the name of a file, then prints the 
   * string to a new file of the specified name or, if the file exists, 
   * overwrites its contents with the output string.
   * 
   * @param output      The formatted string to be printed to the text file.
   * @param fileName    The name of the file to be created and/or written to.
   */
  public void print(String output, String fileName) {
    //Create a new file of the specified name.
    File printedTxt = new File(fileName);
    //Attempt to write to the file.
    try {
      //Create the objects for writing to the file.
      FileWriter thing = new FileWriter(printedTxt);
      BufferedWriter txtPrinter = new BufferedWriter(thing);
      //Write to the file.
      txtPrinter.write(output);
      //Close the file after writing to it.
      txtPrinter.close();
    }
    //Catch any errors that might occur.
    catch (Exception error) {
      System.out.println("Whoops!");
    }
  }
}