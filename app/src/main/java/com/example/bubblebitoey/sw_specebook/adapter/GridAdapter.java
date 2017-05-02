package com.example.bubblebitoey.sw_specebook.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.Filterable;

import java.io.IOException;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */
public class GridAdapter extends ArrayAdapter<Book> implements Filterable<Void> {
	private final Filter FILTER_BY_ID = new IFilter(Operation.Type.ID);
	private final Filter FILTER_BY_TITLE = new IFilter(Operation.Type.Title);
	private final Filter FILTER_BY_YEAR = new IFilter(Operation.Type.Year);
	private final Filter FILTER_BY_PRICE = new IFilter(Operation.Type.Price);
	
	// private static final String TAG = "Adapter";
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
		
		// somehow this maybe add duplicate book, so my books add new book have to check contains
		booksOriginal.addNewBook(object);
		booksCurrent.addNewBook(object);
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
	
	public void setFilter(Operation.Type type) {
		switch (type) {
			case ID:
				this.f = FILTER_BY_ID;
				break;
			case Title:
				this.f = FILTER_BY_TITLE;
				break;
			case Year:
				this.f = FILTER_BY_YEAR;
				break;
			case Price:
				this.f = FILTER_BY_PRICE;
				break;
			default:
				this.f = FILTER_BY_TITLE;
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
	
	@Override
	public synchronized Void filter(Operation.Type type, String s) {
		setFilter(type);
		Log.d("Filter Type", IFilter.class.cast(getFilter()).type.name());
		getFilter().filter(s);
		return null;
	}
	
	private static class ViewHolder {
		TextView title;
		ImageView image;
	}
	
	private class IFilter extends Filter {
		private Operation.Type type;
		
		private IFilter(Operation.Type type) {
			this.type = type;
		}
		
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults filterResult = new FilterResults();
			synchronized (this) {
				if (constraint == null || constraint.length() == 0) {
					filterResult.values = booksOriginal;
					filterResult.count = booksOriginal.size();
					
				} else {
					Books result = booksOriginal.filter(type, String.valueOf(constraint));
					filterResult.values = result;
					filterResult.count = result.size();
				}
			}
			return filterResult;
		}
		
		@Override
		protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
			synchronized (this) {
				booksCurrent = (Books) filterResults.values;
				clear();
				addAll(booksCurrent.getBooks());
			}
		}
	}
}
