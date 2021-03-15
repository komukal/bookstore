package com.course.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.bookstore.domain.Book;
import com.course.bookstore.domain.BookRepository;
import com.course.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookrepository;
	@Autowired
	private CategoryRepository categoryrepository;

	@RequestMapping(value = "/index")
	public String indexString() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";

	}

	/* get all the books with rest
	@RequestMapping(value = "/api/getall", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookrepository.findAll();
	}

	// find book by id with rest
	@RequestMapping(value = "/api/find/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookid) {
		return bookrepository.findById(bookid);
	}
*/
	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("categories", categoryrepository.findAll());
		model.addAttribute("book", new Book());
		return "addbook";
	}

	@GetMapping("/edit/{bookid}")
	public String editBook(@PathVariable Long bookid, Model model) {
		model.addAttribute("book", bookrepository.findById(bookid));
		model.addAttribute("categories", categoryrepository.findAll());
		return "editbook";
	}

	@PostMapping("/save")
	public String saveBook(Book book) {
		bookrepository.save(book);
		return "redirect:/booklist";
	}
	@PreAuthorize("hasRole('ADMIN')")

	@GetMapping("/delete/{bookid}")
	public String deleteBook(@PathVariable Long bookid, Model model) {
		bookrepository.deleteById(bookid);
		return "redirect:../booklist";
	}

}
