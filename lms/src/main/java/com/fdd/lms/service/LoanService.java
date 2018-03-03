package com.fdd.lms.service;

import com.fdd.lms.Model.Loan;
import com.fdd.lms.dao.BookDAO;
import com.fdd.lms.dao.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin.wang
 * @date 2018-03-02 19:33.
 */

@Service
public class LoanService {
    @Autowired
    LoanDAO loanDAO;
    @Autowired
    BookDAO bookDAO;

    public void loanBook(Loan loan){
        loanDAO.insertLoan(loan);
        bookDAO.updateBookStatus(loan.getBookId(),0);
    }
}
