package com.example.bubblebitoey.sw_specebook.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.model.User;
import com.example.bubblebitoey.sw_specebook.presenter.PassingActivity;

import java.io.Serializable;
import java.util.*;

public class UserDetailActivity extends AppCompatActivity {
	private ImageView image;
	private TextView userText;
	private TextView money;
	private Button myBooks;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);
		final User user = (User) getIntent().getSerializableExtra("user");
		
		image = (ImageView) findViewById(R.id.user_image);
		userText = (TextView) findViewById(R.id.name);
		money = (TextView) findViewById(R.id.amount);
		myBooks = (Button) findViewById(R.id.myBooks);
		
		
		userText.setText(user.getName());
		money.setText(user.getCurrentMoney() + " ฿");
		myBooks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, Serializable> map = new HashMap<>();
				map.put("user", user);
				PassingActivity.newActivity(map, UserDetailActivity.this, null); // TODO: 4/27/2017 AD new user books
			}
		});
		
		
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("ADD MONEY", "new money");
			}
		});
	}
	
}
