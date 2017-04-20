package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.adapter.GridAdapter;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements View {
	private MainPresenter presenter;
	private GridView gridView;
	private GridAdapter gridAdapter;
	private ProgressBar progressBar;

	private Books books;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView = (GridView) findViewById(R.id.gridView);
		gridAdapter = new GridAdapter(this, R.layout.grid_layout);
		gridView.setAdapter(gridAdapter);
		progressBar = (ProgressBar) findViewById(R.id.progress);

		books = new Books();

		presenter = new MainPresenter(this);
		presenter.execute();
	}

	@Override
	public void updateData(final Book book) {
		books.add(book);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gridAdapter.add(book);
			}
		});
	}

	@Override
	public synchronized void setMaxProgress(int max) {
		progressBar.setMax(max);
	}

	@Override
	public synchronized void updateProgress(int current) {
		progressBar.setProgress(current);
	}

	@Override
	public void removeProgress() {
		progressBar.setVisibility(android.view.View.INVISIBLE);
	}
}