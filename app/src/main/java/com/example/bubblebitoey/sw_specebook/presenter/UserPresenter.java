package com.example.bubblebitoey.sw_specebook.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.view.UserView;

import java.io.Serializable;
import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */

public class UserPresenter {
	private static final CharSequence[] c = new CharSequence[]{"10฿", "100฿", "500฿", "1000฿"};
	private UserView view;
	private User user;
	
	public UserPresenter(UserView view, User user) {
		this.view = view;
		this.user = user;
		
		setName();
		setListener();
		setMoney();
	}
	
	public AlertDialog getAlertDialog(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("How many money to add?").setItems(c, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Integer integer = Integer.parseInt(c[i].subSequence(0, c[i].length() - 1).toString());
				user.addMoney(integer);
				setMoney();
			}
		});
		return builder.create();
	}
	
	private void setListener() {
		view.setOwnerBookListButton(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Map<String, Serializable> map = new HashMap<>();
				map.put("user", user);
				view.to(map, null); // TODO: 4/27/2017 AD new page
			}
		});
	}
	
	private void setName() {
		view.setName(user.getName());
	}
	
	private void setMoney() {
		view.setMoneyText(String.valueOf(user.getCurrentMoney()));
	}
}
