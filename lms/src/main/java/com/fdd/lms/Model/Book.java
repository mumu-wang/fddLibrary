package com.fdd.lms.Model;

/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

public class Book {

    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookIsbn;
    private String bookCg;
    private String bookPos;
    private String bookPre;
    private int bookStatus; //0:借出 1:在库
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookCg() {
        return bookCg;
    }

    public void setBookCg(String bookCg) {
        this.bookCg = bookCg;
    }

    public String getBookPos() {
        return bookPos;
    }

    public void setBookPos(String bookPos) {
        this.bookPos = bookPos;
    }

    public String getBookPre() {
        return bookPre;
    }

    public void setBookPre(String bookPre) {
        this.bookPre = bookPre;
    }

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }
}
