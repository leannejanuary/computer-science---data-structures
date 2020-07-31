/**
 * PowerHash is a class that reads in data from an input file, stores it
 * in PowerUsage objects and adds those objects to a Hash with either linear 
 * probing, quadratic probing or seperate chaining as the collision scheme
 * 
 * The user can specifies the table size, data file, collision scheme and 
 * number of keys to be searched for
 *
 * The performance is measured by counting the amount of probes needed to 
 * insert and search for data - these values are written to files
 *  
 * @author LeanneJanuary
 */
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;

public class PowerHash {

	public static linearHash powerUsageData1;
	public static quadraticHash powerUsageData2;
	public static chainingHash powerUsageData3;
	public static ArrayList<String> keys = new ArrayList<String>();

	private PowerHash() {}

	/**
	 * Insert data from dataFile into Hash Table		
	 * @param dataFile the data to be stored
	 */
	public static void insertData(String dataFile, String collisionScheme, int tableSize)
		throws FileNotFoundException {
			// read in file
			Scanner file = new Scanner(new File(dataFile));
			
			// skip first line of dataFile
			file.nextLine();

			file.useDelimiter(",");

			// hash functions created
			switch (collisionScheme) {
				case "linear":
					powerUsageData1 = new linearHash(tableSize);
					break;
				case "quadratic":
					powerUsageData2 = new quadraticHash(tableSize);
					break;
				case "chaining":
					powerUsageData3 = new chainingHash(tableSize);
			}

			// inserts data from file to correct hash function
			while (file.hasNextLine()){
				String dateTime = file.next();
				String power = file.next();
				file.next();
				String voltage = file.next();
				
				switch (collisionScheme) {
                                case "linear":
                                        powerUsageData1.insert(dateTime, new PowerUsage(dateTime, power, voltage));
                                        break;
                                case "quadratic":
                                        powerUsageData2.insert(dateTime, new PowerUsage(dateTime, power, voltage));
                                        break;
                                case "chaining":
                                       powerUsageData3.insert(dateTime, new PowerUsage(dateTime, power, voltage));
				       break;
				}
				
				// add key values to an array to be searched
				keys.add(dateTime);
				file.nextLine();
			}
			// output load factor, total insert probes and display hash table
			// write the total insert probes to a file
			switch (collisionScheme) {
				case "linear":
					System.out.println("Load Factor: " + powerUsageData1.loadFactor());
					System.out.println("Total Insert Probes: " + powerUsageData1.getInsertProbes());
					System.out.println("Hash Table: ");
					powerUsageData1.printHash();
                                        writeInsertCountToFile(String.valueOf(powerUsageData1.getInsertProbes()), collisionScheme, tableSize);
                                        break;

                                case "quadratic":
                                        writeInsertCountToFile(String.valueOf(powerUsageData2.getInsertProbes()), collisionScheme, tableSize);
					System.out.println("Load Factor: " + powerUsageData2.loadFactor());
                                        System.out.println("Total Insert Probes: " + powerUsageData2.getInsertProbes());
                                        System.out.println("Hash Table: ");
                                        powerUsageData2.printHash();
                                        break;

                                case "chaining":  
                                       writeInsertCountToFile(String.valueOf(powerUsageData3.getInsertProbes()), collisionScheme, tableSize);
				       System.out.println("Load Factor: " + powerUsageData3.loadFactor());
                                       System.out.println("Total Insert Probes: " + powerUsageData3.getInsertProbes());
                                       System.out.println("Hash Table: ");
                                       powerUsageData3.printHash();
                                       break;

		}
	}
	
	/**
         * Check if a number is a prime numer
         * @param num the number to be checked
	 * @return true if composite number and false if prime if false number
         */
	public static boolean isPrime(int num){
		boolean a = true;
		for (int i = 2; i<num; i++){
			if (num%i == 0){
				a = false;
				break;
			}
		}
		return a;
	}


	/** 
	* Searches for a specific dateTime value in the AVL Tree
	* @param dateTime the key to be found
	* @return the dateTime with its corresponding power and 
	* voltage values, if key is found
	*/
	public static void printDateTime (int numKeys, int tableSize, String collisionScheme) {	
		
		int i = 0;
		while (i < numKeys+1) {
			// write search probes to a file
			switch (collisionScheme) {
                                case "linear":
					String dateTimeQuery1 = keys.get(i);
					powerUsageData1.search(dateTimeQuery1);
                               		writeSearchCountToFile(String.valueOf(powerUsageData1.getSearchProbes()), collisionScheme, tableSize);
					
                                        break;
                                case "quadratic":
					String dateTimeQuery2 = keys.get(i);
                                        powerUsageData2.search(dateTimeQuery2);
					writeSearchCountToFile(String.valueOf(powerUsageData2.getSearchProbes()), collisionScheme, tableSize);
					
                                        break;
                                case "chaining":
                                        String dateTimeQuery3 = keys.get(i);
                                        powerUsageData3.search(dateTimeQuery3);
                                        writeSearchCountToFile(String.valueOf(powerUsageData3.getSearchProbes()), collisionScheme, tableSize);
                                       break;

                	}	
			i++;
		}
	}

	/** Writes the insert count value to a file
	 * @param data the data to be written to the file
         */
	private static void writeInsertCountToFile(String data, String collisionScheme, int tableSize) {
        	File file = new File("Insert"+collisionScheme+tableSize+".txt");
                FileWriter f = null;
                try {
			f = new FileWriter(file);
		     	f.write(data);
                }
		catch (IOException e) {
	 		System.out.println("Error occured");
                        System.exit(-1);
                }
                finally {
                        try {
		  		f.close();
                        }
                        catch (IOException e) {
                                System.exit(-1);
                        }
                }
	}

	public static int i = 0;
	/** Writes the search count value to a file
         * @param data the data to be written to the file
         */
	private static void writeSearchCountToFile(String data, String collisionScheme, int tableSize) {
		File file = new File("Search"+collisionScheme+tableSize+".txt");
                FileWriter f = null;
	    	try {
    			f = new FileWriter(file, true);
			i++;
			f.write(i + "	"  + data + "\n");
                }
	       	catch (IOException e) {
		 	System.out.println("Error occured");
                        System.exit(-1);
                }
                finally {
                        try {
				f.close();
                        }
                        catch (IOException e) {
                                System.exit(-1);
                        }
                }
        }


	public static void main(String[] args)
		throws IOException {

		try {
			int tableSize = Integer.parseInt(args[0]);
			if (tableSize >= 653 && tableSize <= 1009){
				if (isPrime(tableSize) == false) {
					System.out.println("Table Size is not a prime number.");
					System.exit(-1);
				}
			}
			else {
				System.out.println("Table Size not within range.");
				System.exit(-1);
			}

			String collisionScheme = args[1];
			String dataFile = args[2];

			int numKeys =Integer.parseInt(args[3]);

			insertData(dataFile, collisionScheme, tableSize); 
			printDateTime(numKeys, tableSize, collisionScheme);
		}

		catch
		(FileNotFoundException fileExcep) {
			System.out.println("File not found.");
			System.exit(-1);
		}
	}
}
