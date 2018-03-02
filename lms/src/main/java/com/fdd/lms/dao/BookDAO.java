package com.fdd.lms.dao;
/**
 * @author Lin.wang
 * @date 2018-02-26 21:59.
 */

import com.fdd.lms.Model.Book;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BookDAO {

    String TABLE_NAME = "bookinfo";
    String INSET_FIELDS = " book_id, book_name, book_isbn, book_cg, book_pos, book_pre";
    String SELECT_FIELDS = " book_id, book_name, book_isbn, book_cg, book_pos, book_pre, book_status";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{bookId},#{bookName},#{bookIsbn},#{bookCg},#{bookPos},#{bookPre})"})
    int insertBook(Book book);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_id=#{id}"})
    Book selectBookById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME})
    List<Book> selectAll();

    @Update({"update ", TABLE_NAME, " set book_name=#{bookName}, book_isbn=#{bookIsbn}, " +
            "book_cg=#{bookCg},book_pos=#{bookPos},book_pre=#{bookPre}" +
            " where book_id=#{bookId}"})
    void updateBook(Book book);
//
//    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
//    void deleteById(int id);
}
