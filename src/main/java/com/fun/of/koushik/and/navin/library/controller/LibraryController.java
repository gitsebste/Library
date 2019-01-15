/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.controller;

import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.service.LibraryService;
import java.util.List;
import java.util.Set;
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
@RequestMapping(path="/library", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibraryController {
    
    @Autowired
    LibraryService libraryService;
    
//    public void lendByTitle(){        
//    }
        @RequestMapping(method=RequestMethod.GET)
    public Set<String> allOnStock() {
        return libraryService.allOnStock();
    }
    @RequestMapping(method=RequestMethod.GET,value="/{authorOrTitle}")
    public Set<String> allByAuthorOrTitle(@PathVariable("authorOrTitle") String allByAuthorOrTitle) {
        return libraryService.allByAuthorOrTitle(allByAuthorOrTitle);//(String author);
    }
    
}
