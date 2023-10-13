package com.wildcodeschool.restApi.repository;

import com.wildcodeschool.restApi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitleContainingOrDescriptionContaining(String searchText, String sameSearchText);
}
