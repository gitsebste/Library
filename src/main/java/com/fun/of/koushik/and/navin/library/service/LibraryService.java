/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.service;

import com.fun.of.koushik.and.navin.library.model.Book;
import com.fun.of.koushik.and.navin.library.model.Library;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class LibraryService {
    
    public void addToStock(Book toAdd){
        int ilosc=0;
        HashMap<Book, Long> stock = Library.getInstance().getStock();
        if(stock.containsKey(toAdd)){
            Long quantity = stock.get(toAdd);
            if(0==quantity||null==quantity){
                quantity=1L;
                ilosc=1;
            }
            else{
                quantity++;
                ilosc=quantity.intValue();
            }
            stock.replace(toAdd, quantity);
        }
        else{
            stock.put(toAdd, 1L);
            ilosc=1;
        }
        System.out.println("Ilosc po dodaniu: "+ilosc);
    }
    public void removeFromStock(Book toAdd){
        HashMap<Book, Long> stock = Library.getInstance().getStock();
        if(!stock.containsKey(toAdd))return;
        if(0<stock.get(toAdd)){
            stock.replace(toAdd,stock.get(toAdd)-1);
            System.out.println("Ilosc po usunieciu: "+stock.get(toAdd));
        }
    }

    public Set<String> allOnStock() {
        HashMap<Book, Long> stock = Library.getInstance().getStock();
        return stock.entrySet().stream().filter(es->{return es.getValue()>0;}).map(es->es.getKey().idPlusTitle()).collect(Collectors.toSet()); 
        //).allMatch(es->es.)//collect((k,v)->{System.out.println(k+v);});
        
    }

    public Set<String> allByAuthorOrTitle(String allByAuthorOrTitle) {
        HashMap<Book, Long> stock = Library.getInstance().getStock();
        return stock.entrySet().stream()
                .filter(es->{return es.getKey().titlePlusAuthors().contains(allByAuthorOrTitle);})
                .map(es->es.getKey().idPlusTitle())
                .collect(Collectors.toSet()); 
    }
    
}
