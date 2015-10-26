package com.n9mtq4.ai.neuralsystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 10/26/15 at 9:46 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class Neuron implements Serializable {
	
	private static final long serialVersionUID = 6542725002195157941L;
	
	private static final double LEARNING_RATE = 0.15d; //TODO: update theses values
	private static final double MOMENTUM = 0.5d;
	
	private static double transferFunction(double x) {
//		tanh - output range -1d to 1d
		return Math.tanh(x);
	}
	
	private static double transferFunctionDerivative(double x) {
//		tanh derivative
		return 1d - x * x;
	}
	
	private double outputValue;
	private int index;
	private double gradient;
	private ArrayList<Connection> outputWeights;
	
	public Neuron(int numOutputs, int index) {
		this.index = index;
		this.outputWeights = new ArrayList<Connection>();
		for (int c = 0; c <= numOutputs; c++) {
			outputWeights.add(new Connection());
		}
	}
	
	/**
	 * Sums up all the previous layer's output and multiplies it by weight
	 * */
	public void feedForward(Layer previousLayer) {
		
		double sum = 0d;
		
//		includes the bias neuron
		for (int n = 0; n < previousLayer.size(); n++) {
			sum += previousLayer.get(n).getOutputValue() * previousLayer.get(n).getOutputWeights().get(getIndex()).getWeight();
		}
		
		setOutputValue(transferFunction(sum));
		
	}
	
	public void calcOutputGradients(double targetValue) {
		double delta = targetValue - getOutputValue();
		gradient = delta * transferFunctionDerivative(getOutputValue());
	}
	
	public void calcHiddenGradients(Layer nextLayer) {
		double dow = sumDOW(nextLayer);
		gradient = dow * transferFunctionDerivative(getOutputValue());
	}
	
	public void updateInputWeights(Layer previousLayer) {
		
//		the weights to be updated are int the connection container
//		in the neurons in the preceding layer
//		including bias
		for (int n = 0; n < previousLayer.size(); n++) {
			Neuron neuron = previousLayer.get(n);
			double oldDeltaWeight = neuron.getOutputWeights().get(getIndex()).getDeltaWeight();
			
			double newDeltaWeight = LEARNING_RATE * neuron.getOutputValue() * getGradient() + MOMENTUM * oldDeltaWeight;
			
			neuron.getOutputWeights().get(getIndex()).setDeltaWeight(newDeltaWeight);
			neuron.getOutputWeights().get(getIndex()).addWeight(newDeltaWeight);
			
		}
		
	}
	
	/**
	 * Sums the derivatives of the weights of the next layer
	 * */
	private double sumDOW(Layer nextLayer) {
		double sum = 0.0d;
		
//		sum our contributions of the errors at the nodes we feed
		for (int n = 0; n < nextLayer.size() - 1; n++) {
//			sum += getOutputWeights().get(n).getWeight() * nextLayer.getNeuron(n).getGradient();
			sum += outputWeights.get(n).getWeight() * nextLayer.get(n).getGradient();
		}
		
		return sum;
		
	}
	
	public final double getOutputValue() {
		return outputValue;
	}
	
	public void setOutputValue(double outputValue) {
		this.outputValue = outputValue;
	}
	
	public final ArrayList<Connection> getOutputWeights() {
		return outputWeights;
	}
	
	public void setOutputWeights(ArrayList<Connection> outputWeights) {
		this.outputWeights = outputWeights;
	}
	
	public int getIndex() {
		return index;
	}
	
	public final double getGradient() {
		return gradient;
	}
	
}
