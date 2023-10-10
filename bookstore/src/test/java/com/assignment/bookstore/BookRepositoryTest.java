package com.assignment.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.bookstore.domain.Book;
import com.assignment.bookstore.domain.BookRepository;
import com.assignment.bookstore.domain.Category;
import com.assignment.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository cRepository;

	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("A Farewell to Arms");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
	}

	@Test
	public void findByAuthorShouldReturnBook() {
		List<Book> books = repository.findByAuthor("Ernest Hemingway");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("A Farewell to Arms");
	}
	
	@Test
	public void createNewBook() {
		Category category = new Category("Fiction");
		cRepository.save(category);
		Book book = new Book("The Lord of the Rings", "J. R. R. Tolkien", 1955, "9876-ABC", 9.99, category);
		repository.save(book);
		assertThat(book.getId()).isNotNull();	
	}
	
	@Test
	public void deleteBook() {
		Category category = new Category("Fiction");
		cRepository.save(category);
		Book book = new Book("The Lord of the Rings", "J. R. R. Tolkien", 1955, "9876-ABC", 9.99, category);
		repository.save(book);
		
		repository.deleteById(book.getId());
	}
	
}
