package com.fdd.lms.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Lin.wang
 * @date 2018-03-04 21:08.
 */

@Controller
@RequestMapping(value = "/admin/query")
public class QueryController {
    @Autowired
    BookService bookService;
    @Autowired
    LoanService loanService;
    @Autowired
    UserService userService;
    @Autowired
    List<LoanInfo> loanInfo;

    /*
     *功能：图书查询
     */
    @RequestMapping(value = "book", method = RequestMethod.GET)
    public String queryBook() {
        return "queryBookForm";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String queryBookResult(@ModelAttribute Book book, ModelMap map) {

        List<Book> listBook = bookService.getBookByFuzzyName(book.getBookName());
        listBook = LmsUtil.transformationBookStatus(listBook);
        map.addAttribute("bookList", listBook);
        return "bookList";
    }

    /*
     *功能：用户查询
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String queryUser() {
        return "queryUserForm";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String queryUserResult(@ModelAttribute User user, ModelMap map) {
        //TBD 用户信息和用户借阅信息
        List<Loan> listLoan = loanService.selectNotFinishByUserId(user.getUserId());
        if(listLoan != null) {
            loanInfo.clear();
            for(Loan loan  : listLoan){
                LoanInfo ln = new LoanInfo();
                String bookId = loan.getBookId();
                String userId = loan.getUserId();
                ln.setUserId(userId);
                ln.setLoanTime(loan.getLoanTime());
                ln.setBookId(bookId);
                //bookname
                Book book = bookService.getBookById(bookId);
                ln.setBookName(book.getBookName());
                //username
                User ur = userService.getUserById(userId);
                ln.setUserName(ur.getUserName());
                loanInfo.add(ln);
            }
            map.addAttribute("loanInfo",loanInfo);
        }

        return "loanInfo";
    }



}
