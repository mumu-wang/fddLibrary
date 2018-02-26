package com.fdd.zte.lms.lms.controller;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.zte.lms.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/books")
public class bookListController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserList(ModelMap map) {
        map.addAttribute("bookList", bookService.getAllBook());
        return "bookList";
    }

}
