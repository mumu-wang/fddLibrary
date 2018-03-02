package com.fdd.lms.dao;

import com.fdd.lms.Model.Loan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Lin.wang
 * @date 2018-03-02 19:32.
 */
@Mapper
public interface LoanDAO {

    String TABLE_NAME = " loanrelation ";
    String INSET_FIELDS = " loan_time, book_id, user_id, return_time ";
    String SELECT_FIELDS =" loan_time, book_id, user_id, return_time ";
    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{loanTime},#{bookId},#{userId},#{loanTime})"})
    int insertLoan(Loan loan);

    @Select({"select ",SELECT_FIELDS, "from",TABLE_NAME,
    " where book_id = #{bookId} and return_status = 0"})
    Loan selectLoanBookStatus(int bookId);

    @Update({"update ", TABLE_NAME, " set return_status = 1 where book_id=#{bookId}"})
    void returnBookById(int bookId);


}
