package com.fdd.lms.Model;

import org.springframework.stereotype.Component;

/**
 * @author Lin.wang
 * @date 2018-03-03 13:58.
 */
@Component
public class LoanInfo {
    private String bookId;
    private String bookName;
    private String userId;
    private String userName;
    private String loanTime;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
}
