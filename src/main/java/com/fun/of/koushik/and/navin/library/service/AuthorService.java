/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.service;

import com.fun.of.koushik.and.navin.library.model.Author;
import com.fun.of.koushik.and.navin.library.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookService bookService;
    
    public AuthorService(){                
    }
    
    public Optional<Author> getById(long id){
        return authorRepository.findById(id);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author add(Author toAdd) {
        return authorRepository.save(toAdd);
    }
    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }
}