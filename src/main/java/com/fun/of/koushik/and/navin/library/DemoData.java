/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library;

import com.fun.of.koushik.and.navin.library.model.Author;
import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.service.AuthorService;
import com.fun.of.koushik.and.navin.library.service.BookService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dell
 */
@Component
public class DemoData {

    @Autowired
    private  AuthorService authorService;
    @Autowired
    private  BookService bookService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Set<Author> authors = new HashSet<>();
       
        authors.add(new Author("David","Halliday",null));        
        authors.add(new Author("Robert","Resnick",null));
        authors.add(new Author("Jearl","Walker",null));

        bookService.add(new Book("Fundamentals of Physics",authors));
        authors.clear();
        authors.add(new Author("Richard","Feynman",null));
        bookService.add(new Book("The Feynman Lectures on Physics",authors));
        //Adam Mickiewicz wrote Mr Tadeusz or Tadeusz Mickiewicz wrote Mr Adam
        //http://localhost:8080/library/mickie
        authors.clear();
        authors.add(new Author("Adam","Mickiewicz",null));
        bookService.add(new Book("Pan Tadeusz",authors));

    }
}
