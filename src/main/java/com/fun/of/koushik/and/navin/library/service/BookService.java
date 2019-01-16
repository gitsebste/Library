/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.service;

import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */

@Service
public class BookService {
    
    @Autowired
    AuthorService authorService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LibraryService libraryService;
    
    public Book add(Book toAdd) {      
        Book ret = bookRepository.save(toAdd);
        libraryService.addToStock(toAdd);
        return ret;
    }


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(long id) {
        libraryService.removeFromStock(bookRepository.getOne(id));
        bookRepository.deleteById(id);
    }
    
}
