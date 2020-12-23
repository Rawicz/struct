package struct;

import java.util.ListIterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Class that wraps both LinkedList and its iterator which remembers it's position.
 * Has all methods of ListIterator interface.
 * Has some methods of List interface which does not conflict with the iterator behaviour.
 */
public class TameList<E> implements ListIterator<E> {
	private LinkedList<E> list;
	private ListIterator<E> iterator;

	public TameList() {
		this.list = new LinkedList<E>();
		this.iterator = this.list.listIterator();
	}

	/**
	 * Puts the iterator to first position.
	 * @return this instance
	 */
	public TameList<E> renew() {
		this.iterator = this.list.listIterator();
		return this;
	}

	/**
	 * Puts iterator at the specified index.
	 * @param index
	 * @return this instance
	 */
	public TameList<E> renew(int index) {
		this.iterator = this.list.listIterator(index);
		return this;
	}

	/**
	 * @return next element of the LinkedList without changing the iterator position
	 */
	public E getNext() {
		this.iterator.next();
		return this.iterator.previous();
	}

	/**
	 * @return previous element of the LinkedList without changing the iterator position
	 */
	public E getPrev() {
		this.iterator.previous();
		return this.iterator.next();
	}

/// Methods of List<E> interface:
	public int size() {
		return this.list.size();
	}

	public E getFirst() {
		return this.list.getFirst();
	}

	public E getLast() {
		return this.list.getLast();
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public String toString() {
		return this.list.toString();
	}

/// Methods if ListIterator<E> interface:
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public E next() {
		return this.iterator.next();
	}

	public boolean hasPrevious() {
		return this.iterator.hasPrevious();
	}

	public E previous() {
		return this.iterator.previous();
	}

	public int nextIndex() {
		return this.iterator.nextIndex();
	}

	public int previousIndex() {
		return this.iterator.previousIndex();
	}

	public void remove() {
		this.iterator.remove();
	}

	public void set(E e) {
		this.iterator.set(e);
	}

	public void add(E e) {
		this.iterator.add(e);
	}
}
