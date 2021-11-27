package com.pluralsight.bookstore.util;

public class TextUtil {

	public String sanitize(final String title) {

		return title.replaceAll("\\s+", " ");

	}

}
