package com.n9mtq4.ai.neuralsystem.utils;

import com.n9mtq4.ai.neuralsystem.NeuralNetwork;

import java.io.File;

/**
 * Created by will on 10/26/15 at 7:22 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class NeuralNetworkUtils {
	
	public static void save(NeuralNetwork neuralNetwork, File file) throws Exception {
		ObjectUtils.writeSerializable(neuralNetwork, file);
	}
	
	public static NeuralNetwork load(File file) throws Exception {
		return ObjectUtils.readSerializable(file);
	}
	
}
