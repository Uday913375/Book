package com.uday.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.entity.Book;
import com.uday.repo.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findByActiveSW("y");
	}

	@Override
	public boolean SaveBook(Book book) {
		book.setActiveSW("y");
		Book savedBook = bookRepo.save(book);
		if (savedBook.getBookId() != null) {
			return true;
		}
		return false;
	}
	@Override
	public void deleteBook(Integer bookId) {
		// Hard delete
     // bookRepo.deleteById(bookId);
		// for soft delete we need to write below method
		Optional<Book> findById = bookRepo.findById(bookId);
		if(findById.isPresent()) {
			Book book = findById.get();
			book.setActiveSW("N");
			bookRepo.save(book);
		}
	}
	@Override
	public Book getBookId(Integer bookId) {
		Optional<Book> findById = bookRepo.findById(bookId);
		if(findById.isPresent()) {
			return findById.get();
		}			
			return null;
	}
}

