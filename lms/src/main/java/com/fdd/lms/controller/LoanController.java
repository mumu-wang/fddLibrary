package com.fdd.lms.controller;

import com.fdd.lms.Model.Loan;
import com.fdd.lms.service.BookService;
import com.fdd.lms.service.LoanService;
import com.fdd.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Lin.wang
 * @date 2018-03-02 19:31.
 */

@Controller
@RequestMapping(value = "/loan")
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userServic;

    /*
     *功能：借书
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLoanForm() {
        return "loanForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newLoan(@ModelAttribute Loan loan, ModelMap map) {

        if (bookService.getBookById(loan.getBookId()) == null) {
            map.addAttribute("errinfo", "该图书不存在，请检查图书编号是否正确！");
            return "errorInfo";
        } else if (userServic.getUserById(loan.getUserId()) == null) {
            map.addAttribute("errinfo", "该用户不存在，请检查用户编号是否正确！");
            return "errorInfo";
        } else if (bookService.getBookByIdAndStatus(loan.getBookId(), 1) == null) {
            map.addAttribute("errinfo", "改图书已经借出！");
            return "errorinfo";
        }
        loanService.loanBook(loan);
        return "redirect:/";
    }

    /*
     *功能：还书
     */
    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String returnBookForm() {
        return "returnForm";
    }

    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnBook(@ModelAttribute Loan loan, ModelMap map) {

        if (bookService.getBookById(loan.getBookId()) == null) {
            map.addAttribute("errinfo", "该图书不存在，请检查图书编号是否正确！");
            return "errorInfo";
        } else if (bookService.getBookByIdAndStatus(loan.getBookId(), 0) == null) {
            map.addAttribute("errinfo", "该图书已经归还！");
            return "errorInfo";
        } else {
            bookService.updateBookStatus(loan.getBookId(), 1);
            return "redirect:/";
        }
    }

}
