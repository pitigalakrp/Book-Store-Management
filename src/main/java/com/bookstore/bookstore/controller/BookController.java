package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.entity.Book;
import com.bookstore.bookstore.entity.MyBookList;
import com.bookstore.bookstore.service.BookService;
import com.bookstore.bookstore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAvailableBooks(){
        List<Book> list = bookService.getAvailableBooks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("availableBooks");
        modelAndView.addObject("book",list);
        return modelAndView;
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/available_books";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable int id){
        Book book = bookService.getBookById(id);
        MyBookList myBookList = new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
        myBookListService.saveMyBook(myBookList);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable int id,Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "bookEdit";
    }

    @RequestMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.deleteById(id);
        return "redirect:/available_books";
    }

}
