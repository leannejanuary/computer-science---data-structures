/**
* @author Leanne January
* Program that implements a Hash Table which uses seperate chaining to deal
* with collisions
**/

import java.util.LinkedList;

/** Class ChainingHash **/
public class chainingHash {
	private int tableSize;
	private double itemsInTable;
	private LinkedList<PowerUsage>[] hashTable;
        private int insertProbes = 0;
	private int searchProbes;
   
    	/** Constructor 
	* @param tableSize The size of the hash table
	**/
    	public chainingHash(int tableSize) {
        	this.tableSize = tableSize;
		this.itemsInTable = 0;
        	hashTable = new LinkedList[tableSize];
        
        	for(int i=0; i<tableSize; i++) {
            		hashTable[i] = null;
        	}
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
 
 
	/** Function to insert a key and its corresponding data into hash table
	 * using seperate chaining to deal with collisions 
	* @param key The date/time key with which the data will be found
	* @param data The PowerUsage object to be inserted
	**/
    	public void insert(String key, PowerUsage data) {
		int index = hashFunction(key);
		
        	if(hashTable[index] == null) {
            		LinkedList<PowerUsage> items = new LinkedList<PowerUsage>();
            		items.add(data);
            		hashTable[index] = items;		
	    		itemsInTable++;
		}
			
        	else {
			hashTable[index].add(data);
			itemsInTable++;
        	}
    	}

	
	/** Function to convert a string into an integer that can be used as an
	 * index 
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
		return Math.abs(hashVal%this.tableSize);
	}
	
	/** Function to search for a key and return its corresponding data
	* @param key The key to be searched for
	**/
    	public PowerUsage search(String key) {
		this.searchProbes = -1;
        	if(key == null){
            		return null;
        	}	
        	int index = hashFunction(key);
        	LinkedList<PowerUsage> items = hashTable[index];
        
        	if(items == null){
            		return null;
        	}	
 
        	for(PowerUsage item : items) {
			this.searchProbes++;
			if(item.getDateTime().equals(key)){
                		return item;
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
