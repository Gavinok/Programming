/*
 * @CreateTime: Feb 27, 2018 1:12 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Aug 6, 2018 1:15 PM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 4 Question 2
 * @Description: LinkedBinaryTree implements the LinkedBinaryTree and provides a way to balance it using Recursion 
 */
import java.util.Iterator;
import java.util.*;
import java.util.ArrayList;
class BrutefoceBST
{
    //min and max are the INCLUSIVE bounds of the sublist, from which the tree should be built
    static BinaryTreeNode<Integer> balance(ArrayList<Integer> elements, int min , int max){
        /*
        this is the end for this portion of the recusion
        */
        if(min > max)
        {
            return null;
        }
        /*
        since the midle should be the root we will isolate it
        */
        int middle = (min + max) / 2;
        /*start reconstructing the tree then 
        split the arraylist in half and use recursion for each side*/
        BinaryTreeNode<Integer> result = new BinaryTreeNode<>(elements.get((middle)));
        result.setLeft(balance(elements, min , middle - 1 ));
        result.setRight(balance(elements, middle + 1 , max));

    return result;
    }

	public static void main(String[] args)
    {   
        //construct tree
        LinkedBinaryTree<Integer>tree1 =  new LinkedBinaryTree<Integer>(new Integer(3), new LinkedBinaryTree<Integer>(new Integer(2)), new LinkedBinaryTree<Integer>(new Integer(1)));
        LinkedBinaryTree<Integer>tree2 =new LinkedBinaryTree<Integer>(new Integer(4), tree1, new LinkedBinaryTree<Integer>(new Integer(10)));
        LinkedBinaryTree<Integer>tree3 = new LinkedBinaryTree<Integer>(new Integer(5), tree2, new LinkedBinaryTree<Integer>(new Integer(11)));
        LinkedBinaryTree<Integer>tree4 = new LinkedBinaryTree<Integer>(new Integer(6), tree3, null);
        LinkedBinaryTree tree = new LinkedBinaryTree(tree4);
        //convert the tree into an arrayList
        ArrayList<Integer> elements = ConvertTreeToArrayList(tree);

        //rebuild the tree so that it is balanced
        BinaryTreeNode<Integer> root = balance(elements, 0 , elements.size() - 1 );
        tree = new LinkedBinaryTree(root);
        //print the tree
        System.out.println(tree.toString());
	} 
    //converts the tree to an arraylist
    static ArrayList<Integer> ConvertTreeToArrayList(LinkedBinaryTree<Integer> tree)
    {
        Iterator<Integer> iter = tree.iteratorInOrder();
        ArrayList<Integer> list = new ArrayList<Integer>();

        while (iter.hasNext())
        {
            list.add(iter.next());
        }
        return list;
    }
}
