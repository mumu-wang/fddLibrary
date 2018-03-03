package com.fdd.lms.controller;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.lms.Model.Book;
import com.fdd.lms.Model.Loan;
import com.fdd.lms.Model.LoanInfo;
import com.fdd.lms.Model.User;
import com.fdd.lms.service.BookService;
import com.fdd.lms.service.LoanService;
import com.fdd.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookListController {

    @Autowired
    BookService bookService;
    @Autowired
    LoanInfo loanInfo;
    @Autowired
    UserService userService;
    @Autowired
    LoanService loanService;

    /*
     *功能：图书列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getBookList(ModelMap map) {
        List<Book> bookList = bookService.getAllBook();
        for (Book book : bookList) {
            if (book.getBookStatus() == 0) {
                book.setStatus("已借出");
            } else if (book.getBookStatus() == 1) {
                book.setStatus("在库");
            } else {
                book.setStatus("状态有误");
            }
        }
        map.addAttribute("bookList", bookList);
        return "bookList";
    }

    /*
     *功能：添加图书
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBookList(ModelMap map) {
        map.addAttribute("book", new Book());
        map.addAttribute("action", "create");
        return "bookForm";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addBookList(@ModelAttribute Book book, ModelMap map) {
        if (bookService.getBookById(book.getBookId()) != null) {
            map.addAttribute("errinfo", "改图书编号已经存在，请勿重复添加！");
            return "errorinfo";
        }
        if (bookService.getBookByIdAndDel(book.getBookId(), 1) != null) {
            map.addAttribute("errinfo", "改图编号已经删除，无法再次添加！");
            return "errorinfo";
        }

        bookService.addBook(book);
        return "redirect:/";
    }

    /*
     *功能：修改图书
     */
    @RequestMapping(value = "/update/{bookId}", method = RequestMethod.GET)
    public String getBook(@PathVariable int bookId, ModelMap map) {
        map.addAttribute("book", bookService.getBookById(bookId));
        map.addAttribute("action", "update");
        return "bookForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/";
    }

    /*
     *功能：删除图书
     */
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String delBook(@PathVariable int bookId, ModelMap map) {
        if (bookService.getBookById(bookId) == null) {
            map.addAttribute("errinfo", "改图书不存在，无法删除！");
            return "errorinfo";
        } else if (bookService.getBookByIdAndDel(bookId, 1) != null) {
            map.addAttribute("errinfo", "图书已经删除，无法再次删除！");
            return "errorinfo";
        }

        bookService.updateBookDel(bookId, 1);
        return "redirect:/books";
    }

    /*
     *功能：图书状态查询
     */
    @RequestMapping(value = "/status/{bookId}", method = RequestMethod.GET)
    public String bookStatusList(@PathVariable int bookId, ModelMap map) {
        Book book = bookService.getBookByIdAndStatus(bookId, 0);
        if (book == null) {
            return "redirect:/books";
        }
        Loan loan = loanService.selectNotFinish(book.getBookId());
        if (loan == null) {
            map.addAttribute("loanInfo", loanInfo);
            return "loanInfo";
        }
        User user = userService.getUserById(loan.getUserId());
        if (user == null) {
            map.addAttribute("errinfo", "借书用户不存在！");
            return "errorInfo";
        }

        loanInfo.setBookId(book.getBookId());
        loanInfo.setBookName(book.getBookName());
        loanInfo.setLoanTime(loan.getLoanTime());
        loanInfo.setUserId(loan.getUserId());
        loanInfo.setUserName(user.getUserName());
        map.addAttribute("loanInfo", loanInfo);
        return "loanInfo";
    }


}
