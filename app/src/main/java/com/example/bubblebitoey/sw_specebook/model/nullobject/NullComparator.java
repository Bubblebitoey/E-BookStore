package com.example.bubblebitoey.sw_specebook.model.nullobject;

import java.util.Comparator;

/**
 * Created by kamontat on 4/28/2017 AD.
 */

public class NullComparator<T> implements Comparator<T> {
	@Override
	public int compare(T t, T t1) {
		return 0;
	}
}
