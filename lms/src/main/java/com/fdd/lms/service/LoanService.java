package com.fdd.lms.service;

import com.fdd.lms.Model.Loan;
import com.fdd.lms.dao.BookDAO;
import com.fdd.lms.dao.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /*
    *功能：借书
    */
    public void loanBook(Loan loan) {
        loanDAO.insertLoan(loan);
        bookDAO.updateBookStatus(loan.getBookId(), 0);
    }

//    public Loan selectLoan(String bookId) {
//        return loanDAO.selectLoanById(bookId);
//    }

    /*
    *功能：还书
    */
    public void returnBook(String bookId){
        loanDAO.updateFinishStatusById(bookId);
    }

    /*
    *功能：通过图书ID查找未归还图书
    */
    public Loan selectNotFinishByBookId(String bookId) {
        return loanDAO.selectNotFinishLoanByBookId(bookId);
    }

    /*
    *功能：通过用户ID查询未归还图书
    */
    public List<Loan> selectNotFinishByUserId(String userId){
        return loanDAO.selectNotFinishLoanByUserId(userId);
    }

}
