package com.n9mtq4.ai.neuralsystem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by will on 10/26/15 at 5:19 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class Layer extends ArrayList<Neuron> {
	
	public Layer(int initialCapacity) {
		super(initialCapacity);
	}
	
	public Layer() {
	}
	
	public Layer(Collection<? extends Neuron> c) {
		super(c);
	}
	
	public Neuron back() {
		return get(size() - 1);
	}
	
}
