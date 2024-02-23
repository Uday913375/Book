package com.uday.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uday.entity.Book;
import com.uday.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;
     
	// to print direct msg on the UI without html file 
		@GetMapping("/uday")
		@ResponseBody
		public String getMsg()
		{
			String msg ="welcome to uday";
			return msg;
		}

//		different way to write controller methods and Approach 2 
//		 @GetMapping("/index")
//		public String index(Model model) {
//			model.addAttribute("book", new Book());
//			 return "index";	
//		 }

		
		
// To display the form  using process 1 with ModelAndView
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();

		// sending empty obj for form binding
		
	mav.addObject("book", new Book( ));

		mav.setViewName("index");

		return mav;
	}
		 
//our method is mapped to post request in UI page 
	@PostMapping("/book")
	public ModelAndView saveBook(Book book) {

		ModelAndView mav = new ModelAndView();

		boolean status = service.SaveBook(book);
		if (status) {
			mav.addObject("succMsg", "Book Saved");
		} else {
			mav.addObject("errMsg", " Failed to save");
		}

		mav.setViewName("index");

		return mav;
	}

	@GetMapping("/books")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId) {
		service.deleteBook(bookId);
		
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
        mav.setViewName("booksView");		
		return mav;
		
	}
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId) {
		Book bookobj = service.getBookId(bookId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", bookobj);
		mav.setViewName("index");
        return mav;
	}
}
