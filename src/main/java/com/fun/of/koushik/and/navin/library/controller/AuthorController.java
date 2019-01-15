/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fun.of.koushik.and.navin.library.model.Author;
import com.fun.of.koushik.and.navin.library.service.AuthorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Dell
 */
@RestController
@RequestMapping(path="/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    
    @Autowired
    AuthorService authorService;
    
    @RequestMapping(method=RequestMethod.GET)
    public List<Author> all(){//@RequestParam(value="name", defaultValue="World") String name) {
        return authorService.getAll();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/{id}")
    public Optional<Author> oneById(@PathVariable("id") long id) {                
        return authorService.getById(id);
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/{id}")
    public void delete(@PathVariable("id") long id) {                
        authorService.deleteById(id);
    }
    @RequestMapping(method=RequestMethod.PUT,value="/{id}")
    public Author update(@PathVariable("id") long id,@RequestBody Author author) {
        author.setId(id);
        return authorService.add(author);
    }
    
    @RequestMapping(method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author add(@RequestBody Author author) {

        return authorService.add(author);
    }
    
}
