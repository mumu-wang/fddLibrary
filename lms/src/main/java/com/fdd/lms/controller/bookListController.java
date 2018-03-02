package com.fdd.lms.controller;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.lms.Model.Book;
import com.fdd.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/books")
public class BookListController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getBookList(ModelMap map) {
        map.addAttribute("bookList", bookService.getAllBook());
        return "bookList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBookList(ModelMap map){
        map.addAttribute("book",new Book());
        map.addAttribute("action", "create");
        return "bookForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addBookList(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/";
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable int id, ModelMap map) {
        map.addAttribute("book", bookService.getBookById(id));
        map.addAttribute("action", "update");
        return "bookForm";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/";
    }


}
