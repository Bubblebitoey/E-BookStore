package com.example.bubblebitoey.sw_specebook.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;

import java.io.IOException;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class GridAdapter extends ArrayAdapter {
	private Context context;
	private int layoutResourceId;
	private Books books;

	public GridAdapter(Context context, int layoutResourceId, Books books) {
		super(context, layoutResourceId, books.getBooks());
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.books = books;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageTitle = (TextView) row.findViewById(R.id.text);
			holder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		Book item = books.getBook(position);
		holder.imageTitle.setText(item.getTitle());
		try {
			holder.image.setImageBitmap(item.getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}

	private static class ViewHolder {
		TextView imageTitle;
		ImageView image;
	}
}
