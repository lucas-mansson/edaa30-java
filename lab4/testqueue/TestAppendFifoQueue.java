package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import queue_singlelinkedlist.FifoQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	void testTwoEmptyQueues() {
		assertTrue(q1.isEmpty());
		assertTrue(q2.isEmpty());
		
		q1.append(q2);
		
		assertTrue(q1.isEmpty());
		assertTrue(q2.isEmpty());
	}
	
	@Test
	void testEmptyToNonEmpty() {
		q2.offer(1);
		q2.offer(2);
		
		q1.append(q2);
		
		assertEquals(q1.size(), 2);
		assertEquals(q2.size(), 0);
		
		assertEquals(1, q1.poll());
		assertEquals(2, q1.poll());
		
		assertTrue(q2.isEmpty());	
	}
	
	@Test
	void testNonEmptyToEmpty() {
		q1.offer(1);
		q1.offer(2);
		
		q1.append(q2);
		
		assertEquals(q1.size(), 2);
		
		assertEquals(1, q1.poll());
		assertEquals(2, q1.poll());
		
		assertTrue(q2.isEmpty());	
	}
	
	@Test
	void testTwoNonEmpty(){
		q1.offer(1);
		q2.offer(2);
		
		q1.append(q2);
		
		assertEquals(q1.size(), 2);
		assertEquals(q1.poll(), 1);
		assertEquals(q1.poll(), 2);
		assertTrue(q2.isEmpty());
	}
	
	@Test
	void testTwoEqualQueues() {
		
		q1.offer(1);
		
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
		
	}

}
