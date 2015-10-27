package com.n9mtq4.ai.neuralsystem.test;

import com.n9mtq4.ai.neuralsystem.NeuralNetwork;

import java.util.ArrayList;

/**
 * Created by will on 10/26/15 at 7:39 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class AddingNumbers {
	
	public static void main(String[] args) throws Exception {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork(2, 6, 2, 1);
		
		for (int i = 0; i < 10000; i++) {
			
			// trained with values from 0 to 5
			for (double x = 0; x < 5; x++) {
				for (double y = 0; y < 5; y++) {
					
					double ex = x / 10;
					double ey = y / 10;
					double expected = ex + ey;
					
					neuralNetwork.feedForward(ex, ey);
					
//					training
					neuralNetwork.backProp(expected);
					
//					printing
//					ArrayList<Double> results = neuralNetwork.getResults();
//					System.out.println("Got: " + results.get(0) + "\tExpected: " + expected);
					
				}
			}
			
		}
		
		// now lets try a number that it has never seen before, a 7
		double num1 = .2;
		double num2 = .6;
		neuralNetwork.feedForward(num1, num2);
		ArrayList<Double> results = neuralNetwork.getResults();
		
		System.out.println(num1 + num2);
		System.out.println(results.get(0));
		
	}
	
}
