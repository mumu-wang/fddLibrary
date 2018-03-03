package com.fdd.lms.service;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.lms.Model.Book;
import com.fdd.lms.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public void addBook(Book book) {
        bookDAO.insertBook(book);
    }

    public Book getBookById(int id) {
        return bookDAO.selectBookById(id);
    }

    public List<Book> getAllBook() {
        return bookDAO.selectAll();
    }

    public Book getBookByIdAndStatus(int bookId, int bookStatus) {
        return bookDAO.selectBookByIdAndStatus(bookId, bookStatus);
    }

    public Book getBookByIdAndDel(int bookId, int bookDel) {
        return bookDAO.selectBookByIdAndDel(bookId, bookDel);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void updateBookStatus(int bookId, int bookStatus) {
        bookDAO.updateBookStatus(bookId, bookStatus);
    }

    public void updateBookDel(int bookId, int bookDel) {
        bookDAO.updateBookDel(bookId, bookDel);
    }

}
