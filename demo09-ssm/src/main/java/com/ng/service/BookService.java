package com.ng.service;

import com.ng.domain.Book;
import com.ng.domain.Page;

import java.util.List;


public interface BookService {

    Page<Book> page(int minPrice, int maxPrice, int pageNo, int pageSize);
    Book queryBook(Integer id);
    void addBook(Book book);
    void editBook(Book book);
    void deleteBook(Integer id);

}
