package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
    
  public static void main(String[] args) {
	  	BinarySearchTree<Integer> tree = new BinarySearchTree<>();
	  	BSTVisualizer vis = new BSTVisualizer("tree", 500, 500);
	  	
	  	tree.add(2);
	  	tree.add(1);
	  	tree.add(7);
	  	tree.add(4);
	  	tree.add(6);
	  	tree.add(3);
	  	tree.add(8);
	  	
	 
	  	vis.drawTree(tree);
	  	tree.printTree();
	  	
	  	tree.rebuild();
	  	
	  	try {
	  		Thread.sleep(1000);
	  	}catch(InterruptedException e) {
	  		e.printStackTrace();
	  	}
	  	
	  	vis.drawTree(tree);
  }
  
  /**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		comparator = null; // att comparator sätts till null innebär att element sorteras "standard" eller i "naturlig ordning"
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return addRec(x, root);
	}
	
	private boolean addRec(E x, BinaryNode<E> node){
		
		BinaryNode<E> newNode = new BinaryNode<>(x);
		
		if(root == null) {
			root = newNode; // om trädet är tomt blir nya elementet root-noden
			size++;
			return true;
		}
		
		int compare = ((Comparable<E>) newNode.element).compareTo(node.element); //kollar ifall nya elementet är "större" eller "mindre" än noden som jämförs
		
		if(compare < 0) { //ifall nya noden är mindre 
			if(node.left == null) { //kollar ifall det finns en nod till vänster om noden som jämförs med
				node.left = newNode; //ifall det inte finns sätts nya noden till vänster
				size++;
				return true;
			}else {
				return addRec(x, node.left); //annars kör vi metoden igen fast med noden till vänster som den att jämföra med
			}
		}else if(compare > 0) { //ifall nya noden är större
			if(node.right == null) { // kollar ifall det finns en nod till höger om noden som jämförs med
				node.right = newNode; //ifall det inte finns placeras nya noden till höger
				size++;
				return true;
			}else {
				return addRec(x, node.right); //annars körs metoden igen med noden till höger som utgångspunkt för jämförelse
			}
		}
		
		return false; // här hamnar vi endast om x är lika med node.element, och då sätts inget in och metoden returnerar false
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return heightRec(root);
	}
	
	private int heightRec(BinaryNode<E> node) {
		if(node == null) { 
			return 0;
		}else {
			return 1 + Math.max(heightRec(node.left), heightRec(node.right)); // 
		}
		
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTreeRec(root);
	}
	
	private void printTreeRec(BinaryNode<E> node) {
		if(node != null) {
			printTreeRec(node.left); // inorder innebär att man börjar längst ner till vänster
			System.out.print(node.element + " ");
			printTreeRec(node.right); // därefter höger
		}
		
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList <E> sortedList = new ArrayList<>();
		toArray(root, sortedList);
		root = buildTree(sortedList, 0, sortedList.size()-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n != null) {
			toArray(n.left, sorted); //gör i princip samma sak som printTreeRec fast istället för att printa läggs elementet in i listan
			sorted.add(n.element); 
			toArray(n.right, sorted);
		}
	
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if(first>last) {
			return null;
		}
		
		int mid = (first + last)/2;
		
		BinaryNode<E> midNode = new BinaryNode<E>(sorted.get(mid));
		
		midNode.left = buildTree(sorted, first, mid-1);
		midNode.right = buildTree(sorted, mid+1, last);
		
		return midNode;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
