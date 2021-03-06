package com.example.bubblebitoey.sw_specebook.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.view.MainActivity;
import com.example.bubblebitoey.sw_specebook.view.UserBookListActivity;
import com.example.bubblebitoey.sw_specebook.view.raw.UserView;

import java.util.*;

/**
 * Created by kamontat on 4/27/2017 AD.
 */
public class UserPresenter extends AbstractViewPresenter<UserView> {
	private static final CharSequence[] c = new CharSequence[]{"10฿", "100฿", "500฿", "1000฿"};
	private User user;
	
	public UserPresenter() {
		this.user = UserFactory.getInstance().getUser();
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
				Map<String, Parcelable> map = new HashMap<>();
				map.put(Constants.USER, user);
				view.to(map, UserBookListActivity.class);
			}
		});
		
		view.setLogoutButton(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				logout();
				UserPresenter.super.view.to(MainActivity.class);
			}
		});
	}
	
	private void setName() {
		view.setName(user.getName());
	}
	
	private void setMoney() {
		view.setMoneyText(String.valueOf(user.getCurrentMoney()));
	}
	
	private void setImage() {
		view.setImage(user.getImage(view.getContext()));
	}
	
	@Override
	public void presenterSetting() {
		setName();
		setListener();
		setMoney();
		setImage();
	}
	
	@Override
	public void login() {
		// nothing
	}
	
	@Override
	public void logout() {
		super.logout();
	}
}
