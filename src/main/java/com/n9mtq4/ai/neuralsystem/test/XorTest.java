package com.n9mtq4.ai.neuralsystem.test;

import com.n9mtq4.ai.neuralsystem.NeuralNetwork;

import java.util.ArrayList;

/**
 * Created by will on 10/26/15 at 9:48 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class XorTest {
	
	private static final double[] input1 = new double[] {0d, 0d};
	private static final double[] output1 = new double[] {0d};
	private static final double[] input2 = new double[] {0d, 1d};
	private static final double[] output2 = new double[] {1d};
	private static final double[] input3 = new double[] {1d, 0d};
	private static final double[] output3 = new double[] {1d};
	private static final double[] input4 = new double[] {1d, 1d};
	private static final double[] output4 = new double[] {0d};
	
	public static void main(String[] args) {
		
		NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{2, 4, 4, 1});
		
		for (int i = 0; i < 2000; i++) {
			
			neuralNetwork.feedForward(input1);
			neuralNetwork.backProp(output1);
			neuralNetwork.feedForward(input2);
			neuralNetwork.backProp(output2);
			neuralNetwork.feedForward(input3);
			neuralNetwork.backProp(output3);
			neuralNetwork.feedForward(input4);
			neuralNetwork.backProp(output4);
			
		}
		
		neuralNetwork.feedForward(input1);
		ArrayList<Double> output1f = new ArrayList<Double>();
		neuralNetwork.getResults(output1f);
		System.out.println(output1f.toString());
		
		neuralNetwork.feedForward(input2);
		ArrayList<Double> output2f = new ArrayList<Double>();
		neuralNetwork.getResults(output2f);
		System.out.println(output2f.toString());
		
		neuralNetwork.feedForward(input3);
		ArrayList<Double> output3f = new ArrayList<Double>();
		neuralNetwork.getResults(output3f);
		System.out.println(output3f.toString());
		
		neuralNetwork.feedForward(input4);
		ArrayList<Double> output4f = new ArrayList<Double>();
		neuralNetwork.getResults(output4f);
		System.out.println(output4f.toString());
		
	}
	
}
