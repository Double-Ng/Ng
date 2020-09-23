package com.ng.controller;

import com.ng.domain.Book;
import com.ng.domain.Page;
import com.ng.service.BookService;
import com.ng.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/manager/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/add.do")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "forward:/manager/book/page.do";
    }

    @RequestMapping(value = "/edit.do")
    public String editBook(Book book) {
        bookService.editBook(book);
        return "forward:/manager/book/page.do";
    }

    @RequestMapping(value = "/delete.do")
    public String deleteBook(Integer id) {
        bookService.deleteBook(id);
        return "forward:/manager/book/page.do";
    }

    @RequestMapping(value = "/getBook.do")
    public String getBook(Integer id, Integer pageNo, HttpServletRequest request) {

        Book book = bookService.queryBook(id);
        request.setAttribute("book", book);
        return "manager/book_edit";
    }

    @RequestMapping(value = "/page.do")
    // public ModelAndView page(int min, int max, int pageNo,int pageSize, HttpServletRequest request){
    public ModelAndView page(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), 0);

        Page<Book> page = bookService.page(min, max, pageNo, pageSize);
        page.setUrl("manager/book/page.do?");

        mv.addObject("page", page);
        mv.setViewName("manager/book_manager1");

        return mv;
    }

}
