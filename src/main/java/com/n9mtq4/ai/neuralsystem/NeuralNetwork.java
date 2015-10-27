package com.n9mtq4.ai.neuralsystem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 10/26/15 at 9:47 AM.
 * Based off of https://www.youtube.com/watch?v=KkwX7FkLfug
 * 
 * @author Will "n9Mtq4" Bresnahan
 */
public class NeuralNetwork implements Serializable {
	
	private static final long serialVersionUID = 1854931447091544709L;
	
	private double error;
	private ArrayList<Layer> layers;
	
	public NeuralNetwork(int... topology) {
		this.layers = new ArrayList<Layer>();
		int numLayers = topology.length;
		for (int layerNum = 0; layerNum < numLayers; layerNum++) {
			Layer layer = new Layer();
			int numOutputs = layerNum == topology.length - 1 ? 0 : topology[layerNum + 1];
			
//			we have a new layer now, lets add some neurons to it
			for (int neuronNum = 0; neuronNum <= topology[layerNum]; neuronNum++) {
				layer.add(new Neuron(numOutputs, neuronNum));
			}
			
//			add the layer to the list
			layers.add(layer);
			
		}
	}
	
	public void feedForward(double... inputValues) {
		
//		assign the input values into the input neurons
		for (int i = 0; i < inputValues.length; i++) {
			layers.get(0).get(i).setOutputValue(inputValues[i]);
		}
		
//		forward propagation
		for (int layerNum = 1; layerNum < layers.size(); layerNum++) {
			Layer previousLayer = layers.get(layerNum - 1);
			for (int n = 0; n < layers.get(layerNum).size() - 1; n++) {
				layers.get(layerNum).get(n).feedForward(previousLayer);
			}
		}
		
	}
	
	public void backProp(double... targetValues) {
		
//		Calculate overall net error (RMS of output neuron errors) (Root Mean Square)
		error = 0d;
		Layer outputLayer = layers.get(layers.size() - 1);
		
		for (int n = 0; n < outputLayer.size() - 1; n++) {
			double delta = targetValues[n] - outputLayer.get(n).getOutputValue();
			error += delta * delta;
		}
		
		error /= outputLayer.size() - 1; // get average
		error = Math.sqrt(error);
		
//		Calculate output layer gradients
		for (int n = 0; n < outputLayer.size() - 1; n++) {
			outputLayer.get(n).calcOutputGradients(targetValues[n]);
		}
		
//		Calculates gradients on hidden layers
		for (int layerNum = layers.size() - 2; layerNum > 0; layerNum--) {
			Layer hiddenLayer = layers.get(layerNum);
			Layer nextLayer = layers.get(layerNum + 1);
			
			for (int n = 0; n < hiddenLayer.size(); n++) {
				hiddenLayer.get(n).calcHiddenGradients(nextLayer);
			}
			
		}
		
//		for all layers from outputs to first hidden layer, update connection weights
		for (int layerNum = layers.size() - 1; layerNum > 0; layerNum--) {
			Layer layer = layers.get(layerNum);
			Layer previousLayer = layers.get(layerNum - 1);
			
			for (int n = 0; n < layer.size(); n++) {
				layer.get(n).updateInputWeights(previousLayer);
			}
			
		}
		
	}
	
	public ArrayList<Double> getResults() {
		ArrayList<Double> results = new ArrayList<Double>();
		getResults(results);
		return results;
	}
	
	public void getResults(ArrayList<Double> resultValues) {
		
//		TODO: this is a very c++ way of doing things
		resultValues.clear();
		Layer resultLayer = layers.get(layers.size() - 1);
		for (int i = 0; i < resultLayer.size() - 1; i++) {
//			resultValues.add(resultLayer.getNeuron(i).getOutputValue());
			resultValues.add(resultLayer.get(i).getOutputValue());
		}
		
	}
	
}
