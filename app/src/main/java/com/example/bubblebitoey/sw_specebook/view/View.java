package com.example.bubblebitoey.sw_specebook.view;

import com.example.bubblebitoey.sw_specebook.model.Book;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public interface View {
	void setMaxProgress(int max);

	void updateData(Book book);

	void updateProgress(int current);

	void removeProgress();
}
