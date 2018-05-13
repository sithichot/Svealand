import java.util.*;

public class HashTable {
	private List[] theList;
	private int size = 101;
	private int currentSize;

	public HashTable() {
		theList = new LinkedList[size];
		for (int i = 0; i < theList.length; i++) {
			theList[i] = new LinkedList<Vertex>();
		}

	}

	public int size() {
		return currentSize;
	}

	public List[] getArray() {
		return theList;
	}

	public boolean insert(Vertex x) {

		if (x != null) {
			List<Vertex> whichList = theList[myHash(x.name)]; // insert it to the hashtable, but if the item already
																// exist in the table, do nothing
			if (!whichList.contains(x)) {
				whichList.add(x);
				currentSize = currentSize + 1;
			}
			
			if (currentSize + 1 > theList.length) {
				reHash();
			}
			
			return true;
		}
		
		return false;
	}

	/*
	 * public void remove(Vertex x) { ListVertex whichList = theList[myHash(x)]; //
	 * removes item from hashtable if (whichList.contains(x)) { whichList.remove(x);
	 * currentSize--;
	 */

	private void reHash() {
		List<Vertex>[] oldList = theList;
		theList = new List[(2 * theList.length)]; // makes double sized empty table
		for (int i = 0; i < theList.length; i++) {
			theList[i] = new LinkedList<Vertex>();
		}
		
		currentSize = 0;
		for (int j = 0; j < oldList.length; j++) { // copies the table
			for (Vertex item : oldList[j]) {
				insert(item);
			}
		}
	}

	private int myHash(String key) {
		int hashVal = 0;
		for (int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
			hashVal = hashVal % size;
			if (hashVal < 0) {
				hashVal = hashVal + size;
			}
		}
		
		return hashVal;
	}

	public boolean contain(Vertex x) {
		List<Vertex> whichList = theList[myHash(x.name)];
		for (int i = 0; i < whichList.size(); i++) { // find an item in the hash table
			if (whichList.get(i).name.equals(x.name)) {
				return true;
			}
		}
		
		return false;
	}

	public Vertex get(String name) {
		List<Vertex> whichList = theList[myHash(name)];
		for (int i = 0; i < whichList.size(); i++) {
			if (whichList.get(i).name.equals(name)) {
				return whichList.get(i);
			}
		}
		
		return null;
	}

}
