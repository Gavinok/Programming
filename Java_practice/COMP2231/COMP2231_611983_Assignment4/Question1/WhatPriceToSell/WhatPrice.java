/*
 * @CreateTime: Feb 27, 2018 11:28 AM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Feb 27, 2018 11:29 AM
 * @Student Number:T00611983
 * @COMP 2231 Assignment 4 Question 1
 * @Description: WhatPrice is a decision tree that is meant to determine 
 				 what price a new product should be sold for.
 */

import java.io.*;

/**
 * BackPainAnaylyzer demonstrates the use of a binary decision tree to 
 * diagnose back pain.
 */
public class WhatPrice
{
	/**
	 *  Asks questions of the user to diagnose a medical problem.
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Lets see how much we should sell this new product for");

		DecisionTree expert = new DecisionTree("WhattoSellFor.txt");
		expert.evaluate();
	}
}