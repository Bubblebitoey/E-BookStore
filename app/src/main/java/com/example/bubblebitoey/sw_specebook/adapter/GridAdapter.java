package com.example.bubblebitoey.sw_specebook.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;

import java.io.IOException;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class GridAdapter extends ArrayAdapter<Book> {
	private final Filter FILTER_BY_TITLE = new TitleFilter();
	private final Filter FILTER_BY_YEAR = new YearFilter();
	
	private static final String TAG = "Adapter";
	private Books booksOriginal;
	private Books booksCurrent;
	private Context context;
	private int layoutResourceId;
	private Filter f;
	
	public GridAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		booksOriginal = new Books();
		booksCurrent = new Books();
	}
	
	@Override
	public synchronized void add(Book object) {
		super.add(object);
		booksCurrent.add(object);
		booksOriginal.add(object);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.title = (TextView) row.findViewById(R.id.text);
			holder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		
		Book item = booksCurrent.getBook(position);
		holder.title.setText(String.format(Locale.ENGLISH, "%s: %s (%s)\nprice: %.2f$", item.getId(), item.getTitle(), item.getYear(), item.getPrice()));
		try {
			holder.image.setImageBitmap(item.getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public void setFilter(Store.OperationType type) {
		switch (type) {
			case TITLE:
				this.f = FILTER_BY_TITLE;
				break;
			case YEAR:
				this.f = FILTER_BY_YEAR;
				break;
		}
	}
	
	@Override
	public void sort(@NonNull Comparator<? super Book> comparator) {
		super.sort(comparator);
		booksOriginal.sort(comparator);
		booksCurrent.sort(comparator);
	}
	
	@NonNull
	@Override
	public Filter getFilter() {
		return f;
	}
	
	private static class ViewHolder {
		TextView title;
		ImageView image;
	}
	
	private class YearFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResult = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				synchronized (this) {
					filterResult.values = booksOriginal;
					filterResult.count = booksOriginal.size();
				}
			} else {
				Books result = booksOriginal.filter(Store.OperationType.YEAR, String.valueOf(constraint));
				filterResult.values = result;
				filterResult.count = result.size();
			}
			return filterResult;
		}
		
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			booksCurrent = (Books) results.values;
			clear();
			synchronized (this) {
				addAll(booksCurrent.getBooks());
			}
		}
	}
	
	private class TitleFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResult = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				synchronized (this) {
					filterResult.values = booksOriginal;
					filterResult.count = booksOriginal.size();
				}
			} else {
				Books result = booksOriginal.filter(Store.OperationType.TITLE, String.valueOf(constraint));
				filterResult.values = result;
				filterResult.count = result.size();
			}
			return filterResult;
		}
		
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			booksCurrent = (Books) results.values;
			clear();
			synchronized (this) {
				addAll(booksCurrent.getBooks());
			}
		}
	}
}
