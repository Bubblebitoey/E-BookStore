package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.adapter.GridAdapter;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.Store;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.model.real.RealStore;
import com.example.bubblebitoey.sw_specebook.presenter.MainPresenter;
import com.example.bubblebitoey.sw_specebook.presenter.PassingActivity;

import java.io.Serializable;
import java.util.*;

public class MainActivity extends AppCompatActivity implements BookListView {
	private GridView gridView;
	private GridAdapter gridAdapter;
	private ProgressBar progressBar;
	private EditText searchBar;
	private Menu menu;
	
	private MainPresenter presenter;
	private Books books;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gridView = (GridView) findViewById(R.id.gridView);
		gridAdapter = new GridAdapter(this, R.layout.grid_layout);
		gridView.setAdapter(gridAdapter);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		searchBar = (EditText) findViewById(R.id.search_bar);
		
		search(false); // searching feature
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // disable auto appear keyboard
		
		books = new Books();
		presenter = (MainPresenter) new MainPresenter(new RealStore().setView(this)).setView(this);
	}
	
	@Override
	public void setOnClickBook(AdapterView.OnItemClickListener listener) {
		gridView.setOnItemClickListener(listener);
	}
	
	@Override
	public void login(User u) {
		menu.findItem(R.id.login).setVisible(false);
		menu.findItem(R.id.user).setVisible(true);
		menu.findItem(R.id.user).setTitle(u.getName());
		menu.findItem(R.id.logout).setVisible(true);
	}
	
	@Override
	public void logout() {
		menu.findItem(R.id.login).setVisible(true);
		menu.findItem(R.id.user).setVisible(false);
		menu.findItem(R.id.logout).setVisible(false);
	}
	
	@Override
	public void to(Map<String, Serializable> data, Class nextActivity) {
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
	public void toAndWait(int code, Map<String, Serializable> data, Class nextActivity) {
		PassingActivity.newActivityWithResult(code, data, this, nextActivity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.topmenu, menu);
		this.menu = menu;
		logout();
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		presenter.menuClick(item);
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public synchronized void setMaxProgress(int max) {
		progressBar.setMax(max);
	}
	
	@Override
	public void addNewBook(final Book book) {
		books.add(book);
		setBook(book);
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
	
	@Override
	public synchronized void updateProgress(int current) {
		progressBar.setProgress(current);
	}
	
	@Override
	public void removeProgress() {
		progressBar.setVisibility(android.view.View.INVISIBLE);
	}
	
	@Override
	public void search(boolean enable) {
		searchBar.setVisibility(enable ? android.view.View.VISIBLE: android.view.View.INVISIBLE);
		searchBar.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if (!s.toString().isEmpty()) {
					if (Character.isDigit(s.toString().charAt(0))) gridAdapter.setFilter(Store.OperationType.YEAR);
					else gridAdapter.setFilter(Store.OperationType.TITLE);
				}
				gridAdapter.getFilter().filter(s.toString());
			}
		});
	}
	
	@Override
	public void sort(Comparator<Book> compare) {
		gridAdapter.sort(compare);
	}
	
	@Override
	public void filter(Store.OperationType type, String text) {
		gridAdapter.setFilter(type);
		gridAdapter.getFilter().filter(text);
	}
}