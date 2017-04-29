package com.example.bubblebitoey.sw_specebook.api.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.util.*;

/**
 * Created by bubblebitoey on 4/27/2017 AD.
 */
public class PassingActivity {
	public static void newActivity(Map<String, Parcelable> map, Context from, Class to) {
		//Create intent
		Intent intent = makeIntent(map).setClass(from, to);
		//Start details activity
		from.startActivity(intent);
	}
	
	public static void newActivity(Context from, Class to) {
		//Start details activity
		from.startActivity(new Intent(from, to));
	}
	
	public static void newActivityWithResult(int code, Map<String, Parcelable> map, Activity from, Class to) {
		//Create intent
		Intent intent = makeIntent(map).setClass(from, to);
		//Start details activity
		from.startActivityForResult(intent, code);
	}
	
	public static void newActivityWithResult(int code, Activity from, Class to) {
		from.startActivityForResult(new Intent(from, to), code);
	}
	
	private static Intent makeIntent(Map<String, Parcelable> map) {
		Intent intent = new Intent();
		for (String key : map.keySet()) {
			intent.putExtra(key, map.get(key));
		}
		return intent;
	}
}
