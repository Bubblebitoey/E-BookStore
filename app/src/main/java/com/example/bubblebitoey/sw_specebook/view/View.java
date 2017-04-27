package com.example.bubblebitoey.sw_specebook.view;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public interface View {
	void to(Map<String, Serializable> data, Class nextActivity);
}
