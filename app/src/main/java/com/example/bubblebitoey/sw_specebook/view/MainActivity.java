package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.adapter.GridAdapter;
import com.example.bubblebitoey.sw_specebook.model.Book;
import com.example.bubblebitoey.sw_specebook.model.Books;
import com.example.bubblebitoey.sw_specebook.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements View {
	private MainPresenter presenter;
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView = (GridView) findViewById(R.id.gridView);

		presenter = new MainPresenter(this);
		presenter.execute(new Book(12, "asdf", "https://imagery.pragprog.com/products/508/dcbang2_largebeta.jpg", 24.95), new Book(13, "fdsa", "https://imagery.pragprog.com/products/508/dcbang2_largebeta.jpg", 12.54), new Book(14, "ddsaa", "https://imagery.pragprog.com/products/508/dcbang2_largebeta.jpg", 99.01));
	}

	@Override
	public void setData(Books books) {
		GridAdapter gridAdapter = new GridAdapter(this, R.layout.grid_layout, books);
		gridView.setAdapter(gridAdapter);
	}
}


//		try {
//			BufferedReader read = new BufferedReader(new InputStreamReader(Constants.link.openStream()));
//			StringBuilder sb = new StringBuilder();
//			String line;
//			while ((line = read.readLine()) != null) {
//				sb.append(line).append("\n");
//			}
//
//			JSONArray array = new JSONArray(sb.toString());

//		} catch (IOException | JSONException e) {
//			e.printStackTrace();
//		}
//		return null;