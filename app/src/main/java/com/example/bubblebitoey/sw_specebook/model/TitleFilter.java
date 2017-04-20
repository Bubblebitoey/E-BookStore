package com.example.bubblebitoey.sw_specebook.model;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class TitleFilter extends Filter {
	private ArrayAdapter<Book> bookArray;

	public TitleFilter(ArrayAdapter<Book> bookArrayAdapter) {
		this.bookArray = bookArrayAdapter;
	}

	@Override
	protected FilterResults performFiltering(CharSequence constraint) {
		FilterResults filterResult = new FilterResults();
		List<Book> result = new ArrayList<>();

		for (int i = 0; i < bookArray.getCount(); i++) {
			Book b = bookArray.getItem(i);
			if (b.getTitle().contains(constraint)) {
				result.add(b);
			}
		}

		filterResult.values = result;
		filterResult.count = result.size();

		return filterResult;
	}

	@Override
	protected void publishResults(CharSequence constraint, FilterResults results) {
		bookArray.clear();
		bookArray.addAll((ArrayList<Book>) results.values);
		if (results.count > 0) {
			bookArray.notifyDataSetChanged();
		} else {
			bookArray.notifyDataSetInvalidated();
		}
	}
}
