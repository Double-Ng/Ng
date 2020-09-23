package com.ng.dao;

import com.ng.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookDao {

    public Book selectBookById(Integer id);

    public List<Book> selectForItems(@Param(("min")) int min, @Param("max") int max, @Param("begin") int begin, @Param("pageSize") int pageSize);

    public int selectForTotalCount(@Param("min") int min, @Param("max") int max);

    int insertBook(Book book);
    int updateBook(Book book);
    int deleteBook(int id);

    int selectMaxPrice();
}