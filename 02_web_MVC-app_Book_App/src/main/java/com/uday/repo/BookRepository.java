package com.uday.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uday.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
