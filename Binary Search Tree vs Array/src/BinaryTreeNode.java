/**
 * Binary Tree Node class to be used by Binary Search Tree class
 * @author LeanneJanuary
 */
public class BinaryTreeNode {
	private String key;
	private PowerUsage value;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	/**
	 * Constructor class
	 * @param key the node's identifier
	 * @param value the data that the node stores
	 */
	public BinaryTreeNode(String key, PowerUsage value) {
		this.key = key;
		this.value = value;
	}

	
	/**
	 * Accessor method
	 * @return left node
	 */
	public BinaryTreeNode getLeft() {
		return left;
	}

	/** 
	 * Accessor method
	 * @return right node
	 */
	public BinaryTreeNode getRight() {
		return right;
	}

	/** 
	 * Accessor method
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/** Accessor method
	 * @return value
	 */
	public PowerUsage getValue() {
		return value;
	} 

	

	/**
	 * Inserts new node into the tree
	 * @param key key of new node
	 * @param value value of new node
	 */
	public void insert (String key, PowerUsage value) {
		if (key.compareTo(this.key) < 0) {
			if (left == null) {
				left = new BinaryTreeNode(key, value);
			}
			else {
				left.insert(key, value);
			}
		}
		else if (key.compareTo(this.key) > 0 ) {
			if (right == null) {
				right = new BinaryTreeNode(key, value);
			}
			else {
				right.insert(key, value);
			}
		}
		else {
			this.value = value;
		}
	}

	/**
	 * Finds the node with the matching key to the query
	 * @param keyQuery the key to be found
	 * @param opCount the number of operations it takes to find the key
	 * @return the associated dateTime, power and voltage values of the 
	 * query 
	 */
	public PowerUsage find(String keyQuery, int opCount) {
		if (keyQuery.compareTo(this.key) == 0) {
			System.out.println("Number of Iterations: " + opCount +".");
			return value;
		}
		else if (keyQuery.compareTo(this.key) < 0) {
			return (left == null) ? null:left.find(keyQuery, opCount+1);
		}
		else {
			return (right == null) ? null:right.find(keyQuery, opCount+1);
		}
	}	
}
