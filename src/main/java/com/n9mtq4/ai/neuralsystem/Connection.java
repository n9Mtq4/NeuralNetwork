package com.n9mtq4.ai.neuralsystem;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by will on 10/26/15 at 9:55 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class Connection implements Serializable {
	
	private static final long serialVersionUID = 7087290521642022674L;
	private static final Random RANDOM = new Random();
	
	private static double getRandomWeight() {
		return RANDOM.nextDouble();
	}
	
//	0 to 1
	private double weight;
	private double deltaWeight;
	
	public Connection() {
		this(getRandomWeight());
	}
	
	public Connection(double weight) {
		this.weight = weight;
	}
	
	public void addWeight(double deltaWeight) {
		weight += deltaWeight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getDeltaWeight() {
		return deltaWeight;
	}
	
	public void setDeltaWeight(double deltaWeight) {
		this.deltaWeight = deltaWeight;
	}
	
}
