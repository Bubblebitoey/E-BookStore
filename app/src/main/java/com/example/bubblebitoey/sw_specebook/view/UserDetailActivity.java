package com.example.bubblebitoey.sw_specebook.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.view.PassingActivity;
import com.example.bubblebitoey.sw_specebook.presenter.UserPresenter;
import com.example.bubblebitoey.sw_specebook.view.raw.UserView;

import java.util.*;

public class UserDetailActivity extends AppCompatActivity implements UserView {
	private UserPresenter presenter;
	private ImageView image;
	private TextView userText;
	private TextView money;
	private Button myBooks;
	private Button logoutBtn;
	
	private AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_detail);
		
		image = (ImageView) findViewById(R.id.user_image);
		userText = (TextView) findViewById(R.id.name);
		money = (TextView) findViewById(R.id.amount);
		myBooks = (Button) findViewById(R.id.myBooks);
		logoutBtn = (Button) findViewById(R.id.logout_btn);
		
		
		presenter = (UserPresenter) new UserPresenter().setView(this);
		alert = presenter.getAlertDialog(this);
		
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				alert.show();
			}
		});
	}
	
	@Override
	public void setName(String name) {
		userText.setText(name);
	}
	
	@Override
	public void setMoneyText(String text) {
		money.setText(text);
	}
	
	@Override
	public void setLogoutButton(View.OnClickListener listener) {
		logoutBtn.setOnClickListener(listener);
	}
	
	@Override
	public void setOwnerBookListButton(View.OnClickListener listener) {
		myBooks.setOnClickListener(listener);
	}
	
	@Override
	public void setImage(Bitmap bitmap) {
		image.setImageBitmap(bitmap);
	}
	
	@Override
	public Context getContext() {
		return getBaseContext();
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
}
