package com.example.bubblebitoey.sw_specebook.presenter;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.*;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */

public class PassingActivity {
	public static void newActivity(Map<String, Serializable> map, Context from, Class to) {
		//Create intent
		Intent intent = new Intent(from, to);
		for (String key : map.keySet()) {
			intent.putExtra(key, map.get(key));
		}
		//Start details activity
		from.startActivity(intent);
	}
	
	public static void newActivity(Context from, Class to) {
		//Start details activity
		from.startActivity(new Intent(from, to));
	}
}
