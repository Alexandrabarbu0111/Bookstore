package com.db.bookstore.controller;

import com.db.bookstore.model.Author;
import com.db.bookstore.model.Book;
import com.db.bookstore.model.DtoCLass;
import com.db.bookstore.model.User;

import com.db.bookstore.service.AuthorService;
import com.db.bookstore.service.BookService;
import com.db.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;


    @GetMapping("/register")
    public ModelAndView getRegisterForm(){
        ModelAndView modelAndView = new ModelAndView("register-form");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView addUser(User user){
        user.setRole("Client");
        userService.insertUser(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm(){
        ModelAndView modelAndView = new ModelAndView("login-form");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView verifyUser(User user, HttpServletResponse response){
        try {
            User user1 = userService.findByUsernameOrEmailAndPassword(user);
            response.addCookie(new Cookie("id", "" + user1.getId()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");

        return modelAndView;

    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(@CookieValue(name = "id", defaultValue = "default") int id){
        List<Book> listOfBooks = bookService.getAll();
        ModelAndView modelAndView=new ModelAndView("dashboard");
        User user = userService.findUserById(id);
        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("bookList",listOfBooks);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addBooks(@CookieValue(name = "id", defaultValue = "default") int id) {
        User user = userService.findUserById(id);
        if(user.getRole().equals("Client"))
        {
            ModelAndView modelAndView = new ModelAndView("error-message");
            modelAndView.addObject("errorMessage","You have to be an admin "
        + "to add books");
        return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("add-books");
        DtoCLass dtoCLass = new DtoCLass();
        List<Author> authorList = authorService.getAll();
        List<String> authorNames = new ArrayList<>();
        for(int i = 0;i < authorList.size(); i++)
         authorNames.add(authorList.get(i).getName());
        modelAndView.addObject("dtoCLass", dtoCLass);
        modelAndView.addObject("authorNames",authorNames);
        return modelAndView;
    }

    @PostMapping("/th-new")
    @Transactional
    public ModelAndView addThBook(@ModelAttribute DtoCLass dtoCLass) {
        Book book = new Book(dtoCLass.getTitle(), dtoCLass.getPages(), dtoCLass.getPublisher());
        Set<Author> setAuthors = new HashSet<>();
        List<String> namesAuthor = dtoCLass.getAuthorNames();
        for(int i = 0;i<namesAuthor.size();i++)
        {Author author = authorService.getByName(namesAuthor.get(i));
            setAuthors.add(author);}
        book.setAuthorList(setAuthors);
        bookService.add(book);
        ModelAndView modelAndView = new ModelAndView("added-books");
        return modelAndView;
    }



}
