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

    String TABLE_LOAN = "loanrelation";
    String TABLE_BOOK = "bookinfo";
    String INSET_FIELDS = " loan_time, book_id, user_id, return_time ";
    String SELECT_FIELDS =" loanrelation.loan_time, loanrelation.book_id, loanrelation.user_id, loanrelation.return_time ";

    @Insert({"insert into ", TABLE_LOAN, "(", INSET_FIELDS,
            ") values (#{loanTime},#{bookId},#{userId},#{loanTime})"})
    int insertLoan(Loan loan);

}
