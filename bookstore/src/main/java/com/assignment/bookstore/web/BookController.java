package com.assignment.bookstore.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.assignment.bookstore.domain.Book;
import com.assignment.bookstore.domain.BookRepository;
import com.assignment.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;

	// Login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all books
	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	// Add new book
	@RequestMapping(value = "/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	// Save new book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}

	// Delete book
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		// watch out! it's only two dots after the column
		return "redirect:../booklist";
	}

	// RESTful service to get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	// @ResponseBody signals it's a REST service
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get book by id
	// THIS NEEDS @JsonIgnore IN ONE-TO-MANY RELATIONSHIPS (es: see Department)
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

}
