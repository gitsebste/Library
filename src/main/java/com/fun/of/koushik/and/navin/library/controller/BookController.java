/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.controller;

import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.service.BookService;
import com.fun.of.koushik.and.navin.library.service.LibraryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
@RequestMapping(path="/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    LibraryService libraryService;
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Book> all(){//@RequestParam(value="name", defaultValue="World") String name) {
        return bookService.getAll();
    }
    @RequestMapping(method=RequestMethod.GET,value="/{id}")
    public Optional<Book> oneById(@PathVariable("id") long id) {                
        return bookService.getById(id);
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/{id}")
    public void delete(@PathVariable("id") long id) {             
        bookService.deleteById(id);
    }
    @RequestMapping(method=RequestMethod.PUT,value="/{id}")
    public Book update(@PathVariable("id") long id,@RequestBody Book toUpdate) {
        toUpdate.setId(id);
        return bookService.add(toUpdate);
    }
    
    @RequestMapping(method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book add(@RequestBody Book toAdd) {

        return bookService.add(toAdd);
    }

}
