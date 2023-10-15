package com.wildcodeschool.restApi.service;

import com.wildcodeschool.restApi.entity.Book;
import com.wildcodeschool.restApi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(@Autowired BookRepository bookRepository){
    this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll(){
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(Book book, Long id){
        Optional.ofNullable(id).ifPresent(book::setId);
        this.bookRepository.save(book);
    }

    @Override
    public void deleteById(long id){
        Optional<Book> toDelete = this.findById(id);
        if (toDelete.isPresent()) this.bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findByTitleContainingOrDescriptionContaining(String searchText) {
        return this.bookRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchText, searchText);
    }
}
