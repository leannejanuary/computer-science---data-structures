/**
 * PowerArrayApp reads in values from cleaned_data.csv, stores them in  
 * PowerUsage objects and inserts them into an array.
 * @author LeanneJanuary
 */

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PowerArrayApp {
	private PowerArrayApp() {}
		private final static String dataFile = "cleaned_data.csv";
		private final static PowerUsage[] powerUsageData = new PowerUsage[500];
		/**
		 * Stores the number of operations performed by the data 
		 * structure
		 */
		private static int opCount = 0;

		/**
		 * Inserts data from dataFile into PowerUsage[]
		 * @param datafile the file that contains all the data
		 */
		private static void insertData(String dataFile)
			throws FileNotFoundException {

			Scanner file = new Scanner(new File(dataFile));
			
			// skip first line of dataFile
			file.nextLine();

			file.useDelimiter(",");
			
			for(int i=0; i<powerUsageData.length; i++) {
				String dateTime = file.next();
				String power = file.next();
				file.next();
				String voltage = file.next();

				powerUsageData[i] = new PowerUsage(dateTime, power, voltage);
				file.nextLine();
			}

		}
		
		/**
		 * Prints entire array of PowerUsage objects
		 */
		private static void printAllDateTimes() {
			for (int i=0; i<powerUsageData.length; i++) {
				System.out.println(powerUsageData[i]);
			}
		}

		/** 
		 * Searches for a specific DateTime
		 * @param dateTime the dateTime to be searched for
		 * @return DateTime and corresponding voltage and power values
		 * if found
		 */
		private static String printDateTime (String dateTime) {
			String dateTimeQuery = dateTime;
			String queryAnswer = "Date/time not found";
			for (int i=0; i<powerUsageData.length; i++) {
				opCount++;
				if (dateTimeQuery.equals(powerUsageData[i].getDateTime())) {
					queryAnswer = powerUsageData[i].toString();
					return queryAnswer;
				}
			}
			return queryAnswer;
		}

		/** 
		 * Accessor method
		 * @return total number of operations
		 */
		private static int getOpCount() {
			return opCount;
		}

		/**
		 * Mutator method that resets the count back to 0 after 
		 * counting operations of one object.
		 */
		private static void resetOpCount() {
			opCount = 0;
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
					System.out.println("Number of Iterations: " + getOpCount());
					resetOpCount();
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
