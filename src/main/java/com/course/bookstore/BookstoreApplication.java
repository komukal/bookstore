package com.course.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.course.bookstore.domain.Book;
import com.course.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book book1 = new Book("Java Concurrency in Practice", "Brian Goetz", 2006, "978-0321349606", 33.13);
			Book book2 = new Book("A Promised Land", "Barack Obama", 2020, "978-1524763169", 23.96);
			Book book3 = new Book("Becoming ","Michelle Obama", 2018, "978-1524763138", 18.99);
			
			repository.save(book1);
			repository.save(book2);
			repository.save(book3);
		};
	}
}
