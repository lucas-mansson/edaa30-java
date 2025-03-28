package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		
		QueueNode<E> newNode = new QueueNode<E>(e);
		
		if(size == 0) {
			last = newNode;
			last.next = newNode;
			size++;
			return true;
		}else {
			newNode.next = last.next;
			last.next = newNode;
			last = newNode;
			size++;
			return true;
		}
			
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size==0) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
	 
		if(last == null) {
			return null;
		}
		
		E element = last.next.element;
		
		last.next = last.next.next;
		
		size--;
		return element;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this == q) {
			throw new IllegalArgumentException("Kan inte slå samman samma kö");
		}
		
		if(q.last == null) { // om q är tom behövs inget göras
			return;
		}
		
		
		if(this.last == null) {
			this.last = q.last; // ifall this är tom gör vi bara q till den nya kön	
			
		}else {
			QueueNode <E> temp = this.last.next; // sparar första noden i this i en temporär nod
			this.last.next = q.last.next; //pekaren som pekar på första elementet i this ändras så att den pekar på första
			q.last.next = temp; // gör så att sista elementet i q ska peka på den första noden i this så att vi sluter cirkeln
			this.last = q.last; // gör så att last blir det sista elementet i q istället för sista elementet i this
		}
		
		this.size = this.size + q.size();
		
		q.last = null;
		q.size = 0;
		
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		/* Konstruktor */
		private QueueIterator() {
			if(last != null) {
				pos = last.next;
			}else {
				pos = null;
			}
		}
		
		public boolean hasNext() {
			
			return pos != null && (pos != last);
			
		}
		
		public E next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			E element = pos.element;
			
			if(pos.next != last.next) {
				pos = pos.next;
			}else {
				pos = null;
			}
			
			return element;
		}
		
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
