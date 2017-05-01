package com.example.bubblebitoey.sw_specebook.presenter;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.view.BookDetailActivity;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 12:45 PM
 */
public abstract class AbstractBookListPresenter extends AbstractViewPresenter<BookListView> implements BookListPresenter {
	
	@Override
	public void presenterSetting() {
		setDropdown();
		setOnClick();
	}
	
	private void setDropdown() {
		view.setOption();
		view.setOptionEvent();
		view.setSearchEvent();
	}
	
	private void setOnClick() {
		view.setOnClickBook(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View newView, int i, long l) {
				Log.d("NEXT PAGE", "click image");
				Map<String, Parcelable> map = new HashMap<>();
				map.put(Constants.BOOK, (Book) adapterView.getItemAtPosition(i));
				view.to(map, BookDetailActivity.class); // TODO: 4/27/2017 AD show book information page
			}
		});
	}
	
	@Override
	public void addBook(Book b) {
		view.addNewBook(b);
	}
	
	@Override
	public void addBook(Book b, int number) {
		view.addNewBook(b);
		view.updateProgress(number);
	}
	
	@Override
	public void addBooks(Books b) {
		view.addAll(b);
	}
	
	@Override
	public void setBookNumber(int number) {
		view.setMaxProgress(number);
	}
	
	@Override
	public void startLoading() {
		view.createProgress();
	}
	
	@Override
	public void endLoading() {
		view.removeProgress();
	}
	
	@Override
	public void sort(Operation.Type type) {
		view.sort(type);
	}
	
	@Override
	public void filter(Operation.Type type, String str) {
		view.filter(type, str);
	}
	
	@Override
	public void notifyWhenDataSetChange() {
		view.notifyOnChange();
	}
	
	@Override
	public void clear() {
		view.clear();
	}
	
	@Override
	public void refresh() {
		try {
			clear();
			fetchBook();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			new MaterialDialog.Builder(view.getContext()).title("Cannot start while loading old once").content("please wait, until the old books are downloaded and refresh them").positiveText("OK").show();
		}
	}
}
