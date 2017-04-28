package com.example.bubblebitoey.sw_specebook.model.raw;

import com.example.bubblebitoey.sw_specebook.api.Operation;

/**
 * Created by kamontat on 4/28/2017 AD.
 */

public interface Filterable<T> {
	T filter(Operation.Type type, String s);
}
