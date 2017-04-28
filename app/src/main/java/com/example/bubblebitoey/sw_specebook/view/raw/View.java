package com.example.bubblebitoey.sw_specebook.view.raw;

import com.example.bubblebitoey.sw_specebook.model.raw.User;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public interface View {
	
	void login(User u);
	
	void logout();
	
	void to(Class nextActivity);
	
	void to(Map<String, Serializable> data, Class nextActivity);
	
	void toAndWait(int code, Class nextActivity);
	
	void toAndWait(int code, Map<String, Serializable> data, Class nextActivity);
}
