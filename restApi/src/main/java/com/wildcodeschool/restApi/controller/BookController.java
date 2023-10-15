package com.wildcodeschool.restApi.controller;

import com.wildcodeschool.restApi.entity.Book;
import com.wildcodeschool.restApi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(@Autowired BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable long id){
        return this.bookService.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody Book book){
        this.bookService.saveOrUpdate(book, null);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Book book, @PathVariable Long id){
        this.bookService.saveOrUpdate(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id){
        this.bookService.deleteById(id);
    }

    @PostMapping("/search")
    public List<Book> findByTitleContainingOrDescriptionContaining(@RequestBody Map<String, String> body){
        String searchText = body.get("text");
        return this.bookService.findByTitleContainingOrDescriptionContaining(searchText);
    }
}
