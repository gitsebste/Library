/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.model;

/**
 *
 * @author Dell
 */
public class TwoLongs {
    long quantity;long borrowed;

    public TwoLongs(long quantity, long borrowed) {
        this.quantity = quantity;
        this.borrowed = borrowed;
    }
    

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(long borrowed) {
        this.borrowed = borrowed;
    }
    
}
