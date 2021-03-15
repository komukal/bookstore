package com.course.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.course.bookstore.domain.Book;
import com.course.bookstore.domain.BookRepository;
import com.course.bookstore.domain.Category;
import com.course.bookstore.domain.CategoryRepository;
import com.course.bookstore.domain.User;
import com.course.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {

			Category c1 = new Category("Education");
			Category c2 = new Category("Autobiography");
			Category c3 = new Category("Fiction");

			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);

			Book book1 = new Book("Java Concurrency in Practice", "Brian Goetz", 2006, "978-0321349606", 33.13,
					crepository.findByName("Education").get(0));
			Book book2 = new Book("A Promised Land", "Barack Obama", 2020, "978-1524763169", 23.96,
					crepository.findByName("Autobiography").get(0));
			Book book3 = new Book("Becoming ", "Michelle Obama", 2018, "978-1524763138", 18.99,
					crepository.findByName("Autobiography").get(0));

			brepository.save(book1);
			brepository.save(book2);
			brepository.save(book3);

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

		};
	}
}
