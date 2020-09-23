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
@RequestMapping(value = "/client")
public class ClientBookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/page.do")
    public ModelAndView page(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int minPrice = WebUtils.parseInt(request.getParameter("min"), 0);
        int maxPrice = WebUtils.parseInt(request.getParameter("max"), 0);

        String url = null;
        if (maxPrice != 0){
            url = "&min=" + minPrice + "&max=" + maxPrice;
        }

        Page<Book> page = bookService.page(minPrice, maxPrice, pageNo, pageSize);
        page.setUrl("client/page.do?" + url);
        mv.addObject("page", page);
        mv.setViewName("client/index");

        return mv;
    }

}
