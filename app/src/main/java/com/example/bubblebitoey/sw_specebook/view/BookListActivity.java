package com.example.bubblebitoey.sw_specebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.adapter.GridAdapter;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.api.view.PassingActivity;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;

import java.util.*;
import java.util.concurrent.Executors;

import static com.example.bubblebitoey.sw_specebook.presenter.MainPresenter.CALL_USER_ACTIVITY;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Apr/2017 - 10:28 AM
 */
public class BookListActivity extends AppCompatActivity implements BookListView {
	private GridView gridView;
	private GridAdapter gridAdapter;
	private ProgressBar progressBar;
	private EditText searchBar;
	private Menu menu;
	private Spinner spinner;
	
	private BookListPresenter presenter;
	private Books books;
	private Operation.Type type;
	
	public BookListActivity(BookListPresenter presenter) {
		this.presenter = presenter;
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
		
		presenter.setView(this);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); // disable auto appear keyboard
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
		switch (item.getItemId()) {
			case R.id.login:
				UserFactory.getInstance().createMockUser(); // TODO: 4/27/2017 AD change to non mock
				login();
				break;
			case R.id.user:
				toAndWait(CALL_USER_ACTIVITY, UserDetailActivity.class);
				break;
			case R.id.logout:
				logout();
				break;
			case R.id.about:
				new MaterialDialog.Builder(this).title(String.format("%s v%s", getResources().getString(R.string.about_title), Constants.version)).content("Develop by").items(Constants.developerName).itemsCallback(new MaterialDialog.ListCallback() {
					@Override
					public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Constants.developerFacebook.get(which));
						startActivity(browserIntent);
					}
				}).positiveText(R.string.ok_message).canceledOnTouchOutside(true).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public BookListPresenter getPresenter() {
		return presenter;
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
	public void login() {
		User u = UserFactory.getInstance().getUser();
		if (u == null) logout();
		else {
			menu.findItem(R.id.login).setVisible(false);
			menu.findItem(R.id.user).setVisible(true);
			menu.findItem(R.id.user).setTitle(u.getName());
			menu.findItem(R.id.logout).setVisible(true);
		}
	}
	
	@Override
	public void logout() {
		User u = UserFactory.getInstance().getUser();
		if (u != null) login();
		else {
			menu.findItem(R.id.login).setVisible(true);
			menu.findItem(R.id.user).setVisible(false);
			menu.findItem(R.id.logout).setVisible(false);
		}
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
		// FIXME: 4/29/2017 AD error duplicate books
		// manualFilter();
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
			books.executeOnExecutor(Executors.newSingleThreadExecutor(), presenter);
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
		gridAdapter.filter(type, searchBar.getText().toString());
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
	public void setBook(final Book book) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gridAdapter.add(book);
			}
		});
	}
}
