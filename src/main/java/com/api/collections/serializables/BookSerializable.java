package com.api.collections.serializables;

import com.api.collections.entities.BookEntity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSerializable extends ItemSerializable 
{
    private String genre;
    private String author;
    private LocalDate datePublished;
    
    public BookSerializable(BookEntity book)
    {
        super(book.getId(), book.getName(), book.getNotes(), book.getImageBytes());
        genre = enumValToString(book.getGenre());
        author = book.getAuthor();
        datePublished = book.getDatePublished().toLocalDate();
    }
    
    public BookSerializable(Long id, String name, String notes, byte[] image,
            String genre, String author, LocalDate datePublished)
    {
        super(id, name, notes, image);
        this.genre = genre;
        this.author = author;
        this.datePublished = datePublished; // LocalDates are immutable so this is okay
    }
}
