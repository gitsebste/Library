/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dell
 */

@Entity
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class Book {
    
    @Id @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany(cascade = {
                CascadeType.PERSIST},fetch = FetchType.LAZY)//(cascade = { CascadeType.ALL })
    //@Cascade({CascadeType.SAVE_UPDATE})
    //    @JoinTable(
    //        name = "Books_Authors"
    //        //joinColumns = { @JoinColumn(name = "id") }, //book_id
    //        //inverseJoinColumns = { @JoinColumn(name = "id") }
    //    )
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE,
//                CascadeType.REFRESH
//            })
    private Set<Author> authors=new HashSet<>();

    public Book() {}

    public Book( String title, Set<Author> author) {        
        this.title = title;
        this.authors = author;
    }

    

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthor() {
        return authors;
    }

    public void setAuthor(Set<Author> author) {
        this.authors = author;
    }    

    public void addAuthor(Author toAdd) {
        if(null==authors)authors=new HashSet<>();
        authors.add(toAdd);
    }

    public void setAuthor() {
        authors=new HashSet<>();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book))return false;
        Book other = (Book)obj;
        if(this==other)return true;
        if(this.hashCode()!=other.hashCode())return false;
        if(this.authors.size()!=other.authors.size())return false;
        return (authors.stream().allMatch(x ->other.authors.contains(x)));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.authors);
        return hash;
    }
    public String idPlusTitle(){
        return id+" "+title;
    }
    public String titlePlusAuthors(){
        return authors.stream()
                .map(x->x.getFirstName()+x.getLastName())
                .reduce("", String::concat)+title;
    }

 
    
}
