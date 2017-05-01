package com.example.bubblebitoey.sw_specebook.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.adapter.GridAdapter;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.api.view.PassingActivity;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

import java.util.*;
import java.util.concurrent.Executors;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 10:28 AM
 */
public class BookListActivity extends TopMenuActivity<BookListView> implements BookListView {
	private GridView gridView;
	private GridAdapter gridAdapter;
	private ProgressBar progressBar;
	private EditText searchBar;
	private Menu menu;
	private Spinner spinner;
	private Books books;
	private Operation.Type type;
	
	public BookListActivity(BookListPresenter presenter) {
		super(presenter);
		books = new Books();
	}
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gridView = (GridView) findViewById(R.id.gridView);
		gridAdapter = new GridAdapter(this, R.layout.grid_layout);
		gridView.setAdapter(gridAdapter);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		searchBar = (EditText) findViewById(R.id.search_bar);
		spinner = (Spinner) findViewById(R.id.spinner);
		
		getPresenter().setView(this);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // disable auto appear keyboard
	}
	
	@Override
	public void setOption() {
		spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(Operation.Type.values())));
	}
	
	@Override
	public void setOptionEvent() {
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				Operation.Type t = (Operation.Type) adapterView.getItemAtPosition(i);
				type = t;
				setSearchPlaceHolder(t.name());
				manualFilter();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});
	}
	
	@Override
	public void setSearchEvent() {
		searchBar.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (s == null || s.toString().length() == 0) // if empty
					gridAdapter.filter(type, "");
				else // otherwise
					gridAdapter.filter(type, s.toString());
			}
		});
	}
	
	@Override
	public void setSearchPlaceHolder(String s) {
		searchBar.setHint(getResources().getString(R.string.searching_by) + " " + s);
	}
	
	@Override
	public void to(Map<String, Parcelable> data, Class nextActivity) {
		PassingActivity.newActivity(data, this, nextActivity);
	}
	
	@Override
	public void to(Class nextActivity) {
		PassingActivity.newActivity(this, nextActivity);
	}
	
	@Override
	public void toAndWait(int code, Class nextActivity) {
		PassingActivity.newActivityWithResult(code, this, nextActivity);
	}
	
	@Override
	public void toAndWait(int code, Map<String, Parcelable> data, Class nextActivity) {
		PassingActivity.newActivityWithResult(code, data, this, nextActivity);
	}
	
	@Override
	public void addNewBook(Book book) {
		manualFilter();
		books.addNewBook(book);
		setBook(book);
	}
	
	@Override
	public void addAll(Books books) {
		synchronized (this) {
			createProgress();
			setMaxProgress(books.size());
			int i = 0;
			for (Book b : books.getBooks()) {
				addNewBook(b);
				updateProgress(0);
			}
			books.executeOnExecutor(Executors.newSingleThreadExecutor(), (BookListPresenter) getPresenter());
		}
	}
	
	@Override
	public void notifyOnChange() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gridAdapter.notifyDataSetChanged();
			}
		});
	}
	
	@Override
	public void clear() {
		books.clear();
		gridAdapter.clear();
	}
	
	@Override
	public Book getBook(int pos) {
		return books.getBook(pos);
	}
	
	@Override
	public synchronized void manualFilter() {
		if (type != null) gridAdapter.filter(type, searchBar.getText().toString());
	}
	
	@Override
	public void setMaxProgress(int max) {
		progressBar.setMax(max);
	}
	
	@Override
	public synchronized void updateProgress(int current) {
		progressBar.setProgress(current);
	}
	
	@Override
	public void createProgress() {
		progressBar.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void removeProgress() {
		progressBar.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void sort(Comparator<Book> comparator) {
		gridAdapter.sort(comparator);
	}
	
	@Override
	public void sort(Operation.Type type) {
		gridAdapter.sort(new Operation.SortingBook().by(type));
	}
	
	@Override
	public void filter(Operation.Type type, String text) {
		gridAdapter.filter(type, text);
	}
	
	@Override
	public void setOnClickBook(AdapterView.OnItemClickListener listener) {
		gridView.setOnItemClickListener(listener);
	}
	
	@Override
	public Context getContext() {
		return this;
	}
	
	@Override
	public void setBook(final Book book) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gridAdapter.add(book);
			}
		});
	}
}
