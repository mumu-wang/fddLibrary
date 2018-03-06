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

    String TABLE_NAME = " bookinfo ";
    String INSET_FIELDS = " book_id, book_name, book_author, book_isbn, book_cg, book_pos, book_pre";
    String SELECT_FIELDS = " book_id, book_name, book_author,book_isbn, book_cg, book_pos, book_pre, book_status";

    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{bookId},#{bookName},#{bookAuthor},#{bookIsbn},#{bookCg},#{bookPos},#{bookPre})"})
    int insertBook(Book book);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_id=#{bookId} and book_del = 0"})
    Book selectBookById(@Param("bookId") int bookId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_del = 0"})
    List<Book> selectAll();

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_id=#{bookId} and book_status = #{bookStatus} and book_del = 0"})
    Book selectBookByIdAndStatus(@Param("bookId") int bookId,
                                 @Param("bookStatus") int bookStatus);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_id=#{bookId} and book_del = #{bookDel}"})
    Book selectBookByIdAndDel(@Param("bookId") int bookId,
                              @Param("bookDel") int bookDel);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where book_name like \"%\"#{bookName}\"%\" and book_del = 0 "})
    List<Book> selectByFuzzyName(@Param("bookName") String bookName);

    @Update({"update ", TABLE_NAME, " set book_name=#{bookName}, book_isbn=#{bookIsbn}, " +
            "book_cg=#{bookCg},book_pos=#{bookPos},book_pre=#{bookPre}" +
            " where book_id=#{bookId}"})
    void updateBook(Book book);

    @Update({"update ", TABLE_NAME, " set book_status = #{bookStatus} where book_id=#{bookId}"})
    void updateBookStatus(@Param("bookId") int bookId,
                          @Param("bookStatus") int bookStatus);

    @Update({"update ", TABLE_NAME, " set book_del = #{bookDel} where book_id=#{bookId}"})
    void updateBookDel(@Param("bookId") int bookId,
                       @Param("bookDel") int bookDel);


//
//    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
//    void deleteById(int id);
}
