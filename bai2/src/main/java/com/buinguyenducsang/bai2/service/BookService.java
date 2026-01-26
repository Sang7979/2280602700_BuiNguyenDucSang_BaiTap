package com.buinguyenducsang.bai2.service;

import com.buinguyenducsang.bai2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Lap trinh Java", "Nguyen Van A"),
            new Book(2, "Spring Boot Co Ban", "Tran Van B"),
            new Book(3, "Java Web", "Le Van C")
    ));


    // GET all
    public List<Book> getAllBooks() {
        return books;
    }

    // GET by id
    public Book getBookById(int id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
        return book.orElse(null);
    }

    // POST
    public void addBook(Book book) {
        books.add(book);
    }

    // PUT
    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }

    // DELETE
    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
