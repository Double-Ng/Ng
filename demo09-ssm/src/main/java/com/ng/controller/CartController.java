package com.ng.controller;

import com.ng.domain.Book;
import com.ng.domain.Cart;
import com.ng.domain.CartItem;
import com.ng.service.BookService;
import com.ng.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/ajaxAddItem.do")
    @ResponseBody
    public Map<String, Object> ajaxAddItem(int id, HttpServletRequest request){
        Book book = bookService.queryBook(id);
        CartItem item = new CartItem(id,book.getName(), 1, book.getPrice());
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(item);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", item.getName());
        request.getSession().setAttribute("lastName", item.getName());
        return resultMap;

    }

    @RequestMapping(value = "/addItem.do")
    public String addItem(int id, HttpServletRequest request){
        Book book = bookService.queryBook(id);
        CartItem item = new CartItem(id,book.getName(), 1, book.getPrice());
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(item);
        request.getSession().setAttribute("lastName", item.getName());
        return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping(value = "/deleteItem.do")
    public String deleteItem(HttpServletRequest request, int id){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/updateCount.do")
    public String updateCount(HttpServletRequest request, int count, int id){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
        }
        return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping(value = "/clearItem.do")
    public String clearItem(HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
        }
        return "redirect:" + request.getHeader("Referer");
    }

}





































