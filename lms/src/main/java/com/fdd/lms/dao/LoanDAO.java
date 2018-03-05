package com.fdd.lms.dao;

import com.fdd.lms.Model.Loan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lin.wang
 * @date 2018-03-02 19:32.
 */
@Mapper
public interface LoanDAO {

    String TABLE_LOAN = "loanrelation";
    String INSET_FIELDS = " loan_time, book_id, user_id, return_time ";
    String SELECT_FIELDS = " loanrelation.loan_time, loanrelation.book_id, loanrelation.user_id, loanrelation.return_time ";

    @Insert({"insert into ", TABLE_LOAN, "(", INSET_FIELDS,
            ") values (#{loanTime},#{bookId},#{userId},#{loanTime})"})
    int insertLoan(Loan loan);

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_LOAN, " where book_id=#{bookId}"})
//    Loan selectLoanById(@Param("bookId") int bookId);

//    @Select({"select ", SELECT_FIELDS, " from ", TABLE_LOAN, " where book_id=#{bookId} order by loan_id desc limit 0,1;"})
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_LOAN, " where book_id=#{bookId} and loan_finish = 0"})
    Loan selectNotFinishLoanByBookId(@Param("bookId") int bookId);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_LOAN, " where user_id=#{userId} and loan_finish = 0"})
    List<Loan> selectNotFinishLoanByUserId(@Param("userId") String userId);

    @Update({"update ",TABLE_LOAN," set loan_finish = 1 where book_id=#{bookId} "})
    void updateFinishStatusById(@Param("bookId") int bookId);

}
