package com.example.bubblebitoey.sw_specebook.view.raw;

import android.content.Context;
import android.os.Parcelable;

import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */
public interface View {
	
	void to(Class nextActivity);
	
	void to(Map<String, Parcelable> data, Class nextActivity);
	
	void toAndWait(int code, Class nextActivity);
	
	void toAndWait(int code, Map<String, Parcelable> data, Class nextActivity);
	
	Context getContext();
}
