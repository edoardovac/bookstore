package com.assignment.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.assignment.bookstore.domain.Book;
import com.assignment.bookstore.domain.BookRepository;
import com.assignment.bookstore.domain.Category;
import com.assignment.bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository cRepository) {
		return (args) -> {
			log.info("save a couple of book");
			cRepository.save(new Category("Realism"));
			cRepository.save(new Category("Political Satire"));
			
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 10.00, cRepository.findByName("Realism").get(0)));
			repository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 9.99, cRepository.findByName("Political Satire").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("fetch all books by ernest hemingway");
			for (Book book : repository.findByAuthor("Ernest Hemingway")) {
				log.info(book.toString());
			}
		};
	}
}
