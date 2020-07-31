/**
* @author Leanne January
* Program that implements a Hash Table which uses quadratic probing to deal with collisions
**/

import java.lang.IndexOutOfBoundsException;

/** Class quadraticHash **/
public class quadraticHash {
	private PowerUsage[] hashTable;
	private int tableSize;
	private double itemsInTable;
	private int insertProbes = 0;
	private int searchProbes = 0;
	
	/** Constructor 
	* @param tableSize The size of the hash table
	**/
	public quadraticHash (int tableSize) {
		this.itemsInTable = 0;
		this.tableSize = tableSize;
		this.hashTable = new PowerUsage[tableSize];
	}
	
	/** Function to calculate load factor of hash table 
	* @return the load factor 
	**/
	public double loadFactor(){ 
		return Math.round((itemsInTable/tableSize) * 100.0)/100.0;
	}
	
	/** Accessor Method 
	* @return the size of the hash table 
	**/
	public int getTableSize() { return this.tableSize; }
	
	/** Accessor Method 
	* @return the number of items in the hash table 
	**/
	public double getItemsInTable() { return this.itemsInTable; }
	
	/** Accessor Method 
	* @return the number of insert probes 
	**/
	public int getInsertProbes() { return this.insertProbes; }
	
	/** Accessor Method 
	* @return the number of search probes
	**/
	public int getSearchProbes() { return this.searchProbes; }
	
	/** Function to insert a key and its corresponding data into hash table using quadratic probing to deal with collisions 
	* @param key The date/time key with which the data will be found
	* @param data The PowerUsage object to be inserted
	**/
	public void insert(String key, PowerUsage data) {
		int index = hashFunction(key);
		int i = index;
		int x = 1;
		do {
			if (hashTable[i] == null) {
				hashTable[i] = data;
				itemsInTable++;
				return;
			}
			else {
				this.insertProbes++;
				if (this.insertProbes > tableSize) {
					throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
				}
				i = (i + x*x) % tableSize;
				x++;
			}
		}	while (i != index);
	}
	
	/** Function to convert a string into an integer that can be used as an index 
	* @param key The date/time key to be hashed
	**/
	public int hashFunction (String key) {
		if(key == null){
            return -1;
        }
		int hashVal = 0;
		for (int i =0; i<key.length(); i++) {
			hashVal = (37 * hashVal) + key.charAt(i);
		}
		return Math.abs(hashVal%tableSize);
	}
	
	/** Function to search for a key and return its corresponding data
	* @param key The key to be searched for
	**/
	public PowerUsage search(String key) {
		this.searchProbes = 0;
		if(key == null){
            		return null;
        	}	
		int index = hashFunction(key);
		int x = 1;
		while (hashTable[index] != null) {
			if (key.equals(hashTable[index].getDateTime())) {
				return hashTable[index];
			}
			else {
				this.searchProbes++;
				index = (index + x*x) % tableSize;
				x++;
			}
		}
		return null;
	}
	
	/** Function to display hash table **/
	public void printHash () {
		for (int i = 0; i<hashTable.length; i++){
			if (hashTable[i] == null){
				System.out.println(i + " : 0");
			}
			else {
				System.out.println(i + " : " + hashTable[i]);
			}
		}
	}
}
