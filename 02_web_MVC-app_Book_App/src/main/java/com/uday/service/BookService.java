package com.uday.service;

import java.util.List;

import com.uday.entity.Book;

public interface BookService {
	
	public List<Book> getAllBooks();

	boolean SaveBook(Book book);
	public void deleteBook(Integer bookId); 
	public Book getBookId(Integer bookId);
	
 
}
