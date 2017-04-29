package com.example.bubblebitoey.sw_specebook.view.raw;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by kamontat on 4/27/2017 AD.
 */
public interface UserView extends View {
	void setName(String name);
	
	void setMoneyText(String text);
	
	void setOwnerBookListButton(android.view.View.OnClickListener listener);
	
	void setImage(Bitmap bitmap);
	
	Context getContext();
}
