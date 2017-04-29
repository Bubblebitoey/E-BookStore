package com.example.bubblebitoey.sw_specebook.model;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.util.Predicate;
import com.example.bubblebitoey.sw_specebook.api.Operation;
import com.example.bubblebitoey.sw_specebook.model.raw.BookList;
import com.example.bubblebitoey.sw_specebook.model.raw.Filterable;
import com.example.bubblebitoey.sw_specebook.presenter.BookListPresenter;

import java.io.IOException;
import java.util.*;

/**
 * Created by bubblebitoey on 4/20/2017 AD.
 */

public class Books extends AsyncTask<BookListPresenter, Void, Books> implements Parcelable, BookList, Filterable<Books> {
	private static Operation.FilteringBook filter;
	private List<Book> books;
	
	public Books(Book... book) {
		filter = new Operation.FilteringBook();
		this.books = new ArrayList<>(Arrays.asList(book));
	}
	
	protected Books(Parcel in) {
		books = in.createTypedArrayList(Book.CREATOR);
	}
	
	public static final Creator<Books> CREATOR = new Creator<Books>() {
		@Override
		public Books createFromParcel(Parcel in) {
			return new Books(in);
		}
		
		@Override
		public Books[] newArray(int size) {
			return new Books[size];
		}
	};
	
	@Override
	public void addNewBook(Book book) {
		books.add(book);
	}
	
	@Override
	public void addAll(Books books) {
		this.books.addAll(books.getBooks());
	}
	
	@Override
	public void clear() {
		this.books.clear();
	}
	
	@Override
	public Book getBook(int pos) {
		try {
			return books.get(pos);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<Book> getBooks() {
		return books.subList(0, books.size());
	}
	
	public int size() {
		return books.size();
	}
	
	public void sort(Comparator<? super Book> comparator) {
		Collections.sort(books, comparator);
	}
	
	@Override
	public void sort(Operation.Type type) {
		sort(new Operation.SortingBook().by(type));
	}
	
	@Override
	public void manualFilter() {
		// do nothing
	}
	
	@Override
	public Books filter(Operation.Type type, String str) {
		filter.update(str);
		Predicate<Book> predicate = filter.by(type);
		Books newBooks = new Books();
		synchronized (this) {
			for (Book b : books) {
				if (predicate.apply(b)) newBooks.addNewBook(b);
			}
		}
		return newBooks;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeTypedList(books);
	}
	
	@Override
	protected Books doInBackground(BookListPresenter... presenters) {
		BookListPresenter presenter1 = presenters[0];
		// print format: fetch/current_read/all
		int f = 0;
		int c = 0;
		for (Book b : books) {
			try {
				if (!b.haveImage()) {
					b.fetchImage();
					f++;
				}
				c++;
				Log.i("FETCH IMAGE", String.format("(%d/%d/%d)", f, c, size()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		presenter1.notifyWhenDataSetChange();
		return this;
	}
}
