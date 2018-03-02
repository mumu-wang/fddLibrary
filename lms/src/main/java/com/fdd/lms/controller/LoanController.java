package com.fdd.lms.controller;

import com.fdd.lms.Model.Book;
import com.fdd.lms.Model.Loan;
import com.fdd.lms.Model.User;
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
    //借书
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLoanForm(){
        return "loanForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newLoan(@ModelAttribute Loan loan,ModelMap map) {
        Book book = bookService.getBookById(loan.getBookId());
        User user = userServic.getUserById(loan.getUserId());
        if(book == null){
            map.addAttribute("errinfo","该图书不存在，请检查图书编号是否正确！");
            return "errorInfo";
        }else if(user == null){
            map.addAttribute("errinfo","该用户不存在，请检查用户编号是否正确！");
            return "errorInfo";
        }
        loanService.loanBook(loan);
        return "redirect:/";
    }

    //还书
    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String returnBookForm(){
        return "returnForm";
    }

    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnBook(@ModelAttribute Loan loan,ModelMap map) {
        Book book = bookService.getBookById(loan.getBookId());

        if(book == null){
            map.addAttribute("errinfo","该图书不存在，请检查图书编号是否正确！");
            return "errorInfo";
        }else if(loanService.isBookReturn(loan.getBookId())) {
            map.addAttribute("errinfo", "该图书已经归还！");
            return "errorInfo";
        }else{
            loanService.returnBook(loan.getBookId());
            return "redirect:/";
        }

    }


//    @RequestMapping(value = "/new1")
//    public String addNewLoan(ModelMap map) {
//        map.addAttribute("errinfo","出错啦，出错啦");
//        return "error";
//    }



}
