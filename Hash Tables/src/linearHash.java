/**
* @author Leanne January
* Program that implements a Hash Table which uses linear probing to deal with collisions
**/

import java.lang.IndexOutOfBoundsException;

/** Class LinearHash **/
public class linearHash {
	private PowerUsage[] hashTable;
	private int tableSize;
	private double itemsInTable;
	private int insertProbes = 0;
	private int searchProbes = 0;
	
	/** Constructor 
	* @param tableSize The size of the hash table 
	**/
	public linearHash (int tableSize) {
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

	/** Function to insert a key and its corresponding data into hash table using linear probing to deal with collisions 
	* @param key The date/time key with which the data will be found
	* @param data The PowerUsage object to be inserted
	**/
	public void insert(String key, PowerUsage data) {
		int index = hashFunction(key);
		int i = index;
		
		if (itemsInTable == tableSize){
			throw new IndexOutOfBoundsException("Index " + i + " out of bounds.");
		}
		do {
			if (hashTable[i] == null) {
				hashTable[i] = data;
				itemsInTable++;
				return;
			}
			else {
				this.insertProbes++;
				i++;
				i = i % tableSize;
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
		while (hashTable[index] != null) {
			if (key.equals(hashTable[index].getDateTime())) {
				return hashTable[index];
			}
			else {
				this.searchProbes++;
				index++;
				index = index % tableSize;
			}
		}
		return null;
	}
	
	/** Function to display the hash table **/
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
