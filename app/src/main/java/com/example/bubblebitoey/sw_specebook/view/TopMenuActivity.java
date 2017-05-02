package com.example.bubblebitoey.sw_specebook.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.bubblebitoey.sw_specebook.R;
import com.example.bubblebitoey.sw_specebook.api.factory.UserFactory;
import com.example.bubblebitoey.sw_specebook.constants.Constants;
import com.example.bubblebitoey.sw_specebook.model.raw.User;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;
import com.example.bubblebitoey.sw_specebook.presenter.ViewPresenter;
import com.example.bubblebitoey.sw_specebook.view.raw.BookListView;
import com.example.bubblebitoey.sw_specebook.view.raw.View;

import static com.example.bubblebitoey.sw_specebook.presenter.MainPresenter.CALL_USER_ACTIVITY;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 01/May/2017 - 9:43 PM
 */
public abstract class TopMenuActivity<T extends View> extends AppCompatActivity implements View {
	private ViewPresenter<T> presenter;
	private Menu menu;
	
	public TopMenuActivity(ViewPresenter<T> presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.topmenu, menu);
		this.menu = menu;
		UserFactory.getInstance().toggleMenu(this);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean b = super.onPrepareOptionsMenu(menu);
		Log.d("UPDATE", "Option Menu");
		UserFactory.getInstance().toggleMenu(this);
		return b;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.login:
				presenter.login();
				toggleMenu();
				break;
			case R.id.user:
				toAndWait(CALL_USER_ACTIVITY, UserDetailActivity.class);
				break;
			case R.id.logout:
				presenter.logout();
				toggleMenu();
				break;
			case R.id.refresh:
				if (presenter instanceof BookListPresenter) {
					((BookListPresenter) presenter).refresh();
				}
				break;
			case R.id.about:
				new MaterialDialog.Builder(this).title(String.format("%s %s", getResources().getString(R.string.about_title), Constants.version)).content("Develop by").items(Constants.developerName).itemsCallback(new MaterialDialog.ListCallback() {
					@Override
					public void onSelection(MaterialDialog dialog, android.view.View itemView, int which, CharSequence text) {
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Constants.developerFacebook.get(which));
						startActivity(browserIntent);
					}
				}).positiveText(R.string.ok_message).canceledOnTouchOutside(true).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void toggleMenu() {
		User u = UserFactory.getInstance().getUser();
		if (u != null) {
			menu.findItem(R.id.login).setVisible(false);
			menu.findItem(R.id.user).setVisible(true);
			menu.findItem(R.id.user).setTitle(u.getName());
			menu.findItem(R.id.logout).setVisible(true);
		} else {
			menu.findItem(R.id.login).setVisible(true);
			menu.findItem(R.id.user).setVisible(false);
			menu.findItem(R.id.logout).setVisible(false);
		}
		
		// for refresh
		boolean isRefresh = false;
		try {
			BookListView.class.cast(this);
			isRefresh = true;
		} catch (ClassCastException ignore) {
		}
		menu.findItem(R.id.refresh).setVisible(isRefresh);
	}
	
	protected ViewPresenter<T> getPresenter() {
		return presenter;
	}
}
