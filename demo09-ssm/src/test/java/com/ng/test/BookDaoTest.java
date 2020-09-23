package com.ng.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.ng.dao.BookDao;
import com.ng.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao;

    @Before
    public void init() {
        String config = "conf/applicationContext.xml";
        ApplicationContext acx = new ClassPathXmlApplicationContext(config);
        bookDao = acx.getBean("bookDao", BookDao.class);
    }

    @Test
    public void selectBookById() {


        Book book = bookDao.selectBookById(12);
        System.out.println(book);

    }

    @Test
    public void selectForItems() {
        List<Book> books = bookDao.selectForItems(1, 100, 1, 5);
        books.forEach(book -> System.out.println(book));

    }

    @Test
    public void selectForTOtalCount() {

        int i = bookDao.selectForTotalCount(1, 100);
        System.out.println("i --> " + i);
    }

    @Test
    public void another(){
        Book book = new Book();
        book.setId(23);
        book.setName("啊啊啊");
        book.setPrice(new BigDecimal(998));
        book.setAuthor("nng");
        //int i = bookDao.updateBook(book); //修改
       // int i = bookDao.deleteBook(23);   //删除
        int i = bookDao.insertBook(book);   //添加
        System.out.println("i ---> " + i);
    }
}