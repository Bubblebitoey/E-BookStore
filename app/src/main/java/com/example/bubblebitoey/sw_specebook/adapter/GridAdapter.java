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

import java.io.IOException;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class GridAdapter extends ArrayAdapter<Book> {
	private Context context;
	private int layoutResourceId;

	public GridAdapter(Context context, int layoutResourceId) {
		super(context, layoutResourceId);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
	}

	@Override
	public synchronized void add(Book object) {
		super.add(object);
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

		Book item = getItem(position);
		holder.title.setText(String.format(Locale.ENGLISH, "%s: %s \nprice: %.2f$", item.getId(), item.getTitle(), item.getPrice()));
		try {
			holder.image.setImageBitmap(item.getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;
	}

	private static class ViewHolder {
		TextView title;
		ImageView image;
	}
}
