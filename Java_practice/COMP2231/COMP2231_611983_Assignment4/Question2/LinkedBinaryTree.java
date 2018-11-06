/*
 * @CreateTime: Feb 27, 2018 1:12 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 27, 2018 1:15 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 4 Question 1
 * @Description: LinkedBinaryTree implements the BinaryTreeADT interface
 * 				 changes where only made to the size, getRight, and getLeft methods.
 */
import java.util.ArrayList;
import java.lang.Integer;
import java.util.*;
/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 * 
 * @author Java Foundations
 * @version 4.0
 */
public class LinkedBinaryTree<Integer> implements Iterable<Integer>
{
	protected BinaryTreeNode<Integer> root; 
	protected int modCount;

	/**
	 * Creates an empty binary tree.
	 */
	public LinkedBinaryTree() 
	{
		root = null;
	}

	/**
	 * Creates a binary tree with the specified element as its root.
	 *
	 * @param element the element that will become the root of the binary tree
	 */
	public LinkedBinaryTree(Integer element) 
	{
		root = new BinaryTreeNode(element);
	}

	/**
	 * Creates a binary tree with the specified element as its root and the 
	 * given trees as its left child and right child
	 *
	 * @param element the element that will become the root of the binary tree
	 * @param left the left subtree of this tree
	 * @param right the right subtree of this tree
	 */
	public LinkedBinaryTree(Integer element, LinkedBinaryTree<Integer> left, 
			LinkedBinaryTree<Integer> right) 
	{
		root = new BinaryTreeNode(element);
		root.setLeft(left.root);
		root.setRight(right.root);
	}

	/**
	 * Returns a reference to the element at the root
	 *
	 * @return a reference to the specified target
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public Integer getRootElement() throws EmptyCollectionException
	{
		// To be completed as a Programming Project
		
		 if (root == null)
            throw new EmptyCollectionException("LinkedBinaryTree");
        
        return (root.getElement());
	}

	/**
	 * Returns a reference to the node at the root
	 *
	 * @return a reference to the specified node
	 * @throws EmptyCollectionException if the tree is empty
	 */
	protected BinaryTreeNode<Integer> getRootNode() throws EmptyCollectionException
	{
		if (root == null)
            throw new EmptyCollectionException("LinkedBinaryTree");
		
		return root;  // temp
	}

	/**
	 * Returns the left subtree of the root of this tree.
	 *
	 * @return a link to the left subtree fo the tree
	 */
	public LinkedBinaryTree<Integer> getLeft()
	{
		LinkedBinaryTree<Integer> Left = new LinkedBinaryTree<Integer>();
		//create a binary tree with the left node as its root
        Left.root = root.getRight();

		return Left;
	}

	/**
	 * Returns the right subtree of the root of this tree.
	 *
	 * @return a link to the right subtree of the tree
	 */
	public LinkedBinaryTree<Integer> getRight()
	{
		LinkedBinaryTree<Integer> Right = new LinkedBinaryTree<Integer>();
		//create a binary tree with the right node as its root
        Right.root = root.getRight();

		return Right;
	}

	/**
	 * Returns true if this binary tree is empty and false otherwise.
	 *
	 * @return true if this binary tree is empty, false otherwise
	 */
	public boolean isEmpty() 
	{
		return (root == null);
	}

	/**
	 * Returns the integer size of this tree.
	 *
	 * @return the integer size of the tree
	 */
	public int size() 
	{
		return root.numChildren();
	}

	/**
	 * Returns the height of this tree.
	 *
	 * @return the height of the tree
	 */
	public int getHeight()
	{
		return 0;  // temp
	}

	/**
	 * Returns the height of the specified node.
	 *
	 * @param node the node from which to calculate the height
	 * @return the height of the tree
	 */
	private int height(BinaryTreeNode<Integer> node) 
	{
		// To be completed as a Programming Project
		
		return 0;  // temp
	}

	/**
	 * Returns true if this tree contains an element that matches the
	 * specified target element and false otherwise.
	 *
	 * @param targetElement the element being sought in this tree
	 * @return true if the element in is this tree, false otherwise
	 */
	public boolean contains(Integer targetElement) 
	{
		// To be completed as a Programming Project
		
		return true;  // temp
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in this binary tree.  Throws a ElementNotFoundException if
	 * the specified target element is not found in the binary tree.
	 *
	 * @param targetElement the element being sought in this tree
	 * @return a reference to the specified target
	 * @throws ElementNotFoundException if the element is not in the tree
	 */
	public Integer find(Integer targetElement) throws ElementNotFoundException
	{
		BinaryTreeNode<Integer> current = findNode(targetElement, root);

		if (current == null)
			throw new ElementNotFoundException("LinkedBinaryTree");

		return (current.getElement());
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in this binary tree.
	 *
	 * @param targetElement the element being sought in this tree
	 * @param next the element to begin searching from
	 */
	private BinaryTreeNode<Integer> findNode(Integer targetElement, 
			BinaryTreeNode<Integer> next)
	{
		if (next == null)
			return null;

		if (next.getElement().equals(targetElement))
			return next;

		BinaryTreeNode<Integer> temp = findNode(targetElement, next.getLeft());

		if (temp == null)
			temp = findNode(targetElement, next.getRight());

		return temp;
	}

	/**
	 * Returns a string representation of this binary tree showing
	 * the nodes in an inorder fashion.
	 *
	 * @return a string representation of this binary tree
	 */
	public String toString() 
	{
		Iterator<Integer> iter = this.iteratorInOrder();
		String str = new String();
        while (iter.hasNext())
        {
			Integer temp = iter.next();
            str = str + temp;
        }
		
		return str;  // temp
	}

	/**
	 * Returns an iterator over the elements in this tree using the 
	 * iteratorInOrder method
	 *
	 * @return an in order iterator over this binary tree
	 */
	public Iterator<Integer> iterator()
	{
		return iteratorInOrder();
	}

	/**
	 * Performs an inorder traversal on this binary tree by calling an
	 * overloaded, recursive inorder method that starts with
	 * the root.
	 *
	 * @return an in order iterator over this binary tree
	 */
	public Iterator<Integer> iteratorInOrder()
	{
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		inOrder(root, tempList);

		return new TreeIterator(tempList.iterator());
	}

	/**
	 * Performs a recursive inorder traversal.
	 *
	 * @param node the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void inOrder(BinaryTreeNode<Integer> node, ArrayList<Integer> tempList) 
	{
		if (node != null)
		{
			inOrder(node.getLeft(), tempList);
			tempList.add( node.getElement());
			inOrder(node.getRight(), tempList);
		}
	}

	/**
	 * Performs an preorder traversal on this binary tree by calling 
	 * an overloaded, recursive preorder method that starts with
	 * the root.
	 *
	 * @return a pre order iterator over this tree
	 */
	public Iterator<Integer> iteratorPreOrder() 
	{
		// To be completed as a Programming Project
		
		return null;  // temp
	}

	/**
	 * Performs a recursive preorder traversal.
	 *
	 * @param node the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void preOrder(BinaryTreeNode<Integer> node, 
			ArrayList<Integer> tempList) 
	{
		// To be completed as a Programming Project
	}

	/**
	 * Performs an postorder traversal on this binary tree by calling
	 * an overloaded, recursive postorder method that starts
	 * with the root.
	 *
	 * @return a post order iterator over this tree
	 */
	public Iterator<Integer> iteratorPostOrder() 
	{
		// To be completed as a Programming Project
		
		return null;  // temp
	}

	/**
	 * Performs a recursive postorder traversal.
	 *
	 * @param node the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void postOrder(BinaryTreeNode<Integer> node, 
			ArrayList<Integer> tempList) 
	{
		// To be completed as a Programming Project
	}

	/**
	 * Performs a levelorder traversal on this binary tree, using a
	 * templist.
	 *
	 * @return a levelorder iterator over this binary tree
	 */
	public Iterator<Integer> iteratorLevelOrder() 
	{
		ArrayList<BinaryTreeNode<Integer>> nodes = 
				new ArrayList<BinaryTreeNode<Integer>>();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		BinaryTreeNode<Integer> current;
		nodes.add( root);

		while (!nodes.isEmpty()) 
		{
			current = nodes.remove(0);

			if (current != null)
			{
				
				tempList.add( current.getElement());
				if (current.getLeft() != null)
					nodes.add( current.getLeft());
				if (current.getRight() != null)
					nodes.add(current.getRight());
			}
			else
				tempList.add(null);
		}

		return new TreeIterator(tempList.iterator());
	}

	/**
	 * Inner class to represent an iterator over the elements of this tree
	 */
	private class TreeIterator implements Iterator<Integer>
	{
		private int expectedModCount;
		private Iterator<Integer> iter;

		/**
		 * Sets up this iterator using the specified iterator.
		 *
		 * @param iter the list iterator created by a tree traversal
		 */
		public TreeIterator(Iterator<Integer> iter)
		{
			this.iter = iter;
			expectedModCount = modCount;
		}

		/**
		 * Returns true if this iterator has at least one more element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasNext() throws ConcurrentModificationException
		{
			if (!(modCount == expectedModCount))
				throw new ConcurrentModificationException();

			return (iter.hasNext());
		}

		/**
		 * Returns the next element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iterator is empty
		 */
		public Integer next() throws NoSuchElementException
		{
			if (hasNext())
				return (iter.next());
			else 
				throw new NoSuchElementException();
		}

		/**
		 * The remove operation is not supported.
		 * 
		 * @throws UnsupportedOperationException if the remove operation is called
		 */
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

