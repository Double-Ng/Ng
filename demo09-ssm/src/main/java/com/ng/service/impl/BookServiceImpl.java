package com.ng.service.impl;

import com.ng.dao.BookDao;
import com.ng.domain.Book;
import com.ng.domain.Page;
import com.ng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Page<Book> page(int minPrice, int maxPrice, int pageNo, int pageSize) {

        Page<Book> page = new Page<>();


        maxPrice = (maxPrice != 0) ? maxPrice : bookDao.selectMaxPrice();
        Integer totalCount = bookDao.selectForTotalCount(minPrice, maxPrice);
        Integer pageTotal = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            pageTotal++;
        }

        page.setTotalCount(totalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDao.selectForItems(minPrice, maxPrice, begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Book queryBook(Integer id) {
        return bookDao.selectBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDao.insertBook(book);
    }

    @Override
    public void editBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }
}
