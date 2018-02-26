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

    public Book getBook(int id) {
        return bookDAO.selectById(id);
    }

    public List<Book> getAllBook(){
        return bookDAO.selectAll();
    }

}
