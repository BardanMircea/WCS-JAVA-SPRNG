package com.wildcodeschool.restApi.service;

import com.wildcodeschool.restApi.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(long id);

    void saveOrUpdate(Book book);

    void deleteById(long id);

    List<Book> findByTitleContainingOrDescriptionContaining(String searchText);
}
