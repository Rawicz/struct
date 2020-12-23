package struct;

import java.util.HashMap;

/**
 * The HashSet each element of which is associated with both previous and next elements.
 * You can always access the first element, the last element, and the neigbour elements of any specific element.
 */
public class IndexSet<T> extends HashMap<T, Duet<T>> {
	private T first;
	private T last;

	public IndexSet() {
		super(16, 0.75f);
	}

	public IndexSet(int initialCapacity) {
		super(initialCapacity, 0.75f);
	}

	public IndexSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public T getFirst() {
		return first;
	}

	public T getLast() {
		return last;
	}

	public T getPrev(T e) {
		if (!this.containsKey(e))
			throw new RuntimeException("No such element!");
		return this.get(e).getFirst();
	}

	public T getNext(T e) {
		if (!this.containsKey(e))
			throw new RuntimeException("No such element!");
		return this.get(e).getLast();
	}

	/**
	 * Adds the specified element at the top. 
	 */
	public void add(T e) {
		if(!this.isEmpty()) {
			super.put(e, new Duet<T>(last, null));
			this.get(last).setLast(e);
			last = e;
		} else {
			super.put(e, new Duet<T>(null, null));
			first = e;
			last = e;
		}
	}

	/**
	 * Adds the specified element at the top of another element in the set.
	 * If there is no element specified as a seat, adds new element at the top.
	 * @param e is an element to add
	 * @param seat is an element atop of which the new element will be added
	 */
	public void add(T e, T seat) {
		if (this.containsKey(seat) && this.get(seat).getLast() != null) {
			T next = this.get(seat).getLast();
			T prev = this.get(next).getFirst();
			this.get(prev).setLast(e);
			this.get(next).setFirst(e);
			super.put(e, new Duet<T>(prev, next));
		}
		else this.add(e);
	}

	/**
	 * Replaces the contained element with new one.
	 * If there is no element to replace, adds the new element at the top.
	 * @param e new element
	 * @param seat element to be replaced
	 */
	public void set(T e, T seat) {
		if (this.containsKey(seat)) {
			super.put(e, this.get(seat));
			if (this.get(e).getFirst() != null)
					this.get(this.get(e).getFirst()).setLast(e);
			if (this.get(e).getLast() != null)
					this.get(this.get(e).getLast()).setFirst(e);
			super.remove(seat);
		}
		else this.add(e);
	}

	/**
	 * Attaches the specified element at the beginning of the set.
	 * @param e element to be attached
	 */
	public void attach(T e) {
		if (!this.isEmpty()) {
			super.put(e, new Duet<T>(null, first));
			this.get(first).setFirst(e);
			first = e;
		}
		else this.add(e);
	}

	public boolean contains(T e) {
		if (this.containsKey(e)) return true;
		else return false;
	}

	@Override
	public Duet<T> remove(Object e) {
		T prev = this.get(e).getFirst();
		T next = this.get(e).getLast();
		if (e != first) 
			this.get(prev).setLast(next);
		else first = next;
		if (e != last)
			this.get(next).setFirst(prev);
		else last = prev;
		return super.remove(e);
	}

	/**
	 * @deprecated
	 * It's comepletely pointless to use this method for this class.
	 * Use add(), set(), attach() methods instead.
	 * add() : adds the instance atop
	 * attach() : adds the instance below
	 * set() : replaces the specified instance
	 */
	@Deprecated
	public Duet<T> put(T e, Duet<T> d) {
		this.add(e);
		return d;
	}
}
