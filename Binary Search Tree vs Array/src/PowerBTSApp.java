/**
 * PowerBTSApp is a class that reads in data from cleaned_data.csv, stores it 
 * in PowerUsage objects and adds those objects to a Binary Search tree where 
 * the data items can be searched for. 
 * @author LeanneJanuary
 */
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PowerBTSApp {
	private PowerBTSApp() {}
		private final static String dataFile = "cleaned_data.csv";
		private final static BinarySearchTree powerUsageData = new BinarySearchTree();
		private static int opCount = 0;

		/**
		 * Insert data from dataFile into PowerUsage Binary Search Tree
		 * @param dataFile the data to be stored
		 */
		private static void insertData(String dataFile)
			throws FileNotFoundException {

			Scanner file = new Scanner(new File(dataFile));
			
			// skip first line of dataFile
			file.nextLine();

			file.useDelimiter(",");
			
			while (file.hasNextLine()){
				String dateTime = file.next();
				String power = file.next();
				file.next();
				String voltage = file.next();

				powerUsageData.insert(dateTime, new PowerUsage(dateTime, power, voltage));
				file.nextLine();
			}

		}
		
		/**
		 * Prints all PowerUsage objects in the Binary Search Tree
		 */
		private static void printAllDateTimes() {
			powerUsageData.printTree(powerUsageData.getRoot());
		}
		/** 
		 * Searches for a specific dateTime value in the Binary
		 * Search Tree
		 * @param dateTime the key to be found
		 * @return the dateTime with its corresponding power and 
		 * voltage values, if key is found
		 */
		private static String printDateTime (String dateTime) {
			String dateTimeQuery = dateTime;
			PowerUsage queryAnswer = powerUsageData.find(dateTimeQuery, opCount);
		       if (queryAnswer != null){
				return queryAnswer.toString();
			}
		       else {
		       		return "Date/time not found.";
		       }
			
		}

	public static void main(String[] args)
		throws IOException {

		try {
			insertData(dataFile);
			
			if (args.length==0) {
				printAllDateTimes();
			}
			else {
				for (String argument:args) {
				 	System.out.println(printDateTime(argument));
				}
			}
		}	

		catch
		(FileNotFoundException fileExcep) {
			System.out.println(dataFile + " not found.");
			System.exit(-1);
		}
	}
}
