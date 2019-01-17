/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.model;

import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Dell
 */
@Entity
public class Library {
    
    private Library() {
    }
    
    private static class Holder {
        private static final Library INSTANCE = new Library();
    }
    public static Library getInstance() {
        return Holder.INSTANCE;
    }
    
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //borrowed?
    HashMap<Book, TwoLongs>  stock= new HashMap<>();

    public HashMap<Book, TwoLongs> getStock() {
        return stock;
    }

    public void setStock(HashMap<Book, TwoLongs> stock) {
        this.stock = stock;
    }
    
    
}
