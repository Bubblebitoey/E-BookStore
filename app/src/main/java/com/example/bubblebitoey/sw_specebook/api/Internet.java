package com.example.bubblebitoey.sw_specebook.api;

import com.example.bubblebitoey.sw_specebook.constants.Constants;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Internet {
	public static JSONArray fetchData() {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(Constants.link.openStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = read.readLine()) != null) {
				sb.append(line).append("\n");
			}
			return new JSONArray(sb.toString());
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
