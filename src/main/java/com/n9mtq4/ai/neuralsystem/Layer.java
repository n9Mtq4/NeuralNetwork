package com.n9mtq4.ai.neuralsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by will on 10/26/15 at 5:19 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class Layer extends ArrayList<Neuron> implements Serializable {
	
	private static final long serialVersionUID = 4171169620876214764L;
	
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
