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
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.Store;

import java.io.IOException;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class GridAdapter extends ArrayAdapter<Book> {
	private static final String TAG = "Adapter";
	private Books books;
	private Context context;
	private int layoutResourceId;
	private Filter f;

	public GridAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		books = new Books();
	}

	@Override
	public synchronized void add(Book object) {
		super.add(object);
		books.add(object);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;

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

		Book item = books.getBook(position);
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
				this.f = new TitleFilter();
				break;
			case YEAR:
				this.f = new YearFilter();
				break;
		}
	}

	@Override
	public void sort(Comparator<? super Book> comparator) {
		books.sort(comparator);
		super.sort(comparator);
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
		private YearFilter() {
		}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.d(TAG + " Year", String.valueOf(constraint));
			FilterResults filterResult = new FilterResults();
			Books result = new Books();

			for (int i = 0; i < books.size(); i++) {
				Book b = books.getBook(i);
				Log.d(TAG + "Year", b.toString());
				if (b.getYear().startsWith(String.valueOf(constraint))) {
					result.add(b);
				}
			}

			filterResult.values = result;
			filterResult.count = result.getBooks().size();

			return filterResult;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			Log.d("Year", String.valueOf(results.count));
			books = (Books) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	}

	private class TitleFilter extends Filter {
		private TitleFilter() {
		}

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.d(TAG + " Title", String.valueOf(constraint));
			FilterResults filterResult = new FilterResults();
			Books result = new Books();

			for (int i = 0; i < books.size(); i++) {
				Book b = books.getBook(i);
				if (b.getTitle().contains(constraint)) {
					result.add(b);
				}
			}

			filterResult.values = result;
			filterResult.count = result.getBooks().size();

			return filterResult;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			Log.d("title", String.valueOf(results.count));
			books = (Books) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}
	}

}
