/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fun.of.koushik.and.navin.library.repository;

import com.fun.of.koushik.and.navin.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
}
