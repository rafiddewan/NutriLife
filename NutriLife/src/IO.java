import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 
 * @author Matthew
 *
 */
public class IO {

	private static PrintWriter fileOut;//reads files
	private static BufferedReader fileIn;//writes files

	/**
	 * Creates the written file
	 *@param String
	 *@return void
	*/
	public static void createOutputFile(String fileName) {
		try {
			fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e) {
			System.out.println("*** Cannot create file: " + fileName + " ***");
		}
	}

	/**
	 *Opens a file and allows it to be written on the next line
	 * @param String
	 * @return void
	*/
	public static void openOutputFile(String fileName) {
		try {
			fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		} catch (IOException e) {
			System.out.println("*** Cannot open file: " + fileName + " ***");
		}
	}

	/**
	 *Opens a file and allows lines to be over written
	 *@param String
	 *@return void
	*/
	public static void openOutputFile2(String fileName) {
		try {
			fileOut = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
		} catch (IOException e) {
			System.out.println("*** Cannot open file: " + fileName + " ***");
		}
	}
	
	
	/**
	 *Allows prints into the file
	 *@param String
	 *@return void
	*/
	public static void print(String text) {
		fileOut.print(text);
	}

	/**
	 *Allows files to be printed on the next lines
	 *@param text
	 *@return void
	*/
	public static void println(String text) {
		fileOut.println(text);
		//System.out.println(text);
	}

	/**
	 *Closes the written file
	 *@return void
	*/
	public static void closeOutputFile() {
		fileOut.close();
	}

	/**
	 *Opens read-only files
	 * @param String
	 * @return void
	*/
	public static void openInputFile(String fileName) {
		try {
			fileIn = new BufferedReader(new FileReader(fileName));
			//System.out.println("opening " + fileName);
		} catch (FileNotFoundException e) {
			System.out.println("***Cannot open " + fileName + "***");
		}
	}

	/**
	 *Reads a specific line of a file
	 *@return String
	*/
	public static String readLine()
	// throws IOException
	// Note: if there's an error in this method it will return IOException
	{
		try {

			return fileIn.readLine();

		} catch (IOException e) {
			e.printStackTrace();
			return "errors";
		}

	}

	/**
	 *Closes the read only files
	 *@return void
	*/
	public static void closeInputFile() {// throws IOException
		// Note: if there's an error in this method it will return IOException
		try {
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


