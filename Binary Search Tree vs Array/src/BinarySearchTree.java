/**
 * Implementation of a BinarySearchTree to be used to store data items and
 * search through it.
 * @author LeanneJanuary
 * {@link https://www.moreofless.co.uk/binary-search-tree-bst-java/}
 */
public class BinarySearchTree {
	private BinaryTreeNode root;

	/**
	 * Insert a new node into the tree
	 * @param key the identifier of the node
	 * @param value the object or data to be stored in the node
	 */
	public void insert(String key, PowerUsage value) {
		if (root == null) {
			root = new BinaryTreeNode(key, value);
		}
		else {
			root.insert(key, value);
		}
	}

	/**
	 * Find a specific node based on its key
	 * @param key item that we are looking for
	 * @param opCount the number of operations it takes to find the query
	 * @return the query in the form of a PowerUsage object
	 */
	public PowerUsage find(String key, int opCount) {
		if (root == null) {
			System.out.println("Number of Iterations: " + opCount + ".");
			return null;
		}
		else {
			return root.find(key, opCount+1);
		}
		
	}
	
	/**
	 * Prints the whole tree
	 * @param node the point at which it must print the tree from
	 */
	public void printTree(BinaryTreeNode node) {
		if (node != null) {
			printTree(node.getRight());
			System.out.println(node.getValue().toString());
			printTree(node.getLeft());
		}
	}

	/** Accessor method
	 * @return the starting node
	 */
	public BinaryTreeNode getRoot() {
		return root;
	}
}
