import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A class implementing a Union-Find-data structure with representatives.
 * 
 * @author AlgoDat-Team
 */
public class UnionFindSet<T>{

	//You can use this map to store the representant for each element of the Union find structure
    private HashMap<T,T> element2representative;

	public UnionFindSet() {
		element2representative = new HashMap<>();
	}

	/**
	 * Takes a collection of n elements and adds 
	 * n disjoint partitions each with one element in it.
	 * 
	 * @param set
	 *            The set to be partitioned.
	 */
	public void add(List<T> elements) {
		// TODO Homework 2.1
		for (T element : elements) {
			if (element2representative.containsKey(element)) {
				throw new RuntimeException("element is already added");
			}
			this.element2representative.put(element, element);
		}
	}

	/**
	 * Creates one disjoint partition with the element in it 
	 * and adds it to the Union-Find data structure
	 * 
	 * @param element
	 *            The element to put in the partition.
	 */
	public void add(T element) {
		// TODO Homework 2.1
		if (element2representative.containsKey(element)) {
			throw new RuntimeException("element is already added");
		} else if (element == null) {
			throw new RuntimeException("element equals null");
		}
		
		this.element2representative.put(element, element);
	}

	/**
	 * Retrieves the partition which contains the wanted element.
	 * 
	 * A partition is identified by a single, representative element.
	 * This function returns the representative of the partition
	 * that contains x. 
	 * 
	 * @param x
	 *            The element whose partition we want to know
	 * @return 
	 *            The representative element of the partition
	 */
	public T getRepresentative(T x) {
		// TODO Homework 2.1
		return this.element2representative.get(x);
	}

	/**
	 * Joins two partitions. First it retrieves the partitions containing the
	 * given elements. Removes one of the joined partitions from
	 * <code>partitions</code>.
	 * 
	 * @param x
	 *            One element whose partition is to be joined.
	 * @param y
	 *            The other element whose partition is to be joined.
	 */
	public void union(T x, T y) {
		// TODO Homework 2.1
		
		if (!(this.element2representative.containsKey(x)) || !(this.element2representative.containsKey(y))) {
			throw new RuntimeException("elements are not in hashMap");
		} else if (x == null || y == null) {
			throw new RuntimeException("x or y equals null");
		}
		
		T repOfX = getRepresentative(x);
		T repOfY = getRepresentative(y);
		
		// iterate through keysSet of hashMap
		// if the rep of element equals rep of y than change rep of element to rep of x
		for (T element : this.element2representative.keySet()) {
			if (this.element2representative.get(element).equals(repOfY)) {
				this.element2representative.put(element, repOfX);
			}
		}
		
	}
}
