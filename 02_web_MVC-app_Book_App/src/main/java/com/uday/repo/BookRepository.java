package com.uday.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uday.entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public List<Book> findByActiveSW(String status);

}
