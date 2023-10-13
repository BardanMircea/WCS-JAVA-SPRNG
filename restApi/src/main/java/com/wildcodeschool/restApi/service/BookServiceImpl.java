package com.wildcodeschool.restApi.service;

import com.wildcodeschool.restApi.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    public BookServiceImpl(@){

    }
}
