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
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
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
public class Author {
    
    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE,
//                CascadeType.REFRESH
//            },mappedBy = "authors")
    @ManyToMany(mappedBy = "authors",cascade = {
                CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Set<Book> books=new HashSet<>();

    public Author() {}

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book add) {
        if(null==books)books = new HashSet<>();
        books.add(add);
    }

    public void setBooks() {
        books = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Author{" + "firstName=" + firstName + " lastName=" + lastName + "\nbooks=" + books.stream().map(x->x.getTitle()).collect(Collectors.toList()) + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        //hash = 29 * hash + Objects.hashCode(this.books);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if(this.hashCode()!=other.hashCode())return false;
        if( (this.books==null || this.books.isEmpty())&&
            (other.books==null || other.books.isEmpty()))
                return true;
        if(this.books.size()!=other.books.size())return false;
        return (books.stream()
                .map(x->x.getId())
                .allMatch(x ->other.books.stream()
                        .map(y->y.getId())
                        .collect(Collectors.toSet()).contains(x)));
    }
  
    
}
