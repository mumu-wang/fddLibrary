package com.fdd.lms.controller;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.lms.Model.Book;
import com.fdd.lms.Model.Loan;
import com.fdd.lms.Model.LoanInfo;
import com.fdd.lms.Model.User;
import com.fdd.lms.lmsUtil.LmsUtil;
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
@RequestMapping(value = "/admin/books")
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
        bookList = LmsUtil.transformationBookStatus(bookList);
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
            map.addAttribute("errinfo", "该图书编号已经存在，请勿重复添加！");
            return "errorInfo";
        }
        if (bookService.getBookByIdAndDel(book.getBookId(), 1) != null) {
            map.addAttribute("errinfo", "该图编号已经删除，无法再次添加！");
            return "errorInfo";
        }

        bookService.addBook(book);
        return "redirect:/admin";
    }

    /*
     *功能：修改图书
     */
    @RequestMapping(value = "/update/{bookId}", method = RequestMethod.GET)
    public String getBook(@PathVariable String bookId, ModelMap map) {
        map.addAttribute("book", bookService.getBookById(bookId));
        map.addAttribute("action", "update");
        return "bookForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putBook(@ModelAttribute Book book) {
        bookService.updateBook(book);
        return "redirect:/admin";
    }

    /*
     *功能：删除图书
     */
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String delBook(@PathVariable String bookId, ModelMap map) {
        if (bookService.getBookById(bookId) == null) {
            map.addAttribute("errinfo", "该图书不存在，无法删除！");
            return "errorInfo";
        } else if (bookService.getBookByIdAndDel(bookId, 1) != null) {
            map.addAttribute("errinfo", "图书已经删除，无法再次删除！");
            return "errorInfo";
        }

        bookService.updateBookDel(bookId, 1);
        return "redirect:/admin/books";
    }

    /*
     *功能：图书状态查询
     */
    @RequestMapping(value = "/status/{bookId}", method = RequestMethod.GET)
    public String bookStatusList(@PathVariable String bookId, ModelMap map) {
        Book book = bookService.getBookByIdAndStatus(bookId, 0);
        if (book == null) {
            return "redirect:/admin/books";
        }
        Loan loan = loanService.selectNotFinishByBookId(book.getBookId());
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
