package com.api.collections.serializables;

import com.api.collections.entities.BookEntity;
import com.api.collections.entities.BookEntity.Genre;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSerializable extends ItemSerializable 
{
    private Genre genre;
    private String author;
    private LocalDate datePublished;
    
    public BookSerializable(BookEntity book)
    {
        super(book.getId(), book.getName(), book.getNotes(), book.getImageBytes());
        genre = book.getGenre();
        author = book.getAuthor();
        datePublished = book.getDatePublished();
    }
    
    public BookSerializable(Long id, String name, String notes, byte[] image,
            Genre genre, String author, LocalDate datePublished)
    {
        super(id, name, notes, image);
        this.genre = genre;
        this.author = author;
        this.datePublished = datePublished; // LocalDates are immutable so this is okay
    }
    
    @Override
    public BookEntity toEntity()
    {
        return new BookEntity(getId(), getName(), getNotes(), getImageBytes(), genre, author, datePublished);
    }
}
