package com.fdd.lms.controller;

import com.fdd.lms.Model.Book;
import com.fdd.lms.lmsUtil.LmsUtil;
import com.fdd.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Lin.wang
 * @date 2018-03-10 21:39.
 */

@Controller
@RequestMapping(value = "/userQuery")
public class UserBookListController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String userQueryBookForm(){
        return "queryBookForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String userQueryBookList(ModelMap map,Book book){
        List<Book> listBook = bookService.getBookByFuzzyName(book.getBookName());
        listBook = LmsUtil.transformationBookStatus(listBook);
        map.addAttribute("bookList", listBook);
        return "UserBookList";
    }


}
