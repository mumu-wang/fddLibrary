package com.fdd.lms.lmsUtil;

import com.fdd.lms.Model.Book;

import java.util.List;

/**
 * @author Lin.wang
 * @date 2018-03-04 21:51.
 */
public class LmsUtil {
    public static List<Book> transformationBookStatus(List<Book> bookList){
        if (bookList.isEmpty()){
            return bookList;
        }
        for (Book book : bookList) {
            if (book.getBookStatus() == 0) {
                book.setStatus("已借出");
            } else if (book.getBookStatus() == 1) {
                book.setStatus("在库");
            } else {
                book.setStatus("状态有误");
            }
        }
        return bookList;
    }
}
