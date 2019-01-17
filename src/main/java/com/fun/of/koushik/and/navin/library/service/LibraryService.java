/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.service;

import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.model.Library;
import com.fun.of.koushik.and.navin.library.model.TwoLongs;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class LibraryService {
    
    @Autowired
    BookService bookService;
    
    public boolean addToStock(long id){
        Optional<Book> tmp = bookService.getById(id);
        if(tmp.isPresent()){            
            return addToStock(tmp.get());
        } 
        return false;
    }
    public boolean addToStock(Book toAdd){
        toAdd=bookService.getById(toAdd.getId()).get();
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        if(stock.containsKey(toAdd)){
            TwoLongs quantityAndBorrowed = stock.get(toAdd);
            if(0==quantityAndBorrowed.getBorrowed())return false;
            stock.replace(toAdd, new TwoLongs(quantityAndBorrowed.getQuantity()+1, quantityAndBorrowed.getBorrowed()-1));return true;
        }
        else{
            stock.put(toAdd, new TwoLongs(1, 0));
            return true;
        }
    }
    public boolean removeFromStock(long id){
        Optional<Book> tmp = bookService.getById(id);
        if(tmp.isPresent()){            
            return removeFromStock(tmp.get());
        } 
        return false;
    }
    public boolean removeFromStock(Book toAdd){
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        if(!stock.containsKey(toAdd)){return false;}        
        if(0<stock.get(toAdd).getQuantity()){
            stock.replace(toAdd,
                    new TwoLongs(stock.get(toAdd).getQuantity()-1,stock.get(toAdd).getBorrowed()+1));
            return true;
        }
        return false;
    }

    public Set<String> allOnStock() {
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        return stock.entrySet().stream().filter(es->{return es.getValue().getQuantity()>0;}).map(es->es.getKey().idPlusTitle()).collect(Collectors.toSet()); 
        
    }

    public Set<String> allByAuthorOrTitle(String allByAuthorOrTitle) {
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        return stock.entrySet().stream()
                .filter(es->{return es.getKey().titlePlusAuthors().contains(allByAuthorOrTitle);})
                .map(es->es.getKey().idPlusTitle())
                .collect(Collectors.toSet()); 
    }

    public Set<String> allByAuthor(String author) {
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        return stock.entrySet().stream()
                .filter(es->{return es.getKey().getAuthor().toString().toLowerCase().contains(author);})
                .map(es->es.getKey().idPlusTitle())
                .collect(Collectors.toSet()); 
    }

    public Set<String> allByTitle(String title) {
        HashMap<Book, TwoLongs> stock = Library.getInstance().getStock();
        return stock.entrySet().stream()
                .filter(es->{return es.getKey().toString().toLowerCase().contains(title);})
                .map(es->es.getKey().idPlusTitle())
                .collect(Collectors.toSet()); 
    }

    public boolean borrowById(long id) {
        return removeFromStock(id);
    }

    public boolean returnById(long id) {
        return addToStock(id);
    }
    
}
