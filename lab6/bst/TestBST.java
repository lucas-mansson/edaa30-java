package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBST {
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHeight() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		assertEquals(tree.height(), 0);
		
		tree.add(2);
		tree.add(1);
		tree.add(3);
		
		assertEquals(tree.height(), 2);
		
		tree.add(4);
		
		assertEquals(tree.height(), 3);
		
		//testa andra konstruktor
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>((e1, e2) -> e2.compareTo(e1));
		
		assertEquals(tree2.height(), 0);
		
		tree2.add(2);
		tree2.add(1);
		tree2.add(3);
		
		assertEquals(tree2.height(), 2);
		
		tree2.add(4);
		
		assertEquals(tree2.height(), 3);
	}
	
	@Test
	void testAdd() {
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		
		assertTrue(tree.add("A"));
		
		assertFalse(tree.add("A"));
		
		//testa andra konstruktor
		BinarySearchTree<String> tree2 = new BinarySearchTree<>((e1, e2) -> e2.compareTo(e1));
		
		assertTrue(tree2.add("A"));
		
		assertFalse(tree2.add("A"));
	}
	
	@Test
	void testSize() {
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		assertEquals(tree.size(), 0);
		tree.add("B");
		tree.add("A");
		tree.add("C");
		
		assertEquals(tree.size(), 3);
		
		
		//testa andra konstruktor
		BinarySearchTree<String> tree2 = new BinarySearchTree<>((e1, e2) -> e2.compareTo(e1));
		assertEquals(tree2.size(), 0);
		tree2.add("B");
		tree2.add("A");
		tree2.add("C");
		
		assertEquals(tree2.size(), 3);
	}
	
	@Test
	void testClear() {
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		tree.add("A");
		assertEquals(tree.size(), 1);
		tree.clear();
		assertEquals(tree.size(), 0);
		
		//testa andra konstruktor
		BinarySearchTree<String> tree2 = new BinarySearchTree<>((e1, e2) -> e2.compareTo(e1));
		tree2.add("L");
		assertEquals(tree2.size(), 1);
		tree2.clear();
		assertEquals(tree2.size(), 0);
		
	}
	

}
